package com.mantushnikita.phonebook.ui.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mantushnikita.phonebook.model.Contact
import com.mantushnikita.phonebook.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    fun saveContact(contact: Contact) {
        contactRepository.saveContact(
            Contact(
                contact.name,
                contact.phone
            )
        )
    }
}