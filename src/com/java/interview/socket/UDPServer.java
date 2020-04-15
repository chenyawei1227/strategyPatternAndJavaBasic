package com.java.interview.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Author: chenyawei
 * @Date: 2020-04-14  18:10
 * @Description:
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        //服务器端接受客户端发送的数据报
        DatagramSocket socket = new DatagramSocket(65001);//监听的端口号
        byte[] buff = new byte[100];//存储从客户端接受到的内容
        DatagramPacket packet = new DatagramPacket(buff,buff.length);
        //接受客户端发送过来的内容，并将内容封装进DatagramPacket对象中
        socket.receive(packet);

        byte[] data = packet.getData();//从DatagramPacket对象中获取到真正存储的数据
        //将数据从二进制转换成字符串形式
        String content = new String(data,0,packet.getLength());
        System.out.println(content);
        //将要发送给客户端的数据转换成二进制
        byte[] sendedContent = String.valueOf(content.length()).getBytes();
        //服务端给客户端发送数据报
        //从DatagramPacket对象中获取到数据端来源地址和端口号
        DatagramPacket packetToClient = new DatagramPacket(sendedContent,sendedContent.length,
                packet.getAddress(),packet.getPort());
        socket.send(packetToClient);
    }
}
