package com.wizy.android.student.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import com.wizy.android.student.R
import com.wizy.android.student.base.BaseActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        animateLogo { showHiddenView() }
        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
        ivTheme.setOnClickListener(this)
    }

    private fun showHiddenView() {
        tvAppName.visibility = View.VISIBLE
        btnLogin.visibility = View.VISIBLE
        btnSignUp.visibility = View.VISIBLE
    }


    private fun animateLogo(onDone: () -> Unit) {
        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.move_up_anim)
        anim.fillAfter = true
        logo.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                onDone()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

    }

    override fun onClick(v: View?) {
        when (v) {
            btnSignUp -> moveToNextActivity(SignUpActivity::class.java)
            btnLogin -> moveToNextActivity(LoginActivity::class.java)
            ivTheme -> toggleNightMode()
        }
    }

    private fun moveToNextActivity(@NonNull cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

}
