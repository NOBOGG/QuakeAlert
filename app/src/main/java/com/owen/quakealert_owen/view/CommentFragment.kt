package com.owen.quakealert_owen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.adapter.CommentAdapter
import com.owen.quakealert_owen.adapter.HistoryQuakeAdapter
import com.owen.quakealert_owen.databinding.FragmentCommentBinding
import com.owen.quakealert_owen.model.Comment
import com.owen.quakealert_owen.model.DataX
import com.owen.quakealert_owen.model.Gempa
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CommentFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentCommentBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var Commentadapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommentBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getComment()

        viewModel.comment.observe(viewLifecycleOwner, Observer { response ->
            binding.commentRv.layoutManager = LinearLayoutManager(context)
            Commentadapter = CommentAdapter(response.data as ArrayList<DataX>)
            binding.commentRv.adapter = Commentadapter

        })



        return binding.root
    }


}