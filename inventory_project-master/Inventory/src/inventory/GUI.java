package inventory;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.table.DefaultTableModel;


public class GUI {
    public GUI() {
        Mainframe();
    }

    public void Mainframe() {
        //*****First Frame*****
        JFrame f = new JFrame("Inventory");
        f.setVisible(true);
        f.setSize(450, 500);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Buttons
        JButton demandTable = new JButton("Demand Table");
        JButton leadTable = new JButton("Lead Table");
        JButton FinalTable = new JButton("Final Table");
        JButton Analysis = new JButton("Analysis");


        // JButton Exit=new JButton("Exit");


        JPanel panel1 = new JPanel();
        f.add(panel1);
        panel1.setLayout((LayoutManager) null);


        //Customization for buttons
        demandTable.setBounds(10, 10, 400, 100);
        leadTable.setBounds(10, 120, 400, 100);
        FinalTable.setBounds(10, 230, 400, 100);
        Analysis.setBounds(10, 340, 400, 100);

        // Exit.setBounds(110, 420, 150, 25);


        panel1.add(demandTable);
        panel1.add(leadTable);
        panel1.add(FinalTable);
        panel1.add(Analysis);
        // panel1.add(Exit);
        f.add(panel1);
        f.setVisible(true);
        demandTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Demand d=new Demand();

                f.setVisible(false);

            }
        });
        leadTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lead d=new lead();

                f.setVisible(false);

            }
        });


        FinalTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Big b=new Big();

                f.setVisible(false);

            }
        });
        Analysis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Analisys d=new Analisys();

                f.setVisible(false);

            }
        });

    }


}


