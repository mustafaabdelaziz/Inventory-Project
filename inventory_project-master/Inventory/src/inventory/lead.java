package inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

import java.util.StringTokenizer;

public class lead {
    private ArrayList<Integer> lead=new ArrayList<Integer>();
    private ArrayList<Float> leadProbability=new ArrayList<Float>();
    private ArrayList<Float> leadCumulativeProbability=new ArrayList<Float>();
    private ArrayList<String> leadIntervals =new ArrayList<String>();
    private ArrayList<Integer> leadFrequency=new ArrayList<Integer>();
    private int weeks,d;
    private String got;
    public JTextField daysstf;
    private int i,x;
    private int Start=1;

   public lead() {
        weeks=d;
        LeadGUI();
           /**System.out.println("Enter the number of elements");
            Scanner scan =new Scanner(System.in);
            counter=scan.nextInt();**/
       }

  public void getleadtime(int dm)  {
      lead.add(dm);
      try {
          FileWriter writer = new FileWriter("Files/leadtime.txt");
          for (int val : lead) {
              writer.write(val+"@");
          }
          writer.close();
      }
      catch (IOException e){}
  }

    public void getFrequency(int fr){
        leadFrequency.add(fr);
        try{
            FileWriter writer =new FileWriter("Files/leadFrequency.txt");
            for (int val:leadFrequency){
                writer.write(val+"@");
            }
            writer.close();
        }
        catch (IOException e){}
    }

    public void getleadProbability(){
        int sum=0;
        for(int val:leadFrequency){
            sum+=val;
        }
        for (int val:leadFrequency){
            leadProbability.add((float)val/sum);
        }
        try {
            FileWriter writer = new FileWriter("Files/leadprobability.txt");
            for(float val :leadProbability){
                writer.write(val+"@");
            }
            writer.close();
        }
        catch (IOException e){}
    }

    public void getleadCumulativeProbability() {
        float sum =0;
        for (float val:leadProbability){
            sum +=val;
            leadCumulativeProbability.add(sum);
        }
        try {
            FileWriter writer = new FileWriter("Files/leadCumulativeProbability.txt");
            for(float val :leadCumulativeProbability){
                writer.write(val+"@");
            }
            writer.close();
            System.out.println("Printed Results");
        }
        catch(IOException e){}
    }

    public void getleadIntervals(){
        float crinterval =0;
        float lsinterval=0;
        for(int i=0;i<leadCumulativeProbability.size();i++){
            crinterval =leadCumulativeProbability.get(i) * 100;

            if (i==0)
            {
                leadIntervals.add(01+"to"+(int) crinterval);
            }
            else
            {
                lsinterval =leadCumulativeProbability.get(i-1)*100;
                leadIntervals.add((int) lsinterval+1+"to"+(int) crinterval);
            }
        }
        try {
            FileWriter writer = new FileWriter("Files/leadIntervals.txt");
            for (String val : leadIntervals) {
                writer.write(val + "@");
            }
            writer.close();
        }
        catch (IOException e){}
    }

    public void LeadGUI(){
        JFrame f3 = new JFrame("Lead Time Table");
        f3.setVisible(true);
        f3.setSize(890, 500);
        f3.setLocationRelativeTo(null);
        f3.setResizable(false);
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ///////////////////
        JPanel pDM3 = new JPanel();
        pDM3.setLayout(null);



        ///////////
        JLabel lab3 = new JLabel(" Lead Time Table");
        lab3.setBounds(10, 10, 180, 30);

        JLabel frequencyO = new JLabel("Frequency: ");
        frequencyO.setBounds(10, 290, 180, 30);
        pDM3.add(frequencyO);
        JLabel days = new JLabel("Lead Time(Days): ");
        days.setBounds(10, 250, 180, 30);
        pDM3.add(lab3);
        pDM3.add(frequencyO);
        pDM3.add(days);
        /////////////txt area
        daysstf = new JTextField();
        daysstf.setBounds(200, 250, 180, 30);
        JTextField forderstf = new JTextField();
        forderstf.setBounds(200, 290, 180, 30);


        pDM3.add(daysstf);
        pDM3.add(forderstf);

        // back
        JButton b3 = new JButton("<-");
        b3.setBounds(810, 10, 60, 30);
        b3.setVisible(true);
        ////Lead Time
        JButton b4 = new JButton("Submit");
        b4.setBounds(200, 360, 100, 30);
        b4.setVisible(true);
        ////Freq Submit
       // JButton b5 = new JButton("Submit");
        //b5.setBounds(400, 290, 100, 30);
       // b5.setVisible(true);
        /////////////Table button
        JButton b6 = new JButton("Show Table");
        b6.setBounds(300, 400, 200, 30);
        b6.setVisible(true);
        pDM3.add(b3);
        pDM3.add(b6);
        pDM3.add(b4);

        //Table

        JScrollPane js3 = new JScrollPane();
        js3.setBounds(10, 50, 800, 150);
        f3.getContentPane().add(js3);
        f3.getContentPane().add(pDM3);
        //String[] columns3 = {"Lead Time(Days) ", "Frequency(Order)", "Probability", "Cumulative Probability", "Interval of Random Numbers"};
        JTable table3 = new JTable();

        js3.setViewportView(table3);
        DefaultTableModel tableModel3;
        tableModel3 = new DefaultTableModel(0, 0);
        //  tableModel3.setColumnIdentifiers(columns3);
        table3.setModel(tableModel3);
        ////


        //pDM3.add(lab3);
        pDM3.add(b3);
        pDM3.add(js3);

        f3.add(pDM3);
        f3.setVisible(true);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Hna le 3dd ellead time awl 5na fe gdwl ellead time y3ni


                    if (forderstf.getText().equals("") &&daysstf.getText().equals(""));
                    d = Integer.parseInt(daysstf.getText());

                    getleadtime(d);
                    System.out.println(d);

                   int fr = Integer.parseInt(forderstf.getText());
                    getFrequency(fr);
                   // getDemandFrequency(fr);
                    System.out.println(fr);
                    forderstf.setText(null);
                    daysstf.setText(null);

                    JOptionPane.showMessageDialog(null, "you add Successfully "+d+" Demand and frequency");
                }catch(NumberFormatException h){
                    JOptionPane.showMessageDialog(null, "Please enter Number of demand and frequency");
                }



            }
        });
       b3.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                f3.setVisible(false);
                GUI f=new GUI();


            }
        });

//Da elgdwl eli hyt7t fe el7agat w elmfrod hy2ra mn files eli ht3mla 5ailk gad3 w ab2a 5od elUrl bta3 kol file w 7oto
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel3.setColumnCount(0);

                getleadProbability();
                getleadCumulativeProbability();
                getleadIntervals();
                try {
                    FileInputStream fis = new FileInputStream("Files/leadtime.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel3.addColumn("LeadTime",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/leadFrequency.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel3.addColumn("Frequency",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/leadProbability.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel3.addColumn("Probability",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/leadCumulativeProbability.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel3.addColumn("Cumulative",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/leadIntervals.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel3.addColumn("Interval ",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }


            }
        });


    }

}
