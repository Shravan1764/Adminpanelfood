package com.example.adminpanelfood.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfood.databinding.PendingordersBinding

class pendingadaper(private val customernames: ArrayList<String>, private val orderquantity: ArrayList<Int>
                ,private val image: ArrayList<Int>
            ,private val context: Context): RecyclerView.Adapter<pendingadaper.pendingviewholder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): pendingadaper.pendingviewholder {
        val binding =
            PendingordersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return pendingviewholder(binding)
    }

    override fun onBindViewHolder(holder: pendingadaper.pendingviewholder, position: Int) {
        holder.bind(position);
    }

    override fun getItemCount(): Int = customernames.size
    inner class pendingviewholder(val binding: PendingordersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isAccepted = false
        fun bind(i: Int) {
            binding.apply {
                textView31.text = customernames[i]
                textView33.text = orderquantity[i].toString()
                imageView18.setImageResource(image[i])

                acceptbutton.apply {
                    if (!isAccepted) {
                        text = "Accept"
                    } else {
                        text = "Dispatched"
                    }
                    setOnClickListener {
                        if (!isAccepted) {
                            text = "Dispatched"
                            isAccepted = true
                            shotoast("Order Accepted")
                        } else {
                            customernames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            shotoast("Order Dispatched")

                        }
                    }
                }
            }
        }
        fun shotoast(message: String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }
}