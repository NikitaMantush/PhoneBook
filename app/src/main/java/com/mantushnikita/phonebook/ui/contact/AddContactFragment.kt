package com.mantushnikita.phonebook.ui.contact

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mantushnikita.phonebook.R
import com.mantushnikita.phonebook.databinding.FragmentAddContactBinding
import com.mantushnikita.phonebook.model.Contact
import com.mantushnikita.phonebook.ui.list.PhoneBookFragment
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.Pattern

@AndroidEntryPoint
class AddContactFragment : Fragment() {

    private var binding: FragmentAddContactBinding? = null

    private val viewModel: AddContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            buttonSave.setOnClickListener {
                val name = editTextName.text.toString()
                val phone = editTextPhone.text.toString()
                if (name.isEmpty()) {
                    editTextName.error = "Please enter a name"
                    return@setOnClickListener
                }
                if (phone.isEmpty()) {
                    editTextPhone.error = "Please enter a phone number"
                    return@setOnClickListener
                }
                if (!isValidPhoneNumber(phone)) {
                    editTextPhone.error = "Please enter a valid phone number"
                    return@setOnClickListener
                }
                viewModel.saveContact(
                    Contact(name, phone)
                )
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, PhoneBookFragment()).commit()
            }
            buttonBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, PhoneBookFragment()).commit()
            }
        }
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

}