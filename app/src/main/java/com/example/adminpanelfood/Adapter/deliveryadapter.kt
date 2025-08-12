package com.example.adminpanelfood.Adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.hardware.biometrics.BiometricManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfood.databinding.OutdeliverlayoutBinding

class deliveryadapter(private val customernames : ArrayList<String>,private val moneystate : ArrayList<String>) : RecyclerView.Adapter<deliveryadapter.DeliiveryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): deliveryadapter.DeliiveryViewHolder {
        val binding = OutdeliverlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DeliiveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: deliveryadapter.DeliiveryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = customernames.size
    inner class DeliiveryViewHolder(val binding : OutdeliverlayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(i: Int) {
            binding.apply {
                textView21.text = customernames[i]
                textView25.text = moneystate[i]

                val colourmap = mapOf(
                    "recieved" to Color.GREEN,"Not Recieved" to Color.RED,"Pending" to Color.GRAY)
                textView25.setTextColor(colourmap[moneystate[i]]?: Color.BLACK)
                statuscolor.backgroundTintList = ColorStateList.valueOf(colourmap[moneystate[i]]?: Color.BLACK)
            }
        }

    }
}

