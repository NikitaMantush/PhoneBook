package com.mantushnikita.phonebook.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mantushnikita.phonebook.databinding.ItemContactBinding
import com.mantushnikita.phonebook.model.Contact

class PhoneViewHolder(
    private var binding: ItemContactBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        contact: Contact
    ) {
        binding.nameTextView.apply {
            text = contact.name

        }
        binding.phoneTextView.apply {
            text = contact.phone
        }
    }
}