package com.wizy.android.student.ui.activity.signup

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
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
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
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
        btnNext.setOnClickListener{
            moveToNextActivity()
        }
    }
    private fun moveToNextActivity(){
        startActivity(Intent(this,SubjectsSelectionActivity::class.java)
            .putExtra(AppConstants.INTENT_FROM,GreetingActivity::class.java.name)
            .putExtra(AppConstants.INTENT_USER,student))
    }
}
