package com.kindsundev.expense.manager.ui.signup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kindsundev.expense.manager.databinding.ActivitySignUpBinding
import com.kindsundev.expense.manager.utils.onFeatureIsDevelop
import com.kindsundev.expense.manager.utils.startPrepareWalletActivity

class SignUpActivity : AppCompatActivity(), SignUpContract.View {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpPresenter : SignUpPresenter

    override fun getCurrentContext(): Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUpPresenter = SignUpPresenter(this)
        initListener()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener { onClickSignUp() }
        binding.tvSignup.setOnClickListener { onClickSignIn() }
    }

    private fun onClickSignUp() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        signUpPresenter.handlerSignUp(email, password)
    }

    private fun onClickSignIn() { finish() }

    override fun onLoad() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onError(message: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()
        startPrepareWalletActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        signUpPresenter.cleanUp()
    }
}