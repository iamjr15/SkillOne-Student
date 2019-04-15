package com.wizy.android.student.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.gson.Gson
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import com.wizy.android.student.firebase.FireBaseHelper
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.helper.Preferences
import com.wizy.android.student.model.Student
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etNumber
import kotlinx.android.synthetic.main.activity_login.etPassword
import java.lang.Exception

class LoginActivity : BaseToolbarActivity(), View.OnClickListener {
    private lateinit var reference: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpView()
    }

    private fun setUpView() {
        btnLogin.setOnClickListener(this)
        reference = FireBaseHelper.getInstance().db.collection(AppConstants.FB_COLLECTION)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it) {
                btnLogin -> validaInputs()
            }
        }
    }

    private fun validaInputs() {
        when {
            (etPassword.text.isEmpty() || etPassword.text.length < 4) -> {
                setError(etPassword, getString(R.string.enter_valid_password))
            }
            (etNumber.text.isEmpty() || etNumber.text.length < 9) -> {
                setError(etNumber, getString(R.string.enter_valid_number))
            }
            else -> {
                val number: String = codePicker.selectedCountryCodeWithPlus + etNumber.text.toString()
                val password: String = etPassword.text.toString()
                doLogin(number, password)
            }
        }
    }

    private fun doLogin(number: String, password: String) {
        showProgress()
        reference.document(number)
            .get()
            .addOnSuccessListener { document: DocumentSnapshot? ->
                if (document != null) {
                    userExists(document, password)
                } else {
                    hideProgress()
                    userDoestExists()
                }
            }
            .addOnFailureListener { ex: Exception ->
                run {
                    hideProgress()
                    showError(ex.message.toString())
                }
            }
    }

    private fun userExists(document: DocumentSnapshot, password: String) {
        val json: String = document.data.toString()
        val student: Student? = Gson().fromJson(json, Student::class.java)
        if (student != null) {
            comparePassword(student, password)

        } else {
            hideProgress()
            userDoestExists()
        }

    }

    private fun comparePassword(student: Student, password: String) {
        if (student.password == password) {
            hideProgress()
            Preferences.getInstance().saveStudent(student)
            loginSuccessful()
        } else {
            hideProgress()
            inValidPassword()
        }
    }

    private fun inValidPassword() {
        Snackbar.make(btnLogin, getString(R.string.invalid_password), Snackbar.LENGTH_SHORT).show()
    }

    private fun loginSuccessful() {
        Snackbar.make(btnLogin, getString(R.string.login_successful), Snackbar.LENGTH_SHORT).show()
        moveToNextActivity()
    }

    private fun moveToNextActivity() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }, 800)

    }

    private fun userDoestExists() {
        Snackbar.make(btnLogin, getString(R.string.number_is_not_registered), Snackbar.LENGTH_SHORT).show()
    }

    private fun showError(error: String) {
        Snackbar.make(btnLogin, error, Snackbar.LENGTH_SHORT).show()
    }
}
