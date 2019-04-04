package com.wizy.android.student.ui.start.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.Student
import kotlinx.android.synthetic.main.activity_gender_selection.*

class GenderSelectionActivity : BaseToolbarActivity(), View.OnClickListener {
    private var student: Student? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender_selection)
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
        boy.setOnClickListener(this)
        girl.setOnClickListener(this)
        btnNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v) {
            boy -> {
                student?.gender = Student.Gender.BOY
                boy.setCardBackgroundColor(Color.LTGRAY)
                girl.setCardBackgroundColor(Color.WHITE)
            }
            girl -> {
                student?.gender = Student.Gender.GIRL
                girl.setCardBackgroundColor(Color.LTGRAY)
                boy.setCardBackgroundColor(Color.WHITE)
            }
            btnNext -> {
                moveToNextActivity()
            }
        }
    }

    private fun moveToNextActivity() {
        startActivity(
            Intent(this, ClassSelectionActivity::class.java)
                .putExtra(AppConstants.INTENT_USER, student)
        )
    }
}
