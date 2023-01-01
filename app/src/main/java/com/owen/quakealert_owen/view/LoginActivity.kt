package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.databinding.ActivityLoginBinding
import com.owen.quakealert_owen.model.SubmitLogin
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        listener()

    }

    private fun listener(){
        binding.loginBtn.setOnClickListener {
            val email = binding.usernameEdittext.text.toString().trim()
            val password = binding.passwordEdittext.text.toString().trim()
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.loginUser(email,password).enqueue(object : retrofit2.Callback<SubmitLogin>{
                override fun onResponse(
                    call: retrofit2.Call<SubmitLogin>,
                    response: retrofit2.Response<SubmitLogin>
                ) {
                    if (response.isSuccessful){
                        val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                }

                override fun onFailure(call: retrofit2.Call<SubmitLogin>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })
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