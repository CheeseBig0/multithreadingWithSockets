package com.example;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocketClientExample {
	
	
	/*
	 * Modify this example so that it opens a dialogue window using java swing, 
	 * takes in a user message and sends it
	 * to the server. The server should output the message back to all connected clients
	 * (you should see your own message pop up in your client as well when you send it!).
	 *  We will build on this project in the future to make a full fledged server based game,
	 *  so make sure you can read your code later! Use good programming practices.
	 *  ****HINT**** you may wish to have a thread be in charge of sending information 
	 *  and another thread in charge of receiving information.
	*/
 
    //this just sends a message and quits
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        //Socket socket = null;
        //ObjectOutputStream oos = null;
        //ObjectInputStream ois = null;
        
        
        JFrame f=new JFrame("Client");
        JTextArea messageBox = new JTextArea();
	    JButton exitButton=new JButton("Close");  
        JButton sendButton=new JButton("Send"); 

	    //JLabel example = new JLabel(Double.toString(Math.PI));
	    //f.add(example);
        f.add(messageBox);
        f.add(sendButton);
	    f.add(exitButton);
	    f.setSize(300,300);  
	    f.setLayout(new GridLayout(4, 1));  
	    f.setVisible(true);

        
        //establish socket connection to server and write to socket
        Socket socket = new Socket(host.getHostName(), 9876);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");


        //Scanner scan = new Scanner(System.in);
        //System.out.println("Enter message:");
        //oos.writeObject( scan.nextLine() );
        
        //read server response
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        
        //close resources
        //scan.close();
        
        //Thread.sleep(100);

        sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                try {
                    if(messageBox.getText().length() > 0) {
                        System.out.println("sent message: " + messageBox.getText());
                        oos.writeObject( messageBox.getText() );
                        oos.flush();
                        messageBox.setText("");
                    }
                } catch (Exception error) {

                }
			}
		});


        exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                try {
                    ois.close();
                    oos.close();
                    socket.close();
                }
                catch (Exception error) {

                }
				
			}
		});

        
        
       
    }

public static class ThreadRunner extends Thread {

public void run() {
    
}
}

}