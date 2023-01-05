package com.owen.quakealert_owen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.ActivityMainBinding
import com.owen.quakealert_owen.helper.Const
import com.owen.quakealert_owen.model.SubmitComment
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    companion object {
        var loginID = 0
        var login = false
    }

    override fun onStop() {
        super.onStop()
        startService(Intent(this, SplashActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginID = intent.getIntExtra("login_id", 0)
        var commentID = intent.getIntExtra("commentdel_id", 0)
        val sharedPreference = getSharedPreferences("id", MODE_PRIVATE)
//        sharedPreference = intent.getIntExtra("login_id",0)
        val editor = sharedPreference.edit()
        if (login) {
            if (loginID != 0) {
                editor.putInt("id", loginID)
                editor.apply()
            }
        } else {
            editor.putInt("id", 0)
            editor.apply()
            loginID = sharedPreference.getInt("id", 0)
        }

        if (sharedPreference.getInt("id", 0) != 0) {
            loginID = sharedPreference.getInt("id", 0)
        }
        supportActionBar?.hide()
//        Toast.makeText(this, "Login ID : $loginID", Toast.LENGTH_SHORT).show()
        fragmentListener()

        if (commentID != 0) {
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.deleteComment(commentID).enqueue(object : retrofit2.Callback<SubmitComment> {
                override fun onResponse(
                    call: retrofit2.Call<SubmitComment>,
                    response: retrofit2.Response<SubmitComment>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Comment Deleted", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Comment Delete Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<SubmitComment>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }

            })
            replaceFragment(CommentFragment())
        }


    }

    private fun fragmentListener() {
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.history -> replaceFragment(HistoryFragment())
                R.id.addcomment -> replaceFragment(AddCommentFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.comment -> replaceFragment(CommentFragment())
                else -> {

                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}