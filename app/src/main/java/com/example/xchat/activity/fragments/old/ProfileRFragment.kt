package com.example.xchat.activity.fragments.old

import android.content.Context
import android.hardware.ConsumerIrManager
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.xchat.R
import com.example.xchat.activity.server.AcIrSender
import com.example.xchat.databinding.FragmentProfileRBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@RequiresApi(Build.VERSION_CODES.O)
class ProfileRFragment : Fragment() {

    private var _binding: FragmentProfileRBinding? = null
    private val binding get() = _binding!!
    private lateinit var projectionManager: MediaProjectionManager
    private var irManager: ConsumerIrManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        irManager = requireContext()
            .getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileRBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*projectionManager =
            requireContext().getSystemService(Context.MEDIA_PROJECTION_SERVICE)
                    as MediaProjectionManager*/

        binding.button.setOnClickListener {
            Log.d("testtest","1")
            if (irManager == null) {
                Toast.makeText(requireContext(),
                    "IR Service Not Available",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Log.d("testtest","2")
            if (!irManager!!.hasIrEmitter()) {
                Toast.makeText(requireContext(),
                    "This device does NOT have IR blaster",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Log.d("testtest","3")

            //sendSamsungPower()
            sendPowerOn()
            Log.d("testtest","last")

        }


       /* binding.button.setOnClickListener {
            //openDialog()
            captureLauncher.launch(projectionManager.createScreenCaptureIntent())
        }

        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(broadcastReceiver, IntentFilter("MIRROR_URL_READY"))*/

        editTextView()

    }
    /*private val captureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val intent = Intent(requireContext(), ScreenMirrorService::class.java).apply {
                    putExtra("CODE", result.resultCode)
                    putExtra("DATA", result.data)
                }

                requireContext().startForegroundService(intent)
            }
        }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val url = intent?.getStringExtra("URL")
            binding.description.text = "Mirroring at: $url"
            binding.description.setTextColor(Color.GREEN)
        }
    }*/

    private fun sendSamsungPower() {
        Log.d("testtest","4")
        if (irManager == null || !irManager!!.hasIrEmitter()) {
            Toast.makeText(requireContext(),
                "IR Blaster Not Available",
                Toast.LENGTH_LONG).show()
            return
        }
        Log.d("testtest","5")
        val pattern = buildSamsungPowerPattern()

        // Send twice (important for Samsung power)
        repeat(2) {
            Log.d("testtest","6")
            irManager!!.transmit(38000, pattern)
            Thread.sleep(120)
        }
    }


    private fun sendPowerOn() {

        val pattern = buildSamsungPowerPattern()

        AcIrSender.send(requireContext(), pattern)
    }

    private fun buildSamsungPowerPattern(): IntArray {

        val address = 0xE0E0
        val command = 0x40BF
        val data = (address shl 16) or command

        val pattern = mutableListOf<Int>()

        // NEC Header
        pattern.add(9000)
        pattern.add(4500)

        for (i in 31 downTo 0) {
            pattern.add(560)

            if ((data shr i) and 1 == 1) {
                pattern.add(1690)
            } else {
                pattern.add(560)
            }
        }

        // End pulse
        pattern.add(560)

        return pattern.toIntArray()
    }
    private fun showNumberLeft(value:Int):Int{
        return 16-value
    }

    private fun editTextView(){
        binding.userNameE.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                val typedLength = s?.length ?: 0
                binding.number.text = showNumberLeft(typedLength).toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // not needed
            }
        })
    }

    private fun openDialog(){
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_loading, null)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .setCancelable(false)
            .create()

        dialog.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0.8f)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            //findNavController().navigate(R.id.action_profileRFragment_to_homeFragment)
        }, 2800)

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}