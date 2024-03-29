package com.owen.quakealert_owen.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.CommentsCardviewBinding
import com.owen.quakealert_owen.model.Comment
import com.owen.quakealert_owen.model.DataX
import com.owen.quakealert_owen.model.SubmitComment
import com.owen.quakealert_owen.model.Users
import com.owen.quakealert_owen.view.MainActivity
import com.owen.quakealert_owen.view.MainActivity.Companion.loginID
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class CommentAdapter(private val dataSet: ArrayList<DataX>, val status:String) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private lateinit var viewModel: UserViewModel

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CommentsCardviewBinding.bind(itemView)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.comments_cardview, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.namecardTv.text = dataSet[position].comment
        viewHolder.binding.commentcardTv.text = dataSet[position].name
        viewHolder.binding.trashBtn.visibility = View.GONE
        if (status == "admin") {
            viewHolder.binding.trashBtn.visibility = View.VISIBLE
        } else if (status == "member") {
            viewHolder.binding.trashBtn.visibility = View.INVISIBLE
        }else if (status == "guest"){
            viewHolder.binding.trashBtn.visibility = View.INVISIBLE
        }

                viewHolder.binding.trashBtn.setOnClickListener {
                    val alertDialog = AlertDialog.Builder(it.context)
                    alertDialog.apply {
                        setTitle("Konfirmasi")
                        setMessage("Apakah anda yakin untuk menghapus komen ini?")
                        setNegativeButton("Tidak", { dialogInterface, i -> dialogInterface.dismiss() })
                        setPositiveButton("Iya", { dialogInterface, i -> dialogInterface.dismiss()
                            val myIntent =
                                Intent(viewHolder.itemView.context, MainActivity::class.java).apply {
                                    putExtra("commentdel_id", dataSet[position].id)
                                }
                            viewHolder.itemView.context.startActivity(myIntent)
                            Toast.makeText(it.context, "Komen Berhasil Di Hapus", Toast.LENGTH_SHORT).show()
                        })
                        alertDialog.show()
                    }

                }
//            }
//        }


    }

    //delete comment
//        viewHolder.binding.trashBtn.setOnClickListener {
//            delete
//            cannot create an instance of class UserViewModel
//            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//viewModel = ViewModelProvider(ViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(viewHolder.itemView.context)).get(UserViewModel::class.java)
//            viewModel.deleteComment(dataSet[position].id).enqueue(object : Callback<SubmitComment> {
//                override fun onResponse(call: Call<SubmitComment>, response: Response<SubmitComment>) {
//                    if (response.isSuccessful){
//                        Toast.makeText(viewHolder.itemView.context, "Comment Deleted", Toast.LENGTH_SHORT).show()
//                    }else{
//                        Toast.makeText(viewHolder.itemView.context, "Failed to delete comment", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<SubmitComment>, t: Throwable) {
//                    Toast.makeText(viewHolder.itemView.context, "Failed to delete comment", Toast.LENGTH_SHORT).show()
//                }
//
//            })
//            dataSet.removeAt(position)
//            notifyItemRemoved(position)
//
//        }
//}

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}
