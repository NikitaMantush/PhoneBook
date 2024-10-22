package com.mantushnikita.phonebook.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("CallReceiver", "onReceive")
        if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            == TelephonyManager.EXTRA_STATE_OFFHOOK
        ) {
            Toast.makeText(context, "Phone Call is Started..", Toast.LENGTH_LONG).show()
        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            == TelephonyManager.EXTRA_STATE_IDLE
        ) {
            Toast.makeText(context, "Phone Call is Ended..", Toast.LENGTH_LONG).show()
        } else if (intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            == TelephonyManager.EXTRA_STATE_RINGING
        ) {
            Toast.makeText(context, "Incoming call..", Toast.LENGTH_LONG).show()
        }
    }
}