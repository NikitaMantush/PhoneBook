package com.mantushnikita.phonebook.ui

import android.content.IntentFilter
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import com.mantushnikita.phonebook.R
import com.mantushnikita.phonebook.reciever.CallReceiver
import com.mantushnikita.phonebook.ui.list.PhoneBookFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val receiver = CallReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(receiver, IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED))
        supportFragmentManager.beginTransaction().replace(R.id.container, PhoneBookFragment()).commit()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}