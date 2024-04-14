package com.mantushnikita.phonebook.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.mantushnikita.phonebook.model.Contact
import com.mantushnikita.phonebook.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneBookViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    val list = MutableLiveData<List<Contact>>()

    private val observerList = Observer<List<Contact>>{
        list.value = it
    }

    init {
        contactRepository.listContact.observeForever(observerList)
    }

    override fun onCleared() {
        super.onCleared()
        contactRepository.listContact.removeObserver(observerList)
    }


    fun loadContacts(){
        contactRepository.getAllContacts()
    }

}