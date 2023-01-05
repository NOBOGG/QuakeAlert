package com.owen.quakealert_owen.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.adapter.CommentAdapter
import com.owen.quakealert_owen.databinding.FragmentHomeBinding
import com.owen.quakealert_owen.model.DataX
import com.owen.quakealert_owen.model.SubmitGempa
import com.owen.quakealert_owen.view.MainActivity.Companion.loginID
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime

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
        createNotificationChannel()
        binding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(GempaViewModel::class.java)
        viewModel.getGempaTerkini()
        var dateTime = ""
        var dateTimeNow = LocalDateTime.now().toString()

        viewModel.gempaTerkini.observe(viewLifecycleOwner,Observer {
                response->
            dateTime = response.gempa.DateTime
            binding.magnitudeTv.apply {
                text = response.gempa.Magnitude
            }
            binding.wilayahTv.apply {
                text = response.gempa.Wilayah
            }
            binding.lintangTv.apply {
                text = response.gempa.Lintang
            }
            binding.bujurTv.apply {
                text = response.gempa.Bujur
            }
            binding.kedalamanTv.apply {
                text = response.gempa.Kedalaman
            }
            binding.potensiTv.apply {
                text = response.gempa.Potensi
            }
            binding.datetimeTv.apply {
                text = response.gempa.Tanggal
            }
            if(dateTime.equals(dateTimeNow)){
                sendNotification(response.gempa.Wilayah)
            }
        })



        binding.buttonshkCv.setOnClickListener {
            replaceFragment(ShakeMapFragment())
        }

        viewModelCom = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelCom.getUserbyId(loginID)
        viewModelCom.user.observe(viewLifecycleOwner, Observer {
            response->
            val status = response.status
            viewModelCom.getComment()
            viewModelCom.comment.observe(viewLifecycleOwner, Observer { response ->
                binding.homecommentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                Comadapter = CommentAdapter(response.data as ArrayList<DataX>,status)
                binding.homecommentRv.adapter = Comadapter

            })
        })
        viewModelCom.getComment()
        viewModelCom.comment.observe(viewLifecycleOwner, Observer { response ->
            val status = "guest"
            binding.homecommentRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            Comadapter = CommentAdapter(response.data as ArrayList<DataX>,status)
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

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Earthquake Alert"
            val descriptionText = "Notification for Earthquake Alert"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Earthquake Alert",name,importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = requireActivity().getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(Text:String){
        val builder = NotificationCompat.Builder(requireContext(),"Earthquake Alert")
            .setSmallIcon(R.drawable.home_logo)
            .setContentTitle("Earthquake Alert")
            .setContentText(Text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(requireContext())){
            notify(1,builder.build())
        }

    }



}