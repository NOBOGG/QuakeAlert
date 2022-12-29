package com.owen.quakealert_owen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.owen.quakealert_owen.adapter.HistoryQuakeAdapter
import com.owen.quakealert_owen.databinding.FragmentHistoryBinding
import com.owen.quakealert_owen.model.Gempa
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModel: GempaViewModel
    private lateinit var historyQuakeAdapter: HistoryQuakeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentHistoryBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(GempaViewModel::class.java)
        viewModel.getGempaHistory()

        viewModel.gempaDirasakan.observe(viewLifecycleOwner, Observer { response ->
            binding.historyquakeRv.layoutManager = LinearLayoutManager(context)
            historyQuakeAdapter = HistoryQuakeAdapter(response.gempa as ArrayList<Gempa>)
            binding.historyquakeRv.adapter = historyQuakeAdapter

        })

        return binding.root
    }

}