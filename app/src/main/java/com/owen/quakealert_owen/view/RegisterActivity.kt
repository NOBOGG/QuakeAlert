package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.databinding.ActivityRegisterBinding
import com.owen.quakealert_owen.model.SubmitRegister
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        listener()




        //create user


//        viewModel.createUser(name, username, password, email, "member", "").let {
//            if (it.isExecuted){
//                val myIntent = Intent(this, LoginActivity::class.java)
//                startActivity(myIntent)
//                finish()
//            }
//        }
//        viewModel.createUser(name,username,password,email,"member","").let {response->
//            if(response.isExecuted){
//                val myIntent = Intent(this, LoginActivity::class.java)
//                startActivity(myIntent)
//                finish()
//            }
//        }


    }

    private fun listener() {
        binding.regBtn.setOnClickListener {
            val name = binding.commentEdittext.text.toString()
            val username = binding.usernameEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()
            val email = binding.emailEdittext.text.toString()
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.createUser(name, username, password, email, "member", "").enqueue(object : retrofit2.Callback<SubmitRegister> {
                override fun onResponse(call: retrofit2.Call<SubmitRegister>, response: retrofit2.Response<SubmitRegister>) {
                    if (response.isSuccessful) {
                        val myIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                }

                override fun onFailure(call: retrofit2.Call<SubmitRegister>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })
        }
        binding.toLogTv.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
        }
        binding.backregBtn.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun checker(){

    }
}