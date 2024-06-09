package UDP1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame {
    public MyFrame(String name) {
        this.setTitle("UDP-chat " + name);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                // Your custom logic here
                // For example, ask the user if they want to save before closing
                // If yes, close the window; if no, abort the close process
                System.out.println("exiting.... \n");
                try {
                    MultigramSender ms = new MultigramSender(12543, name + " har kopplat ner.");
                } catch (IOException e) {
                    System.out.println("error"+ e);
                };
                int port = 12542;
                try{
                    MultigramSender mg = new MultigramSender(port, name);
                }catch(IOException e){
                    System.out.println("error"+ e);
                }
                dispose();
            }
        });
        setVisible(true);
    }    
};

