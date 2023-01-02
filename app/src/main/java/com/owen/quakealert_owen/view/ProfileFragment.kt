package com.owen.quakealert_owen.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.adapter.CommentAdapter
import com.owen.quakealert_owen.databinding.FragmentProfileBinding
import com.owen.quakealert_owen.model.DataX
import com.owen.quakealert_owen.view.MainActivity.Companion.loginID
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        if(loginID !="0"){
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.getUserbyId(loginID)
            Toast.makeText(context, "Login ID : $loginID", Toast.LENGTH_SHORT).show()


            viewModel.user.observe(viewLifecycleOwner, Observer { response ->
                Toast.makeText(context, "nama"+response.name, Toast.LENGTH_SHORT).show()
                binding.nameTv.apply {
                    text = response.name
                }
            })
        }


        binding.loginfirstBtn.setOnClickListener {
            val myIntent = Intent(this.context,LoginActivity::class.java)
            startActivity(myIntent)
        }
        return binding.root
    }



}