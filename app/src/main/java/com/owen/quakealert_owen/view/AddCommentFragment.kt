package com.owen.quakealert_owen.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.FragmentAddCommentBinding
import com.owen.quakealert_owen.model.SubmitComment
import com.owen.quakealert_owen.retrofit.EndPointApi
import com.owen.quakealert_owen.view.MainActivity.Companion.loginID
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddCommentFragment : Fragment() {

    private lateinit var binding: FragmentAddCommentBinding
    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddCommentBinding.inflate(layoutInflater)


        //create comment
        binding.addcommentBtn.setOnClickListener {
            val comment = binding.commentEdittext.text.toString().trim()
            if (comment.isEmpty()) {
                binding.commentEdittext.error = "Comment is required"
                binding.commentEdittext.requestFocus()
                return@setOnClickListener
            }else {
//            val id = binding.commentEdittext.text.toString()
                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                if (loginID != 0) {
                    viewModel.getUserbyId(loginID)
                    viewModel.user.observe(viewLifecycleOwner, Observer { response ->
                        val id = response.id.toString()
                        viewModel.createComment(comment, id)
                            .enqueue(object : retrofit2.Callback<SubmitComment> {
                                override fun onResponse(
                                    call: Call<SubmitComment>,
                                    response: retrofit2.Response<SubmitComment>
                                ) {
                                    if (response.isSuccessful) {
                                        val myIntent = Intent(context, MainActivity::class.java)
                                        startActivity(myIntent)
                                        Toast.makeText(
                                            context,
                                            "Comment telah terbuat",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Comment tidak berhasil terbuat",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<SubmitComment>, t: Throwable) {
                                    Log.d("TAG", "onFailure: ${t.message}")
                                }
                            })
                    })
                } else {
                    Toast.makeText(context, "Login terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            }
        }

            return binding.root
    }


}