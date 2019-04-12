package com.wizy.android.student.ui.start.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.Student
import com.wizy.android.student.model.StudentClass
import com.wizy.android.student.ui.adapter.ClassAdapter
import kotlinx.android.synthetic.main.activity_class_selection.*


class ClassSelectionActivity : BaseToolbarActivity(), ClassAdapter.NextClickListener {
    private var student: Student? = null
    private var classes: MutableList<StudentClass> = arrayListOf()
    private var adapter: ClassAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_selection)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent.hasExtra(AppConstants.INTENT_USER)) {
            student = intent.getSerializableExtra(AppConstants.INTENT_USER) as Student
            getClasses()
        } else {
            showIntentIsNull()
        }
    }

    private fun getClasses() {
        var studentClass = StudentClass()
        studentClass.name = Student.Standard.FIVE.name
        studentClass.colorString = "#171733"
        studentClass.image = R.drawable.five
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.SIX.name
        studentClass.colorString = "#8A89FA"
        studentClass.image = R.drawable.six
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.SEVEN.name
        studentClass.colorString = "#C06BA6"
        studentClass.image = R.drawable.seven
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.EIGHT.name
        studentClass.colorString = "#FA6B6C"
        studentClass.image = R.drawable.eight
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.NINE.name
        studentClass.colorString = "#007170"
        studentClass.image = R.drawable.nine
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.TEN.name
        studentClass.colorString = "#91A2B1"
        studentClass.image = R.drawable.ten
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.ELEVEN.name
        studentClass.colorString = "#7DD9FD"
        studentClass.image = R.drawable.eleven
        classes.add(studentClass)

        studentClass = StudentClass()
        studentClass.name = Student.Standard.TWELVE.name
        studentClass.colorString = "#FF5464"
        studentClass.image = R.drawable.twelve
        classes.add(studentClass)

        setUpRecyclerView()
    }

    private fun showIntentIsNull() {
        Toast.makeText(this, getString(R.string.intent_is_null), Toast.LENGTH_SHORT).show()
        this.finish()
    }

    private fun setUpRecyclerView() {
        if (classes.size > 0) {
            adapter = ClassAdapter(this, classes, this)
            val gridLayoutManager = GridLayoutManager(this, 2)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        classes.size -> {
                            2
                        }
                        else -> {
                            1
                        }
                    }
                }
            }
            rvClasses.layoutManager = gridLayoutManager
            rvClasses.adapter = adapter
        }
    }

    override fun onClick(standard: Student.Standard) {
        student?.standard = standard
        moveToNextActivity()
    }

    private fun moveToNextActivity() {
        startActivity(
            Intent(this, GreetingActivity::class.java)
                .putExtra(AppConstants.INTENT_USER, student)
        )
    }
}
