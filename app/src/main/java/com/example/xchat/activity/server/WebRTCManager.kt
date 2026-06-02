package com.example.xchat.activity.server
/*
import android.content.Context
import android.media.MediaRecorder
import android.media.projection.MediaProjection

class WebRTCManager(
    private val context: Context,
    private val projection: MediaProjection,
    private val signaling: SignalingServer
) {

    private lateinit var peerConnectionFactory: PeerConnectionFactory
    private lateinit var peerConnection: PeerConnection
    private lateinit var videoSource: MediaRecorder.VideoSource
    private lateinit var videoTrack: VideoTrack

    fun start() {

        PeerConnectionFactory.initialize(
            PeerConnectionFactory.InitializationOptions.builder(context)
                .createInitializationOptions()
        )

        val options = PeerConnectionFactory.Options()
        peerConnectionFactory = PeerConnectionFactory.builder()
            .setOptions(options)
            .createPeerConnectionFactory()

        val eglBase = EglBase.create()

        videoSource = peerConnectionFactory.createVideoSource(false)

        val capturer = ScreenCapturerAndroid(
            projection,
            object : MediaProjection.Callback() {}
        )

        capturer.initialize(
            SurfaceTextureHelper.create("CaptureThread", eglBase.eglBaseContext),
            context,
            videoSource.capturerObserver
        )

        capturer.startCapture(720, 1280, 30)

        videoTrack = peerConnectionFactory.createVideoTrack("SCREEN_TRACK", videoSource)

        val rtcConfig = PeerConnection.RTCConfiguration(
            listOf(
                PeerConnection.IceServer.builder("stun:stun.l.google.com:19302").createIceServer()
            )
        )

        peerConnection = peerConnectionFactory.createPeerConnection(
            rtcConfig,
            object : PeerConnection.Observer {

                override fun onIceCandidate(candidate: IceCandidate) {
                    signaling.client?.send(
                        """{"type":"candidate","sdpMid":"${candidate.sdpMid}","sdpMLineIndex":${candidate.sdpMLineIndex},"candidate":"${candidate.sdp}"}"""
                    )
                }

                override fun onAddStream(stream: MediaStream?) {}
                override fun onDataChannel(dc: DataChannel?) {}
                override fun onIceConnectionReceivingChange(b: Boolean) {}
                override fun onIceConnectionChange(p0: PeerConnection.IceConnectionState?) {}
                override fun onIceGatheringChange(p0: PeerConnection.IceGatheringState?) {}
                override fun onSignalingChange(p0: PeerConnection.SignalingState?) {}
                override fun onRemoveStream(p0: MediaStream?) {}
                override fun onRenegotiationNeeded() {}
                override fun onAddTrack(receiver: RtpReceiver?, streams: Array<out MediaStream>?) {}
            }
        )!!

        peerConnection.addTrack(videoTrack)

        signaling.listener = { message ->
            handleSignal(message)
        }

        createOffer()
    }

    private fun createOffer() {
        peerConnection.createOffer(object : SdpObserver {
            override fun onCreateSuccess(desc: SessionDescription) {
                peerConnection.setLocalDescription(this, desc)
                signaling.client?.send("""{"type":"offer","sdp":"${desc.description}"}""")
            }

            override fun onSetSuccess() {}
            override fun onCreateFailure(p0: String?) {}
            override fun onSetFailure(p0: String?) {}
        }, MediaConstraints())
    }

    private fun handleSignal(message: String) {

        if (message.contains("answer")) {

            val sdp = message.substringAfter("\"sdp\":\"")
                .substringBeforeLast("\"")

            val session = SessionDescription(
                SessionDescription.Type.ANSWER,
                sdp
            )

            peerConnection.setRemoteDescription(object : SdpObserver {
                override fun onSetSuccess() {}
                override fun onCreateSuccess(p0: SessionDescription?) {}
                override fun onCreateFailure(p0: String?) {}
                override fun onSetFailure(p0: String?) {}
            }, session)
        }

        if (message.contains("candidate")) {
            // parse candidate properly in real project
        }
    }
}
*/