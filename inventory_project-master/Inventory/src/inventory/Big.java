package inventory;

import javax.management.monitor.StringMonitorMBean;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Big {


    private int orderQuantity;
    private int orderPoint;
    private int Days;
    private int UnitsRecieved;
    private int BeginningInventory;
    private int EndingInventory;
    private int Demand;
    private int LostSales;
    private boolean Order;
    private int LeadTime;
    private int RandomDemand;
    private int RandomLead;
    private int RecieveDate;

    public Big() {
        bigGui();

    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void setOrderPoint(int orderPoint) {
        this.orderPoint = orderPoint;
    }

    public void setDays(int days) {
        this.Days = days;
    }


    void setBeginningInventory() {
        this.BeginningInventory = this.orderQuantity;
    }

    private int D = 23;

    public int getRandomDemand() {
        this.RandomDemand = (int) (Math.random() * ((100 - 1)) + 1);
        return RandomDemand;
    }

    public int getRandomLead() {
        this.RandomLead = (int) (Math.random() * ((100 - 1)) + 1);
        return RandomLead;
    }

    public int getIntervalFile(String fileName, int Rand) {
        File file = new File(fileName);
        String str = "";
        try (InputStream in = new FileInputStream(file)) {
            int content;
            while ((content = in.read()) != -1) {
                str += (char) content;
                //System.out.print((char)content);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        String[] arrOfStr = str.split("@", 0);
        boolean flag = false;
        //System.out.println();
        int count = 0;
        for (int i = 0; i < arrOfStr.length; i++) {
            String[] arrofInt = arrOfStr[i].split("to", 0);
            if (Rand >= parseInt(arrofInt[0]) && Rand <= parseInt(arrofInt[1])) {
                count = i;
                break;
            }
        }

        return count;
    }

    public int getFileNum(String fileName, int flag) {

        File file = new File(fileName);
        String str = "";
        try (InputStream in = new FileInputStream(file)) {
            int content;
            while ((content = in.read()) != -1) {
                str += (char) content;
                //System.out.print((char)content);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        String[] arrOfStr = str.split("@", 0);
        int demand = parseInt(arrOfStr[flag]);


        return demand;
    }

    public int getDemand(int rand) {

        int x;
        x = getFileNum("Files/Demand.txt", getIntervalFile("Files/DemandIntervals.txt", rand));
        return x;
    }

    public int getLead(int rand) {

        int x;
        x = getFileNum("Files/leadTime.txt", getIntervalFile("Files/leadIntervals.txt", rand));
        return x;

    }

    public void Simulate() throws IOException {
        File file0 = new File("Files/Day.txt");
        FileWriter fr0= new FileWriter(file0);
        File file1 = new File("Files/UnitsRecieved.txt");
        FileWriter fr1= new FileWriter(file1);
        File file2 = new File("Files/BeginningInventory.txt");
        FileWriter fr2= new FileWriter(file2);
        File file3 = new File("Files/RandomDemand.txt");
        FileWriter fr3= new FileWriter(file3);
        File file4 = new File("Files/Bdemand.txt");
        FileWriter fr4= new FileWriter(file4);
        File file5 = new File("Files/EndingInventory.txt");
        FileWriter fr5= new FileWriter(file5);
        File file6 = new File("Files/LostSales.txt");
        FileWriter fr6= new FileWriter(file6);
        File file7 = new File("Files/Order.txt");
        FileWriter fr7= new FileWriter(file7);
        File file8 = new File("Files/RandomLead.txt");
        FileWriter fr8= new FileWriter(file8);
        File file9 = new File("Files/BleadTime.txt");
        FileWriter fr9= new FileWriter(file9);
        for(int i=1 ;i<=Days ; i++){
            Order=false;
            if (i==1){
                UnitsRecieved=0;
                BeginningInventory=orderQuantity;
                RandomDemand=getRandomDemand();
                Demand=getDemand(RandomDemand);
                EndingInventory=BeginningInventory-Demand;
                if(Demand>BeginningInventory){
                    LostSales=BeginningInventory-Demand;
                }
                else{
                    LostSales=0; }
                if(EndingInventory<=orderPoint){
                    Order=true;
                }
                else {
                    Order=false;
                }

                if (Order){
                    RandomLead=getRandomLead();
                    LeadTime=getLead(RandomLead);
                }



            }

            else {
                UnitsRecieved=0;
                if(i==RecieveDate){
                    RecieveDate=0;
                    UnitsRecieved=orderQuantity;}
                BeginningInventory=EndingInventory+UnitsRecieved;
                RandomDemand=getRandomDemand();
                Demand= getDemand(RandomDemand);
                if (BeginningInventory>=Demand){
                EndingInventory=BeginningInventory-Demand;
                }
                else
                {EndingInventory=0;}

                if(BeginningInventory<Demand){
                    LostSales=Demand-BeginningInventory;
                }else{LostSales=0;}
                if (RecieveDate==0){
                    if(EndingInventory<=orderPoint){
                        Order=true;
                    }}
                if (LostSales!=0){

                }
                if(Order){
                    RandomLead=getRandomLead();
                    LeadTime=getLead(RandomLead);
                    RecieveDate=LeadTime+i+1;
                    fr8.write(String.valueOf(RandomLead));
                    fr9.write(String.valueOf(LeadTime));
                }

            }
            fr0.write(String.valueOf(i)+"@");
            fr1.write(String.valueOf(UnitsRecieved)+"@");
            fr2.write(String.valueOf(BeginningInventory)+"@");
            fr3.write(String.valueOf(RandomDemand)+"@");
            fr4.write(String.valueOf(Demand)+"@");
            fr5.write(String.valueOf(EndingInventory)+"@");
            fr6.write(String.valueOf(LostSales)+"@");
            fr7.write(String.valueOf(Order)+"@");
            fr8.write("@");
            fr9.write("@");


        }
        fr0.close();
        fr1.close();
        fr2.close();
        fr3.close();
        fr4.close();
        fr5.close();
        fr6.close();
        fr7.close();
        fr8.close();
        fr9.close();



    }

    public void bigGui() {
        JFrame f4 = new JFrame("Final Table");
        f4.setVisible(true);
        f4.setSize(1150, 500);
        f4.setLocationRelativeTo(null);
        f4.setResizable(false);
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panell = new JPanel();
        panell.setLayout(null);

        // back
        JButton b3 = new JButton("Back");
        b3.setBounds(900, 10, 100, 30);
        b3.setVisible(true);
        panell.add(b3);
        //Table

        JScrollPane js3 = new JScrollPane();
        js3.setBounds(10, 50, 1050, 190);
        f4.getContentPane().add(js3);
        f4.getContentPane().add(panell);
        JTable table4 = new JTable();
        //table.setBounds();

        js3.setViewportView(table4);
        DefaultTableModel tableModel4;
        tableModel4 = new DefaultTableModel();
        //tableModel2.setColumnIdentifiers(columns2);

        table4.setModel(tableModel4);
        ////




//********labels**************************************
        JLabel orderlabel=new JLabel("Order Quantity");
        panell.add(orderlabel);
        JLabel reorderlabel=new JLabel("Reorder Point");
        panell.add(reorderlabel);
        JLabel dayslabel=new JLabel("Days");
        panell.add(dayslabel);
//********textfields*********************************
        JTextField order=new JTextField();
        panell.add(order);
        JTextField reorder=new JTextField();
        panell.add(reorder);
        JTextField days=new JTextField();
        panell.add(days);

//******Buttons***********************************************************************************

        JButton show=new JButton("Show table");
        panell.add(show);
        JButton Submit=new JButton("submit");
        panell.add(Submit);



//**********Customization for buttons**************************************************************

        show.setBounds(400,400,200,25);
        Submit.setBounds(150,400,200,25);

//***************customizations for textfields*******************
        order.setBounds(150, 250, 150, 25);
        reorder.setBounds(150, 300, 150, 25);
        days.setBounds(150, 350, 150, 25);

//**************customization for labels***********
        orderlabel.setBounds(10, 250, 100, 25);
        reorderlabel.setBounds(10, 300, 200, 25);
        dayslabel.setBounds(10,350,200,25);







        //pDM2.add(lab2);







        panell.add(js3);
        //panell.add(tablepanel);
        f4.add(panell);
        f4.setVisible(true);

        Submit.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                try {
                    if (order.getText().equals("") &&reorder.getText().equals("")&&days.getText().equals(""));
                    int d = Integer.parseInt(order.getText());
                    //getOrderQuantity(d);
                    setOrderQuantity(d);
                    int fr = Integer.parseInt(reorder.getText());
                   // getOrderPoint(fr);
                    setOrderPoint(fr);
                    int k = Integer.parseInt(days.getText());
                   // getDays(k);
                    setDays(k);

                    System.out.println(Days);
                    System.out.println(orderQuantity);
                    System.out.println(orderPoint);
                    order.setText(null);
                    reorder.setText(null);
                    days.setText(null);
                    Simulate();

                    JOptionPane.showMessageDialog(null, "you add Successfully ");
                }catch(NumberFormatException | IOException h){
                    JOptionPane.showMessageDialog(null, "Please enter Data first");
                }


            }
        });
        b3.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                f4.setVisible(false);
                GUI f=new GUI();


            }
        });




        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel4.setColumnCount(0);


                try {
                    FileInputStream fis = new FileInputStream("Files/Day.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Day",line.split("@"));

                    }


                } catch (IOException j) {

                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/UnitsRecieved.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("UnitsRecieved",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/BeginningInventory.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("BeginningInventory",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }

                try {
                    FileInputStream fis = new FileInputStream("Files/RandomDemand.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Random Numbers",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }


                try {
                    FileInputStream fis = new FileInputStream("Files/Bdemand.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Demand",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }

                try {
                    FileInputStream fis = new FileInputStream("Files/EndingInventory.txt/");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Ending Inventory",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/LostSales.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("LostSales",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/Order.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Order",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }
                try {
                    FileInputStream fis = new FileInputStream("Files/RandomLead.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Random Numbers",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }



                try {
                    FileInputStream fis = new FileInputStream("Files/BleadTime.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line;
                    while ((line = br.readLine()) != null) {
                        tableModel4.addColumn("Lead Time",line.split("@"));
                    }


                } catch (IOException j) {
                    // JOptionPane.showMessageDialog(null, "Error");
                    j.printStackTrace();
                }



            }
        });
    }
}