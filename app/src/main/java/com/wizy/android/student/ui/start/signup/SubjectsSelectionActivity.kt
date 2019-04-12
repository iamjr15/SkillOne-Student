package com.wizy.android.student.ui.start.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wizy.android.student.R
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.Student
import com.wizy.android.student.model.StudentSubject

class SubjectsSelectionActivity : AppCompatActivity() {
    private var student: Student? = null
    private var subjects:MutableList<StudentSubject> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects_selection)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent.hasExtra(AppConstants.INTENT_USER)) {
            student = intent.getSerializableExtra(AppConstants.INTENT_USER) as Student
            getSubjects()
        } else {
            showIntentIsNull()
        }
    }

    private fun showIntentIsNull() {
        Toast.makeText(this, getString(R.string.intent_is_null), Toast.LENGTH_SHORT).show()
        this.finish()
    }

    private fun getSubjects() {
        val studentSubject = StudentSubject()
        studentSubject.name = Student.Subject.SCIENCE.name

    }
}
