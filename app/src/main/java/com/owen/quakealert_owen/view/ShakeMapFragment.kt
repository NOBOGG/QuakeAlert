package com.owen.quakealert_owen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.FragmentShakeMapBinding
import com.owen.quakealert_owen.helper.Const
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShakeMapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ShakeMapFragment : Fragment() {

    private lateinit var binding: FragmentShakeMapBinding
    private lateinit var viewModel: GempaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentShakeMapBinding.inflate(layoutInflater)
        binding.backgroundLoad.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(GempaViewModel::class.java)
        viewModel.getGempaTerkini()

        viewModel.gempaTerkini.observe(viewLifecycleOwner, Observer { response ->
            binding.backgroundLoad.visibility = View.INVISIBLE
            Glide.with(this)
                .load(Const.IMG_URL + response.gempa.Shakemap)
                .into(binding.shakemapImg)
        })

        binding.backtohomeBtn.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }


}