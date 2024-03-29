package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
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


    }

    private fun listener() {
        binding.regBtn.setOnClickListener {
            val name = binding.commentEdittext.text.toString().trim()
            val username = binding.usernameEdittext.text.toString().trim()
            val password = binding.passwordEdittext.text.toString().trim()
            val email = binding.emailEdittext.text.toString().trim()

            //checker
            if (name.isEmpty()){
                binding.commentLayout.error = "Nama tidak boleh kosong"
                binding.commentLayout.requestFocus()
                return@setOnClickListener
            }else{
                binding.commentLayout.error = ""
            }
            if (username.isEmpty()){
                binding.usernameLayout.error = "Username tidak boleh kosong"
                binding.usernameLayout.requestFocus()
                return@setOnClickListener
            }else{
                binding.usernameLayout.error = ""
            }
            if (email.isEmpty()){
                binding.emailLayout.error = "Email tidak boleh kosong"
                binding.emailLayout.requestFocus()
                return@setOnClickListener
            }else{
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.emailLayout.error = "Email tidak valid"
                    binding.emailLayout.requestFocus()
                    return@setOnClickListener
                }else {
                    binding.emailLayout.error = ""
                }
            }
            if (password.isEmpty()){
                binding.passwordLayout.error = "Password tidak boleh kosong"
                binding.passwordLayout.requestFocus()
                return@setOnClickListener
            }else{
                binding.passwordLayout.error = ""
            }


            if (name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                viewModel.createUser(name, username, password, email, "member", "").enqueue(object : retrofit2.Callback<SubmitRegister> {
                    override fun onResponse(call: retrofit2.Call<SubmitRegister>, response: retrofit2.Response<SubmitRegister>) {
                        if (response.isSuccessful) {
                            val myIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(myIntent)
                            Toast.makeText(this@RegisterActivity, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this@RegisterActivity, "Gagal membuat akun", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<SubmitRegister>, t: Throwable) {
                        Log.d("TAG", "onFailure: ${t.message}")
                    }
                })
            }else{
                Toast.makeText(this, "Semua Harus Diisi", Toast.LENGTH_SHORT).show()
            }

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