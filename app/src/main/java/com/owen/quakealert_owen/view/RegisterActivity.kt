package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.databinding.ActivityRegisterBinding
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        listener()
        var name = binding.commentEdittext.text.toString()
        var username = binding.usernameEdittext.text.toString()
        var password = binding.passwordEdittext.text.toString()
        var email = binding.emailEdittext.text.toString()

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //create user
        viewModel.createUser(name,username,password,email,"member","").let {response->
            if(response.isExecuted){
                val myIntent = Intent(this, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            }
        }



    }

    private fun listener(){
        binding.regBtn.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.toLogTv.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }
        binding.backregBtn.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}