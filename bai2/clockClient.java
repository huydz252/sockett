package bai2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clockClient {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        JLabel timeLabel = new JLabel();

        frame.add(timeLabel);
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 5000);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    output.println("time");
                    String time = input.readLine();
                    timeLabel.setText(time);

                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        timer.start();
    }
}
