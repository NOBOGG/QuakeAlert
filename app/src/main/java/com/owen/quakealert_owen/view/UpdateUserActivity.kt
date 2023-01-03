package com.owen.quakealert_owen.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.ActivityUpdateUserBinding
import com.owen.quakealert_owen.model.SubmitRegister
import com.owen.quakealert_owen.view.MainActivity.Companion.loginID
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var viewModel: UserViewModel
    var tempUri:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        Display()



        binding.profileimageBtn.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type="image/*"
            GetResult.launch(myIntent)
        }

        binding.updateBtn.setOnClickListener {
            val profileID = intent.getIntExtra("profile_id",0)
            val name = binding.nameEdittext.text.toString().trim()
            val username = binding.usernameEdittext.text.toString().trim()
            val password = binding.passwordEdittext.text.toString().trim()
            val email = binding.emailEdittext.text.toString().trim()
            //get image url
            val image = binding.profileimageBtn.imageAlpha.toString().trim()

            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.updateUser(profileID, name, username, password, email, "member", image).enqueue(object : retrofit2.Callback<SubmitRegister>{
                override fun onResponse(
                    call: retrofit2.Call<SubmitRegister>,
                    response: retrofit2.Response<SubmitRegister>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@UpdateUserActivity, "Update Berhasil", Toast.LENGTH_SHORT).show()
                        val myIntent = Intent(this@UpdateUserActivity, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }else{
                        Toast.makeText(this@UpdateUserActivity, "Update Berhasil", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<SubmitRegister>, t: Throwable) {
                    Toast.makeText(this@UpdateUserActivity, "Update Berhasil", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data
            binding.profileimageBtn.setImageURI(uri)
            if(uri!=null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    baseContext.getContentResolver().takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                tempUri = uri.toString()
            }
        }
    }

    private fun Display(){

        viewModel.getUserbyId(loginID)
        viewModel.user.observe(this, Observer { response ->

            binding.nameEdittext.setText(response.name)
            binding.usernameEdittext.setText(response.username)
            binding.emailEdittext.setText(response.email)
            binding.profileimageBtn.setImageURI(Uri.parse(response.image))

            if (response.image.isNotEmpty()) {
                binding.profileimageBtn.setImageURI(Uri.parse(response.image))
            } else {
                binding.profileimageBtn.setImageResource(R.drawable.ic_launcher_foreground)
            }
        })
    }
}