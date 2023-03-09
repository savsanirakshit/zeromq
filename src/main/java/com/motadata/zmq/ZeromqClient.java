package com.motadata.zmq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.time.Duration;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ZeromqClient
{


    public static void main(String[] args) {
        // Create a ZMQ context
        ZContext context = new ZContext();

        // Create a ZMQ subscriber socket
        ZMQ.Socket socket = context.createSocket(ZMQ.SUB);

        // Connect the socket to the publisher address
        socket.connect("tcp://localhost:9559");

        // Subscribe to messages with the "topic" topic
        socket.subscribe("topic".getBytes());

        // Receive messages
        while (true) {
            String topic = socket.recvStr();
            String message = socket.recvStr();
            System.out.println("Received message with topic " + topic + " and message " + message);
        }
    }

}
