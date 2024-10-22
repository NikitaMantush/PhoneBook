package com.mantushnikita.phonebook.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mantushnikita.phonebook.databinding.ItemContactBinding
import com.mantushnikita.phonebook.model.Contact

class PhoneBookAdapter: ListAdapter<Contact, PhoneViewHolder>(object : DiffUtil.ItemCallback<Contact>(){
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return false
    }

}
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}