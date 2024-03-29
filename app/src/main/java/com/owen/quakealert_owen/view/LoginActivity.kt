package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.databinding.ActivityLoginBinding
import com.owen.quakealert_owen.model.SubmitLogin
import com.owen.quakealert_owen.view.MainActivity.Companion.login
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
            if (email.isEmpty()){
                binding.usernameLayout.error = "Email tidak boleh kosong"
                binding.usernameLayout.requestFocus()
                return@setOnClickListener
            }else{
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.usernameLayout.error = "Email tidak valid"
                    binding.usernameLayout.requestFocus()
                    return@setOnClickListener
                }else {
                    binding.usernameLayout.error = ""
                }
            }
            if (password.isEmpty()){
                binding.passwordLayout.error = "Password tidak boleh kosong"
                binding.passwordLayout.requestFocus()
                return@setOnClickListener
            }else{
                binding.passwordLayout.error = ""
            }
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.loginUser(email,password).enqueue(object : retrofit2.Callback<SubmitLogin>{
                override fun onResponse(
                    call: retrofit2.Call<SubmitLogin>,
                    response: retrofit2.Response<SubmitLogin>
                ) {
                    if (response.isSuccessful){
                        val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
//                        Toast.makeText(this@LoginActivity, response.body()?.id, Toast.LENGTH_SHORT).show()
                        myIntent.putExtra("login_id",response.body()?.id?.toInt())
                        login = true
                        startActivity(myIntent)
                        Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<SubmitLogin>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })
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