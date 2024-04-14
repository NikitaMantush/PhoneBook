package com.mantushnikita.phonebook.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mantushnikita.phonebook.R
import com.mantushnikita.phonebook.databinding.FragmentListBinding
import com.mantushnikita.phonebook.model.Contact
import com.mantushnikita.phonebook.reciever.CallReceiver
import com.mantushnikita.phonebook.ui.contact.AddContactFragment
import com.mantushnikita.phonebook.ui.list.adapter.PhoneBookAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneBookFragment: Fragment() {

    private var binding: FragmentListBinding?= null

    private val viewModel: PhoneBookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_item_decorator, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        binding?.run {
            recyclerViewContacts.addItemDecoration(dividerItemDecoration)
            addImageButton.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AddContactFragment()).commit()
            }
        }
        viewModel.list.observe(viewLifecycleOwner) { list ->
            setListContact(list)
        }
        viewModel.loadContacts()
    }

    private fun setListContact(listContact: List<Contact>) {
        binding?.recyclerViewContacts?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = PhoneBookAdapter()
            }
            (adapter as? PhoneBookAdapter)?.submitList(listContact)
        }
    }
}