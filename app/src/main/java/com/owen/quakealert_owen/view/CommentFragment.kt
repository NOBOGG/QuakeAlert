package com.owen.quakealert_owen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.FragmentCommentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommentBinding.inflate(layoutInflater)



        return binding.root
    }


}