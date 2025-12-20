package com.example.whatsappclone.presentation.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.whatsappclone.model.PhoneAuth
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.auth.AuthState
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val database: FirebaseDatabase
) : ViewModel() {
    private val _authState =
        MutableStateFlow<AuthState>(com.example.whatsappclone.presentation.viewmodel.AuthState.Ideal)
    val authState = _authState.asStateFlow()

    private val userRef = database.reference.child("users")

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = com.example.whatsappclone.presentation.viewmodel.AuthState.Loading

        val option = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuth", "OnCodeSent triggered. Verification ID: $id")
                _authState.value =
                    com.example.whatsappclone.presentation.viewmodel.AuthState.CodeSent(
                        verificationId = id
                    )
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("PhoneAuth", "verification failed: ${exception.message}")
                _authState.value = com.example.whatsappclone.presentation.viewmodel.AuthState.Error(
                    exception.message ?: "Verification failed"
                )
            }

        }
    }
}

sealed class AuthState {
    object Ideal : AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Success(val user: PhoneAuth) : AuthState()
    data class Error(val message: String) : AuthState()
}

