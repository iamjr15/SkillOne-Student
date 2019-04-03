package com.wizy.android.student.ui.start.signup

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_gender_selection.*

class GenderSelectionActivity : BaseToolbarActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender_selection)
        boy.setOnClickListener(this)
        girl.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            boy -> {
                boy.setCardBackgroundColor(Color.GRAY)
            }
            girl -> {
                girl.setCardBackgroundColor(Color.GRAY)
            }
        }
    }
}
