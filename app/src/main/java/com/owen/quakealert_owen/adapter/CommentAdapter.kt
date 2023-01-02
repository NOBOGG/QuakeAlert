package com.owen.quakealert_owen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.RecyclerView
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.CommentsCardviewBinding
import com.owen.quakealert_owen.model.Comment
import com.owen.quakealert_owen.model.DataX
import com.owen.quakealert_owen.model.SubmitComment
import com.owen.quakealert_owen.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentAdapter(private val dataSet: ArrayList<DataX>) :

    RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

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
        viewHolder.binding.commentcardTv.text = dataSet[position].user_id.toString()

        //delete comment
        viewHolder.binding.trashBtn.setOnClickListener {
            //delete

            viewModel = ViewModelProvider(ViewModelStore(), ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

            viewModel.deleteComment(dataSet[position].id).enqueue(object : Callback<SubmitComment> {
                override fun onResponse(call: Call<SubmitComment>, response: Response<SubmitComment>) {
                    if (response.isSuccessful){
                        Toast.makeText(viewHolder.itemView.context, "Comment Deleted", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(viewHolder.itemView.context, "Failed to delete comment", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SubmitComment>, t: Throwable) {
                    Toast.makeText(viewHolder.itemView.context, "Failed to delete comment", Toast.LENGTH_SHORT).show()
                }

            })
            dataSet.removeAt(position)
            notifyItemRemoved(position)

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}
