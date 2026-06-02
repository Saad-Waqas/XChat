package com.example.xchat.activity.server

import android.content.Context
import android.hardware.ConsumerIrManager
import android.widget.Toast

object AcIrSender {

    private const val FREQUENCY = 38000

    fun send(context: Context, pattern: IntArray) {

        val irManager = context
            .getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?

        if (irManager == null || !irManager.hasIrEmitter()) {
            Toast.makeText(context,
                "IR Blaster Not Available",
                Toast.LENGTH_LONG).show()
            return
        }

        irManager.transmit(FREQUENCY, pattern)
    }
}
