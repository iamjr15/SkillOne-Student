package com.wizy.android.student.ui.activity.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import com.wizy.android.student.helper.AppConstants
import com.wizy.android.student.model.Student
import com.wizy.android.student.model.StudentHobby
import com.wizy.android.student.ui.activity.MainActivity
import com.wizy.android.student.ui.adapter.HobbiesAdapter
import kotlinx.android.synthetic.main.activity_hobby_selection.*

class HobbySelectionActivity : BaseToolbarActivity(), HobbiesAdapter.NextClickListener {
    private var student: Student? = null
    private var adapter: HobbiesAdapter? = null
    private var hobbies: MutableList<StudentHobby> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobby_selection)
        getIntentData()
    }


    private fun getIntentData() {
        if (intent.hasExtra(AppConstants.INTENT_USER)) {
            student = intent.getSerializableExtra(AppConstants.INTENT_USER) as Student
            getHobbies()
        } else {
            showIntentIsNull()
        }
    }

    private fun showIntentIsNull() {
        Toast.makeText(this, getString(R.string.intent_is_null), Toast.LENGTH_SHORT).show()
        this.finish()
    }

    private fun getHobbies() {
        var tempHobby = StudentHobby()
        tempHobby.name = Student.Hobby.GUITAR.name
        tempHobby.image = R.drawable.guitar
        tempHobby.colorString = "#000000"
        hobbies.add(tempHobby)

        tempHobby = StudentHobby()
        tempHobby.name = Student.Hobby.PAINTING.name
        tempHobby.image = R.drawable.painting
        tempHobby.colorString = "#F8BBC2"
        hobbies.add(tempHobby)

        tempHobby = StudentHobby()
        tempHobby.name = Student.Hobby.MARTIAL_ARTS.name
        tempHobby.image = R.drawable.martial_art
        tempHobby.colorString = "#191333"
        hobbies.add(tempHobby)

        tempHobby = StudentHobby()
        tempHobby.name = Student.Hobby.DRUM_AND_PERCUSSION.name
        tempHobby.image = R.drawable.drums
        tempHobby.colorString = "#A47881"
        hobbies.add(tempHobby)

        tempHobby = StudentHobby()
        tempHobby.name = Student.Hobby.KEYBOARD.name
        tempHobby.image = R.drawable.keyboard
        tempHobby.colorString = "#927CFA"
        hobbies.add(tempHobby)

        tempHobby = StudentHobby()
        tempHobby.name = Student.Hobby.DANCE.name
        tempHobby.image = R.drawable.dance
        tempHobby.colorString = "#F06E6C"
        hobbies.add(tempHobby)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = HobbiesAdapter(this, hobbies, this)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    hobbies.size -> {
                        2
                    }
                    else -> {
                        1
                    }
                }
            }
        }
        rvHobbies.layoutManager = gridLayoutManager
        rvHobbies.adapter = adapter
    }

    override fun onClickNext(hobbies: MutableList<Student.Hobby>) {
        student?.hobbies = hobbies
        registerUser()
    }

    private fun registerUser() {
        moveToNextActivity()
    }

    private fun moveToNextActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }


}
