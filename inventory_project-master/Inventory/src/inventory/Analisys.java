package inventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Analisys  {

    private int totalEndingInventory;
    private  int Day;
    private int totalLostSales;
    private int numberOfOrders;
    private float orderingCost;
    private float holdingCost;
    private float lostSalesCost;
    private int numberOfWorkingDaysYear;
    private float totalDailyInventoryCost;
    private float AnnualInventoryCost;
    private float averageEndingInventory;
    private float averageLostSales;
    private float averageNumberOfOrders;
    public Analisys() {
        AnalysisGUI();
    }

    public int getDays(){
        try {
            File file =new File("Files/Day.txt");
            Scanner scan = new Scanner(file);
            String line;
            String [] saperate;
            while(scan.hasNext()){
                line=scan.nextLine();
                saperate =line.split("@");
                for (int i=0;i<saperate.length;i++) {
                    Day = Integer.parseInt(saperate[i]);
                }
            }
        }
        catch (IOException e){}

        return Day;
    }

    public int getTotalEndingInventory(){
        try {
            File file =new File("Files/EndingInventory.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                String line=scan.nextLine();
                String [] saperate =line.split("@");
                for(int i=0;i<saperate.length;i++) {
                    totalEndingInventory += Integer.parseInt(saperate[i]);

                }
            }
            System.out.println(totalEndingInventory);
        }
        catch (IOException e){}

        return totalEndingInventory;
    }

    public int getTotalLostSales() {
        try {

            File file = new File("Files/LostSales.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] saperate = line.split("@");
                for(int i=0;i<saperate.length;i++) {
                    totalLostSales += Integer.parseInt(saperate[i]);
                }
            }
        } catch (IOException e) {}

        return totalLostSales;

    }

    public int getNumberOfOrders(){
        try {

            File file = new File("Files/Order.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] saperate = line.split("@");
                for (int i=0;i<saperate.length;i++) {
                    if (saperate[i].equals("true")) {
                        numberOfOrders++;
                    }
                }
            }
        } catch (IOException e) {}
        return numberOfOrders;
    }

    public float getAverageEndingInventory(){

        return averageEndingInventory =(float)totalEndingInventory/getDays();


    }

    public float getAverageLostSales(){
        return averageLostSales = (float)totalLostSales/getDays();
    }

    public float getAverageNumberOfOrders(){
        return averageNumberOfOrders = (float)numberOfOrders / getDays();
    }

    public float getDailyOrderCost(){
        return orderingCost * averageNumberOfOrders;
    }

    public float getDailyHoldingCost(){
        return (holdingCost / getDays()) * averageEndingInventory;
    }

    public float getDailyStockoutCost(){
        return lostSalesCost * averageLostSales;
    }

    public float getTotalInventoryCost(){
        totalDailyInventoryCost = getDailyHoldingCost()+getDailyOrderCost()+getDailyStockoutCost();
        return totalDailyInventoryCost;
    }
    public float getAnnualInventoryCost(){
        AnnualInventoryCost = totalDailyInventoryCost * numberOfWorkingDaysYear;
        return AnnualInventoryCost;
    }




    public void AnalysisGUI(){
        JFrame f6 = new JFrame("Analysis");
        f6.setVisible(true);
        f6.setSize(890, 500);
        f6.setLocationRelativeTo(null);
        f6.setResizable(false);
        f6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /////
        JPanel pDM3 = new JPanel();
        pDM3.setLayout(null);
        ////////////////labels for Inputs/////////////////////
        JLabel Dayslab= new JLabel("Days:");
        Dayslab.setBounds(10, 10, 180, 30);
        pDM3.add(Dayslab);
        JLabel Orderlab = new JLabel("Ordering Cost: ");
        Orderlab.setBounds(300, 10, 180, 30);
        pDM3.add(Orderlab);
        JLabel holdinglab = new JLabel("Holding Cost: ");
        holdinglab.setBounds(10, 50, 180, 30);
        pDM3.add(holdinglab);
        JLabel lostlab = new JLabel("Lost Sales: ");
        lostlab.setBounds(300, 50, 180, 30);
        pDM3.add(lostlab);

        /////textfiled inputs////////
        JTextField daystf = new JTextField();
        daystf.setBounds(100, 10, 180, 30);
        JTextField ordertf = new JTextField();
        ordertf.setBounds(400, 10, 180, 30);
        JTextField holdingtf = new JTextField();
        holdingtf.setBounds(100, 50, 180, 30);
        JTextField losttf = new JTextField();
        losttf.setBounds(400, 50, 180, 30);
        pDM3.add(daystf);
        pDM3.add(ordertf);
        pDM3.add(holdingtf);
        pDM3.add(losttf);
        ///////////////button for input/////
        JButton b4 = new JButton("Submit");
        b4.setBounds(200, 100, 200, 30);
        b4.setVisible(true);
        pDM3.add(b4);
        ////////////labout/////////////
        JLabel Averend= new JLabel("Average ending Inv:");
        Averend.setBounds(10, 150, 180, 30);
        pDM3.add(Averend);
        JLabel Averlostsal = new JLabel("Average" +
                " Lost Sales: ");
        Averlostsal.setBounds(250, 150, 180, 30);
        pDM3.add(Averlostsal);
        JLabel Averordplac = new JLabel("Aver No of Ord Plc");

        Averordplac.setBounds(10, 200, 180, 30);
        pDM3.add(Averordplac);
        JLabel Dailyordcost = new JLabel("Daily" +
                " order Cost : ");
        Dailyordcost.setBounds(250, 200, 180, 30);
        pDM3.add(Dailyordcost);
        JLabel dailyhold= new JLabel("Daily hold cost:");
        dailyhold.setBounds(510, 150, 180, 30);
        pDM3.add(dailyhold);
        JLabel dailystock= new JLabel("Daily stockout cost:");
        dailystock.setBounds(510, 200, 180, 30);
        pDM3.add(dailystock);
        JLabel totdaily= new JLabel("Tot Daily Inv cost:");
        totdaily.setBounds(10, 250, 180, 30);
        pDM3.add(totdaily);
        JLabel Ann= new JLabel("Tot Annual Inv cost:");
        Ann.setBounds(250, 250, 180, 30);
        pDM3.add(Ann);
        /////textfiled inputs////////

        JTextField Averendtf = new JTextField();
        Averendtf.setBounds(130, 150, 100, 30);
        JTextField Averlostsaltf = new JTextField();
        Averlostsaltf.setBounds(380, 150, 100, 30);
        JTextField Averordplactf = new JTextField();
        Averordplactf.setBounds(130, 200, 100, 30);
        JTextField Dailyordcostf = new JTextField();
        Dailyordcostf.setBounds(380, 200, 100, 30);
        JTextField dailyholdtf  = new JTextField();
        dailyholdtf.setBounds(660, 150, 100, 30);
        JTextField dailystocktf  = new JTextField();
        dailystocktf.setBounds(660, 200, 100, 30);
        JTextField totdailytf  = new JTextField();
        totdailytf.setBounds(130, 250, 100, 30);
        JTextField Anntf  = new JTextField();
        Anntf.setBounds(380, 250, 100, 30);

        pDM3.add(Averendtf);
        pDM3.add(Averlostsaltf);
        pDM3.add(Averordplactf);
        pDM3.add(Dailyordcostf);
        pDM3.add(dailyholdtf);
        pDM3.add(dailystocktf);
        pDM3.add(totdailytf);
        pDM3.add(Anntf);

        ///////////////button for input/////

        JButton b5 = new JButton("Show");
        b5.setBounds(350, 350, 200, 30);
        b5.setVisible(true);
        pDM3.add(b5);
        JButton b6 = new JButton("<-");
        b6.setBounds(760, 10, 50, 30);
        b6.setVisible(true);
        pDM3.add(b6);
        //////////////////////////////
        f6.add(pDM3);
        //////////////////
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (daystf.getText().equals("") && ordertf.getText().equals("")&& holdingtf.getText().equals("")&& losttf.getText().equals(""));
                    numberOfWorkingDaysYear=Integer.parseInt(daystf.getText());
                    orderingCost= Float.parseFloat(ordertf.getText());
                    holdingCost = Float.parseFloat(holdingtf.getText());
                    lostSalesCost = Float.parseFloat(losttf.getText());

                    // getDemandFrequency(fr);
                    System.out.println(numberOfWorkingDaysYear);
                    System.out.println(orderingCost);
                    System.out.println(holdingCost);
                    System.out.println(lostSalesCost);
                    holdingtf.setText(null);
                    losttf.setText(null);
                    ordertf.setText(null);
                    daystf.setText(null);

                    JOptionPane.showMessageDialog(null, "you add Successfully ");
                }catch(NumberFormatException h){
                    JOptionPane.showMessageDialog(null, "Please enter Data");
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    getDays();
                    getTotalEndingInventory();
                    getTotalLostSales();
                    getNumberOfOrders();
                    String Av= String.valueOf(getAverageEndingInventory());
                    Averendtf.setText(Av);
                    String Al= String.valueOf(getAverageLostSales());
                    Averlostsaltf.setText(Al);
                    String dh= String.valueOf(getDailyHoldingCost());
                    dailyholdtf.setText(dh);
                    String Ao= String.valueOf(getAverageNumberOfOrders());
                    Averordplactf.setText(Ao);
                    String Do= String.valueOf(getDailyOrderCost());
                    Dailyordcostf.setText(Do);
                    String Ds= String.valueOf(getDailyStockoutCost());
                    dailystocktf.setText(Ds);
                    String Td= String.valueOf(getTotalInventoryCost());
                    totdailytf.setText(Td);
                    String AN= String.valueOf(getAnnualInventoryCost());
                    Anntf.setText(AN);
                    System.out.println(getAverageEndingInventory());
                    b5.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "you add Successfully ");
                }catch(NumberFormatException h){
                    JOptionPane.showMessageDialog(null, "Please enter Data");
                }



            }
        });
        b6.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                f6.setVisible(false);
                GUI f=new GUI();


            }
        });

    }
}
