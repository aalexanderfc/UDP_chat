package UDP1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.InetAddress;
import javax.swing.SwingUtilities;
import javax.swing.DefaultListModel;

import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MultigramNameReceiver  implements Runnable {

    private MulticastSocket socket;
    private String ip;
    private InetAddress iadr; 
    private int port;
    private String user;
    private String mode; // "add" or "remove"
    InetSocketAddress group1;
    NetworkInterface netIf;
    DefaultListModel<String> listModel;
    


    public MultigramNameReceiver(int toPort,  DefaultListModel<String> mylistModel, String mode, String user) throws SocketException, IOException{
        
        ip = "234.234.236.238";
        iadr = InetAddress.getByName(ip);
        port = toPort;
        this.mode = mode; 
        this.user = user;
        listModel = mylistModel;
        System.out.println("multireciver");
        System.out.println("model"+listModel);

        group1 = new InetSocketAddress(iadr, port);
        NetworkInterface netIf = NetworkInterface.getByName("MacBook-Pro-som-tillhor-Alexander.local");

        socket = new MulticastSocket(port);
        socket.joinGroup(group1, netIf);
    }
    public void run() {       

        byte[] bytes = new byte[254];

        while (true) {
            DatagramPacket dgp = new DatagramPacket(bytes, bytes.length, 
            iadr, port);
            
            try{
                socket.receive(dgp);
            }catch(IOException e){
                break;
            }
            System.out.println("address" + dgp.getAddress());
            String s = new String(dgp.getData(), 0, dgp.getLength());
            SwingUtilities.invokeLater(() -> {
                if (this.mode == "add"){ 
                    // when new user join the group the current user needs to resend their name to port 12541 (login port)
                    if(!listModel.contains(s)){// the user in not present in the users list 
                        System.out.println("MuligramNameReceiver: new user joining.. resending login to user " + s);
                        try {
                            MultigramSender ms = new MultigramSender(12541, user);
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    } 
                    System.out.println("adding "+ s);               
                    StringCombineAndParse.AddToListModel(listModel, s);//Adding the new user to users list.
                }
                else{// some user is signing out. 
                    System.out.println(user +" removing "+ s);
                    StringCombineAndParse.RemoveFromListModel(listModel, s);
                }
                });
            System.out.print(user + " recieved: ");
            System.out.println(s);
            System.out.print("to port ");
            System.out.println(port);
            //sending log in again
        }

    }



    
}
