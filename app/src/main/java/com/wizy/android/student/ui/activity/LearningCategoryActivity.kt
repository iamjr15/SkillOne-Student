package com.wizy.android.student.ui.activity


import android.os.Bundle
import androidx.core.content.ContextCompat
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseToolbarActivity

class LearningCategoryActivity : BaseToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_category)
        setToolBar()
    }

    private fun setToolBar() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.pink))
        window.statusBarColor = ContextCompat.getColor(this, R.color.pink)
    }
}
