package com.example.dndbuilder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.dndbuilder.databinding.ActivityIntroBinding
import android.animation.Animator
import android.media.MediaPlayer
import com.example.dndbuilder.ui.MainActivity
import com.example.dndbuilder.R

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private lateinit var swordSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        swordSound = MediaPlayer.create(this, R.raw.sword_hit)

        binding.introAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                swordSound.start()
            }

            override fun onAnimationEnd(animation: Animator) {
                swordSound.stop()
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}


        })
    }
    override fun onDestroy() {
        super.onDestroy()
        if (::swordSound.isInitialized) {
            swordSound.release()
        }
    }
}