package UDP1;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import java.util.ArrayList;
import java.io.IOException;



public class MultiClient {
    private MyFrame frame;
    private JPanel listsPanel;    
    private JTextField textField;
    private DefaultListModel<String> messagesListModel;
    private DefaultListModel<String> namesListModel;
    private JList<String> listMessages;
    private JList<String> listNames;
    private String name;
    Thread namesInReceiver;
    Thread namesOutReceiver;
    Thread messageReceiver;

    public void disconnect(){
                System.out.println("exiting.... \n");
                try {
                    MultigramSender ms = new MultigramSender(12543, name + " har kopplat ner.");
                } catch (IOException e) {
                    System.out.println("error"+ e);
                };
                System.out.printf(name);
                int port = 12542;
                try{
                    MultigramSender mg = new MultigramSender(port, name);
                }catch(IOException e){
                    System.out.println("error"+ e);
                }
                System.exit(0);
    };

    public MultiClient(String name) throws IOException {
        this.name = name; 
        System.out.println("multiclient"); 
        System.out.println("name" + this.name);
        frame = new MyFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JButton disconnectButton = new JButton("Koppla ner. "); 
        frame.add(disconnectButton, BorderLayout.NORTH);

        disconnectButton.addActionListener(e -> disconnect());

        // Create text input field
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        textField = new JTextField("", 20);
        inputPanel.add(textField, BorderLayout.CENTER);
        // Create button
        JButton okButton = new JButton("skicka");
        inputPanel.add(okButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Create list
        messagesListModel = new DefaultListModel<>();
        listMessages = new JList<>(messagesListModel);
        frame.add(new JScrollPane(listMessages), BorderLayout.CENTER);
        
        listsPanel = new JPanel();
        listsPanel.setLayout(new BorderLayout());

        frame.add(listsPanel, BorderLayout.EAST);

        
        namesListModel = new DefaultListModel<>();//lista av alla användare
        listNames = new JList<>(namesListModel);//GUI namnlista
        listsPanel.add(new JScrollPane(listNames), BorderLayout.CENTER);
        JLabel label = new JLabel("Delatagare: ");
        listsPanel.add(label, BorderLayout.NORTH);



        //Receiving log in names, port 12541
        namesInReceiver = new Thread(new MultigramNameReceiver(12541, namesListModel, "add", name));
        namesInReceiver.start();
        //Receiving log out names, port 12542
        namesOutReceiver = new Thread(new MultigramNameReceiver(12542, namesListModel, "remove", name));
        namesOutReceiver.start();
        // Receiving user messages on port 12543
        messageReceiver = new Thread(new MultigramReceiver(12543, messagesListModel, "add", name));
        messageReceiver.start();
        
        //Send current user name to the socket port 12541 responsible for Login names and message that shows that an user has connected. 
        new MultigramSender(12541, name);
        new MultigramSender(12543, name + ": uppkopplad.");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText().trim();


                if (!inputText.isEmpty()) {

                    try{
                    MultigramSender mgs =  new MultigramSender(12543, name + ": "+ inputText);
                    }catch(IOException e1){
                        System.out.println(e1);
                    };

                    // if (!listModel.contains(inputText)) {
                    //     listModel.addElement(inputText);
                    // } else {
                    //     listModel.removeElement(inputText);
                    // }
                    textField.setText("");
                }
            }
        });
        // frame.add(okButton, BorderLayout.WEST);


        frame.pack();
        frame.getRootPane().setDefaultButton(okButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            
            String username = JOptionPane.showInputDialog("Ange ditt namn: ");
             if(username != null && !username.trim().isEmpty()){
                try {
                    MultiClient mc = new MultiClient(username);
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }       

             }else{
                JOptionPane.showMessageDialog(null, "Användarnamn krävs för att gå med in chatten.");
             }

        });
    }
}
