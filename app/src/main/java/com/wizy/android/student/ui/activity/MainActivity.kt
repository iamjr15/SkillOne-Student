package com.wizy.android.student.ui.activity

import android.os.Bundle
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {

    }
}
