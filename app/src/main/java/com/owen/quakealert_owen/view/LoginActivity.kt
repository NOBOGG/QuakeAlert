package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owen.quakealert_owen.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        listener()

    }

    private fun listener(){
        binding.loginBtn.setOnClickListener {
            val myIntent = Intent(this,MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.toRegTv.setOnClickListener {
            val myIntent = Intent(this,RegisterActivity::class.java)
            startActivity(myIntent)
        }
        binding.backlogBtn.setOnClickListener{
            val myIntent = Intent(this,MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}