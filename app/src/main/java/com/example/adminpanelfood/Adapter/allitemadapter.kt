package com.example.adminpanelfood.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanelfood.databinding.ItemsofallBinding

class allitemadapter(private val menuitemname : ArrayList<String>,private val menuitemprice :
ArrayList<String>,private val menuitemimage : ArrayList<Int>):
    RecyclerView.Adapter<allitemadapter.itemViewHolder>() {

    private val itemquantities = IntArray(menuitemname.size) { 1 }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): allitemadapter.itemViewHolder {
        val binding = ItemsofallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return itemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: allitemadapter.itemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuitemname.size
    inner class itemViewHolder(private val bindinng: ItemsofallBinding) :
        RecyclerView.ViewHolder(bindinng.root) {
        fun bind(i: Int) {
            bindinng.apply {
                textView14.text = menuitemname[i]
                textView18.text = menuitemprice[i]
                imageView5.setImageResource(menuitemimage[i])

                button2.setOnClickListener {
                    decreaseQuantity(i)
                }
                button5.setOnClickListener {
                    increaseQuantity(i)
                }
                imageButton.setOnClickListener {
                    deleteitem(i)
                }
            }
        }

        private fun deleteitem(i: Int) {
            menuitemname.removeAt(i)
            menuitemprice.removeAt(i)
            menuitemimage.removeAt(i)
            notifyItemRemoved(i)
            notifyItemRangeChanged(i, menuitemname.size)

        }

        private fun increaseQuantity(i: Int) {
            if (itemquantities[i] < 10) {
                itemquantities[i]++
                bindinng.textView19.text = itemquantities[i].toString()
            }
        }

        private fun decreaseQuantity(i: Int) {
            if (itemquantities[i] > 1) {
                itemquantities[i]--
                bindinng.textView19.text = itemquantities[i].toString()
            }

        }
    }
}

