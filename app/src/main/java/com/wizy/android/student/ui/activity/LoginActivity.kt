package com.wizy.android.student.ui.activity

import android.os.Bundle
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity

class LoginActivity : BaseToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
