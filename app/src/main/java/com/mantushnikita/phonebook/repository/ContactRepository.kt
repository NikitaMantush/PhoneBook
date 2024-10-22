package com.mantushnikita.phonebook.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.snapshots
import com.mantushnikita.phonebook.model.Contact
import java.util.Date
import javax.inject.Inject

class ContactRepository @Inject constructor() {

    val listContact = MutableLiveData<List<Contact>>()

    fun saveContact(contact: Contact){
        Firebase.database.getReference("contact")
            .child("Nikita")
            .child("${Date().time}")
            .setValue(contact)

    }
    fun getAllContacts(): List<Contact>{
        Firebase.database.getReference("contact")
            .child("Nikita")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Contact>()
                    snapshot.children.forEach { dataSnapshot ->
                        val contact = dataSnapshot.getValue(Contact::class.java)
                        contact?.let { list.add(it) }
                    }
                    listContact.value = list
                    Log.e("getAllContacts", list.toString())
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return emptyList()
    }
}