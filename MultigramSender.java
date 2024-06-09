package UDP1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;

public class MultigramSender{
    
    public  MultigramSender(int port, String mess) throws IOException, UnknownHostException, SocketException{

        String ip = "234.234.236.238";
        InetAddress iadr = InetAddress.getByName(ip);
        int toPort1 = port;

        InetSocketAddress group1 = new InetSocketAddress(iadr, toPort1);
        NetworkInterface netIf = NetworkInterface.getByName("MacBook-Pro-som-tillhor-Alexander.local");

        MulticastSocket socket1 = new MulticastSocket(toPort1);
        socket1.joinGroup(group1, netIf);


        byte[] bytes = mess.getBytes();
        DatagramPacket dgp = new DatagramPacket(bytes, 
        bytes.length, iadr, toPort1);
        socket1.send(dgp);
        socket1.close();
        System.out.println("sending " + mess +" to port "+port);//For testing and debugging.
    }

    public static void main(String[] args) throws IOException, UnknownHostException, SocketException{
        MultigramSender ds = new MultigramSender(12541, "Hej");
    }
    
}
