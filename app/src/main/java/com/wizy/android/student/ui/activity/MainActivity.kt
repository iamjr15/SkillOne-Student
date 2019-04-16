package com.wizy.android.student.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseActivity
import com.wizy.android.student.helper.Preferences
import com.wizy.android.student.model.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    private var student: Student? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        searchSkillMaster.setOnClickListener(this)
        customLearning.setOnClickListener(this)
        student = Preferences.getInstance().student
        student?.let {
            tvName.text = it.firstName
        }

    }

    override fun onClick(v: View?) {
        v?.let {
            when (it) {
                searchSkillMaster -> searchSkillMaster()
                customLearning -> customLearning()
            }
        }
    }

    private fun searchSkillMaster() {
        startActivity(Intent(this, LearningCategoryActivity::class.java))
    }

    private fun customLearning() {
    }
}
