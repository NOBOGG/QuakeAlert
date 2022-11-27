package com.owen.quakealert_owen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.owen.quakealert_owen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        listener()

    }

    private fun listener(){
        binding.loginBtn.setOnClickListener {
            replaceFragment(ProfileFragment())
//            val myIntent = Intent(this, ProfileFragment::class.java)
//            startActivity(myIntent)
//            finish()
        }
        binding.regTv.setOnClickListener {
            val myIntent = Intent(this, SignupActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}