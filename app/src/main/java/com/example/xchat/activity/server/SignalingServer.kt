package com.example.xchat.activity.server
/*
import org.java_websocket.server.WebSocketServer
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import java.net.InetSocketAddress

class SignalingServer(port: Int) :
    WebSocketServer(InetSocketAddress(port)) {

    var client: WebSocket? = null
    var listener: ((String) -> Unit)? = null

    override fun onOpen(conn: WebSocket, handshake: ClientHandshake) {
        client = conn
    }

    override fun onMessage(conn: WebSocket, message: String) {
        listener?.invoke(message)
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String, remote: Boolean) {}

    override fun onError(conn: WebSocket?, ex: Exception) {
        ex.printStackTrace()
    }

    override fun onStart() {}
}
*/