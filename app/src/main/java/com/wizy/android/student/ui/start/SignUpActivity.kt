package com.wizy.android.student.ui.start

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.User
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.StringBuilder


class SignUpActivity : BaseToolbarActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v) {
            btnNext -> validateInputs()
        }
    }

    private fun validateInputs() {
        when {
            etFirstName.text.isEmpty() -> setError(etFirstName, getString(R.string.enter_valid_first_name))
            etLastName.text.isEmpty() -> setError(etLastName, getString(R.string.enter_valid_last_name))
            (etPassword.text.isEmpty() || etPassword.text.length < 4) -> {
                setError(etPassword, getString(R.string.enter_valid_password))
            }
            (etNumber.text.isEmpty() || etNumber.text.length < 9) -> {
                setError(etNumber, getString(R.string.enter_valid_number))
            }
            else -> {
                val user = User()
                user.firstName = etFirstName.text.toString()
                user.lastName = etLastName.text.toString()
                user.password = etPassword.text.toString()
                user.number = StringBuilder()
                    .append(codePicker.selectedCountryCodeWithPlus)
                    .append(etNumber.text.toString())
                    .toString()
                moveToNextActivity(user)
            }
        }
    }

    private fun moveToNextActivity(user: User) {
        startActivity(
            Intent(this, NumberVerificationActivity::class.java)
                .putExtra(AppConstants.INTENT_USER, user)
        )
    }
}
