package com.owen.quakealert_owen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.ActivityMainBinding
import com.owen.quakealert_owen.helper.Const
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object{
        var loginID = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginID = intent.getStringExtra("login_id").toString()

        supportActionBar?.hide()
        Toast.makeText(this, "Login ID : $loginID", Toast.LENGTH_SHORT).show()
        fragmentListener()



    }

    private fun fragmentListener(){
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.history -> replaceFragment(HistoryFragment())
                R.id.addcomment -> replaceFragment(AddCommentFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.comment -> replaceFragment(CommentFragment())
                else->{

                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}