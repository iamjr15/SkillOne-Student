package com.wizy.android.student.ui.activity.signup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
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
                girl.setCardBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor))
            }
            girl -> {
                student?.gender = Student.Gender.GIRL
                girl.setCardBackgroundColor(Color.LTGRAY)
                boy.setCardBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor))
            }
            btnNext -> {
                moveToNextActivity()
            }
        }
    }

    private fun moveToNextActivity() {
        if (student?.gender == null) {
            Snackbar.make(btnNext, getString(R.string.select_gender), Snackbar.LENGTH_SHORT).show()
            return
        }
        startActivity(
            Intent(this, ClassSelectionActivity::class.java)
                .putExtra(AppConstants.INTENT_USER, student)
        )
    }
}
