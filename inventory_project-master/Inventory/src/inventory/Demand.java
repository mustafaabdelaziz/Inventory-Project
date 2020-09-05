package inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class Demand {
    private int counter;
    private int d;
    private ArrayList<Integer> demand=new ArrayList<Integer>();
    private ArrayList<Integer> demandFrequency=new ArrayList<Integer>();
    private ArrayList<Float> demandProbability=new ArrayList<Float>();
    private ArrayList<Float> demandCumulativeProbability=new ArrayList<Float>();
    private ArrayList<String> demandIntervals =new ArrayList<String>();


   public Demand(){
       counter=d;
       demandGUI();




       /**System.out.println("Enter the number of elements");
       Scanner scan =new Scanner(System.in);
       counter=scan.nextInt();**/
   }

   public void getDemand(int dm)  {
       demand.add(dm);
       try {
           FileWriter writer = new FileWriter("Files/Demand.txt");
           for (int val : demand) {
               writer.write(val+"@");
           }
           writer.close();
       }
       catch (IOException e){}
   }

   public void getDemandFrequency(int fr){
       demandFrequency.add(fr);
       try{
           FileWriter writer =new FileWriter("Files/demandFrequency.txt");
           for (int val:demandFrequency){
               writer.write(val+"@");
           }
           writer.close();
       }
       catch (IOException e){}
   }

   public void getDemandProbability(){
       int sum=0;
       for(int val:demandFrequency){
           sum+=val;
       }
       for (int val:demandFrequency){
           demandProbability.add((float)val/sum);
       }
       try {
           FileWriter writer = new FileWriter("Files/demandProbability.txt");
           for(float val :demandProbability){
               writer.write(val+"@");
           }
           writer.close();
       }
       catch (IOException e){}
   }

   public void getDemandCumulativeProbability() {
       float sum =0;
       for (float val:demandProbability){
           sum +=val;
           demandCumulativeProbability.add(sum);
       }
       try {
           FileWriter writer = new FileWriter("Files/demandCumulativeProbability.txt");
           for(float val :demandCumulativeProbability){
               writer.write(val+"@");
           }
           writer.close();
           System.out.println("Printed Results");
       }
       catch(IOException e){}
   }

   public void getDemandIntervals(){
       float crinterval =0;
       float lsinterval=0;
       for(int i=0;i<demandCumulativeProbability.size();i++){
           crinterval =demandCumulativeProbability.get(i) * 100;

           if (i==0)
           {
               demandIntervals.add(01+"to"+(int) crinterval);
           }
           else
           {
               lsinterval =demandCumulativeProbability.get(i-1)*100;
               demandIntervals.add((int) lsinterval+1+"to"+(int) crinterval);
           }
       }
       try {
           FileWriter writer = new FileWriter("Files/DemandIntervals.txt");
           for (String val : demandIntervals) {
               writer.write(val + "@");
           }
           writer.close();
       }
       catch (IOException e){}
   }

   public void demandGUI() {
       JFrame f2 = new JFrame("Demand Table");
       f2.setVisible(true);
       f2.setSize(890, 500);
       f2.setLocationRelativeTo(null);
       f2.setResizable(false);
       f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ///////////////////
       JPanel panel = new JPanel();
       panel.setLayout(null);

       ///////////
       //JLabel lab2 = new JLabel(" Daily Demand Table");
      // lab2.setBounds(10, 10, 180, 30);

       // back
       JButton b2 = new JButton("Back");
       b2.setBounds(700, 10, 100, 30);
       b2.setVisible(true);
       panel.add(b2);
       //Table

       JScrollPane js2 = new JScrollPane();
       js2.setBounds(10, 50, 800, 150);
       f2.getContentPane().add(js2);
       f2.getContentPane().add(panel);
      // String[] columns2 = {"Demand ", "Frequency(Days)", "Probability", "Cumulative Probability", "Interval of Random Numbers"};
       JTable table2 = new JTable();
       //table.setBounds();

       js2.setViewportView(table2);
       DefaultTableModel tableModel2;
       tableModel2 = new DefaultTableModel();
       //tableModel2.setColumnIdentifiers(columns2);

       table2.setModel(tableModel2);
       ////
//*******labels***********************************************************************************
       JLabel demand=new JLabel("Demand");
       panel.add(demand);
       JLabel Frequency_Days=new JLabel("Frequency (Days");
       panel.add(Frequency_Days);

//******Textfields********************************************************************************
       JTextField demandtf=new JTextField();
       panel.add(demandtf);
       JTextField frequencytf=new JTextField();
       panel.add(frequencytf);

//******Buttons***********************************************************************************
       JButton demandbtn=new JButton("Submit");
       panel.add(demandbtn);
      // JButton frequencybtn=new JButton("Submit");panel.add(frequencybtn);
       JButton show=new JButton("Show table");
       panel.add(show);

//**********Customization for labels**************************************************************
      demand.setBounds(10, 250, 100, 25);
     Frequency_Days.setBounds(10, 300, 200, 25);

//**********Customization for textfields***********************************************************
       demandtf.setBounds(150, 250, 150, 25);
       frequencytf.setBounds(150, 300, 150, 25);
//**********Customization for buttons**************************************************************
       demandbtn.setBounds(150, 350, 150, 25);
      // frequencybtn.setBounds(310, 300, 150, 25);
       show.setBounds(300,400,200,25);
///////////////////////////////////
       panel.add(js2);
       f2.add(panel);
       f2.setVisible(true);


    demandbtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   if (demandtf.getText().equals("") &&frequencytf.getText().equals(""));

                   d = Integer.parseInt(demandtf.getText());
                   getDemand(d);
                   int fr = Integer.parseInt(frequencytf.getText());
                   getDemandFrequency(fr);
                   System.out.println(demandFrequency);
                   frequencytf.setText(null);
                  demandtf.setText(null);

                   JOptionPane.showMessageDialog(null, "you add Successfully "+d+" Demand and frequency");
               }catch(NumberFormatException h){
                   JOptionPane.showMessageDialog(null, "Please enter Number of demand and frequency");
               }



           }
       });
       b2.addActionListener(new ActionListener() {


           public void actionPerformed(ActionEvent e) {

               f2.setVisible(false);
               GUI f=new GUI();


           }
       });

      /** frequencybtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {

               try {
                  //String got = frequencytf.getText();
                   //StringTokenizer br= new StringTokenizer(got,"@");
                   //while(br.hasMoreElements()){
                     //  demandFrequency.add((Integer.parseInt(br.nextToken())));

                 //  }
                   int fr = Integer.parseInt(frequencytf.getText());
                   getDemandFrequency(fr);
                   System.out.println(demandFrequency);
                   JOptionPane.showMessageDialog(null, "Succefully");
               }catch(NumberFormatException h){
                   JOptionPane.showMessageDialog(null, "error");
               }
           }

       });*/

      show.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               tableModel2.setColumnCount(0);

               getDemandProbability();
               getDemandCumulativeProbability();
               getDemandIntervals();
               try {
                   FileInputStream fis = new FileInputStream("Files/Demand.txt");
                   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                   String line;
                   while ((line = br.readLine()) != null) {
                       tableModel2.addColumn("Demand",line.split("@"));

                   }


               } catch (IOException j) {
                   // JOptionPane.showMessageDialog(null, "Error");
                   j.printStackTrace();
               }
               try {
                   FileInputStream fis = new FileInputStream("Files/demandFrequency.txt");
                   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                   String line;
                   while ((line = br.readLine()) != null) {
                       tableModel2.addColumn("Frequency",line.split("@"));
                   }


               } catch (IOException j) {
                   // JOptionPane.showMessageDialog(null, "Error");
                   j.printStackTrace();
               }
               try {
                   FileInputStream fis = new FileInputStream("Files/demandProbability.txt");
                   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                   String line;
                   while ((line = br.readLine()) != null) {
                       tableModel2.addColumn("Probability",line.split("@"));
                   }


               } catch (IOException j) {
                   // JOptionPane.showMessageDialog(null, "Error");
                   j.printStackTrace();
               }
               try {
                   FileInputStream fis = new FileInputStream("Files/demandCumulativeProbability.txt");
                   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                   String line;
                   while ((line = br.readLine()) != null) {
                       tableModel2.addColumn("Cumulative",line.split("@"));
                   }


               } catch (IOException j) {
                   // JOptionPane.showMessageDialog(null, "Error");
                   j.printStackTrace();
               }

               try {
                   FileInputStream fis = new FileInputStream("Files/DemandIntervals.txt/");
                   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                   String line;
                   while ((line = br.readLine()) != null) {
                       tableModel2.addColumn("IntervalsNUM",line.split("@"));
                   }


               } catch (IOException j) {
                   // JOptionPane.showMessageDialog(null, "Error");
                   j.printStackTrace();
               }

           }
       });
   }
}
