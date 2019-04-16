package com.wizy.android.student.ui.activity.signup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.Choice
import com.wizy.android.student.model.Student
import com.wizy.android.student.ui.adapter.SubjectAdapter
import kotlinx.android.synthetic.main.activity_subjects_selection.*

class SubjectsSelectionActivity : BaseToolbarActivity(), SubjectAdapter.NextClickListener {
    private var student: Student? = null
    private var from: String? = null
    private var subjects: MutableList<Choice> = arrayListOf()
    private var adapter: SubjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects_selection)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent.hasExtra(AppConstants.INTENT_USER) && intent.hasExtra(AppConstants.INTENT_FROM)) {
            student = intent.getSerializableExtra(AppConstants.INTENT_USER) as Student
            from = intent.getStringExtra(AppConstants.INTENT_FROM)
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
        if (from == GreetingActivity::class.java.name) {
            tvTitle.text = getString(R.string.what_are_your_favourite_subjects)
        } else {
            val preText: String = getString(R.string.what_are_your)
            val middleText = "<font color='#EE0000'> least </font>"
            val postText: String = getString(R.string.favourite_subjects)
            tvTitle.text = fromHtml(preText + middleText + postText)
        }
        getSubjects()
    }

    @SuppressWarnings("deprecation")
    private fun fromHtml(string: String): Spanned {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)
            }
            else -> {
                Html.fromHtml(string)
            }
        }
    }

    private fun getSubjects() {

        var tempSubject = Choice()
        tempSubject.name = Student.Subject.SCIENCE.name
        tempSubject.image = R.drawable.science
        tempSubject.colorString = "#000000"
        subjects.add(tempSubject)

        tempSubject = Choice()
        tempSubject.name = Student.Subject.ENGLISH.name
        tempSubject.image = R.drawable.english
        tempSubject.colorString = "#A57982"
        subjects.add(tempSubject)

        tempSubject = Choice()
        tempSubject.name = Student.Subject.MATHS.name
        tempSubject.image = R.drawable.maths
        tempSubject.colorString = "#120D31"
        subjects.add(tempSubject)

        tempSubject = Choice()
        tempSubject.name = Student.Subject.SOCIAL_STUDIES.name
        tempSubject.image = R.drawable.social_studies
        tempSubject.colorString = "#9381FF"
        subjects.add(tempSubject)

        tempSubject = Choice()
        tempSubject.name = Student.Subject.LANGUAGES.name
        tempSubject.image = R.drawable.languages
        tempSubject.colorString = "#BC69AA"
        subjects.add(tempSubject)

        tempSubject = Choice()
        tempSubject.name = Student.Subject.COMPUTER_SCIENCE.name
        tempSubject.image = R.drawable.computer_science
        tempSubject.colorString = "#EF6F6C"
        subjects.add(tempSubject)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        adapter = SubjectAdapter(this, subjects, this)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    subjects.size -> {
                        2
                    }
                    else -> {
                        1
                    }
                }
            }
        }
        rvSubjects.layoutManager = gridLayoutManager
        rvSubjects.adapter = adapter
    }

    override fun onClickNext(subjects: MutableList<Student.Subject>) {
        if (from == GreetingActivity::class.java.name) {
            student?.favSubjects = subjects
        } else {
            student?.leastFavSubjects = subjects
        }
        moveToNextActivity()
    }

    private fun moveToNextActivity() {
        if (from == GreetingActivity::class.java.name) {
            startActivity(
                Intent(this, SubjectsSelectionActivity::class.java)
                    .putExtra(AppConstants.INTENT_USER, student)
                    .putExtra(AppConstants.INTENT_FROM, SubjectsSelectionActivity::class.java.name)
            )
        } else {
            startActivity(
                Intent(this, HobbySelectionActivity::class.java)
                    .putExtra(AppConstants.INTENT_USER, student)
            )
        }
    }
}