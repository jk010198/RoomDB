package com.infotechworld.roomdbdemo.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infotechworld.roomdbdemo.databinding.RowContactBinding
import com.infotechworld.roomdbdemo.model.ContactDetails

class ContactAdapter(
    private val list: List<ContactDetails>,
    val clickListener: (ContactDetails) -> Unit,
) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RowContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RowContactBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val pos = list[position]
            tvName.text = "Name :- ${pos.name}"
            tvNumber.text = "Mobile No :- ${pos.phone}"
            card.setOnClickListener({
                clickListener(list[position])
            })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}