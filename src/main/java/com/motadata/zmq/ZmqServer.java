package com.motadata.zmq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import java.time.Duration;
import java.util.concurrent.locks.LockSupport;

public class ZmqServer
{

    public static void main(String[] args) throws InterruptedException {
        try {
            //String port = "9449";
            String port = args[0];

            //String topic = "DG0001";
            String topic = args[1];

            //String command = "Test";
            String command = args[2];
            // Create a ZMQ context
            ZContext context = new ZContext();

            // Create a ZMQ publisher socket
            ZMQ.Socket socket = context.createSocket(ZMQ.PUB);

            // Bind the socket to an address
            socket.bind("tcp://*:"+port);
            while (true) {
                ZMsg message = new ZMsg();
                message.add(topic);
                message.add(command);
                message.send(socket);
                System.out.println("Msg sent for topic : " + topic + ", command : " + command);
                Thread.sleep(5000);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
