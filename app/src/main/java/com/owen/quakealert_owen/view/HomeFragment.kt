package com.owen.quakealert_owen.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.adapter.CommentAdapter
import com.owen.quakealert_owen.databinding.FragmentHomeBinding
import com.owen.quakealert_owen.model.DataX
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: GempaViewModel
    private lateinit var viewModelCom: UserViewModel
    private lateinit var Comadapter: CommentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(GempaViewModel::class.java)
        viewModel.getGempaTerkini()

        viewModel.gempaTerkini.observe(viewLifecycleOwner,Observer {
                response->
            binding.magnitudeTv.apply {
                text = response.gempa.get(0).Magnitude
//                Log.e("tes",response.Magnitude)
//                println("tes")
                println(response.gempa.get(0).Magnitude)
            }
            binding.wilayahTv.apply {
                text = response.gempa.get(0).Wilayah
            }
            binding.lintangTv.apply {
                text = response.gempa.get(0).Lintang
            }
            binding.bujurTv.apply {
                text = response.gempa.get(0).Bujur
            }
            binding.kedalamanTv.apply {
                text = response.gempa.get(0).Kedalaman
            }
            binding.potensiTv.apply {
                text = response.gempa.get(0).Coordinates
            }
            binding.datetimeTv.apply {
                text = response.gempa.get(0).Tanggal
            }
        })

//        binding.buttonshkCv.setOnClickListener {
//            replaceFragment(ShakeMapFragment())
//        }

        viewModelCom = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelCom.getComment()

        viewModelCom.comment.observe(viewLifecycleOwner, Observer { response ->
            binding.homecommentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            Comadapter = CommentAdapter(response.data as ArrayList<DataX>)
            binding.homecommentRv.adapter = Comadapter

        })

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }



}