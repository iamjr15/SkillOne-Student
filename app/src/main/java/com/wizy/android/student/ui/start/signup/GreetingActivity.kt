package com.wizy.android.student.ui.start.signup

import android.os.Bundle
import android.widget.Toast
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseActivity
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.Student
import kotlinx.android.synthetic.main.activity_greeting.*

class GreetingActivity : BaseActivity() {
    var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent.hasExtra(AppConstants.INTENT_USER)) {
            student = intent.getSerializableExtra(AppConstants.INTENT_USER) as Student
            setUpViews()
        } else {
            showIntentIsNull()
        }
    }

    private fun showIntentIsNull() {
        Toast.makeText(this, getString(R.string.intent_is_null), Toast.LENGTH_SHORT).show()
        this.finish()
    }

    private fun setUpViews() {
        tvName.text = student?.firstName
    }
}
