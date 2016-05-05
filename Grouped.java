/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.pkg105.lab03;

import static cmsc.pkg105.lab03.CMSC105LAB03.dataInterpretation;
import java.util.Arrays;
import static java.util.Arrays.sort;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author alfisalvacion
 */
class Grouped {

    double[][] data1;
    double sumofF, sumofFmX, sumofFmXX;
    boolean isOpenended;
    boolean isFirstopened;
    boolean isLastopened;
    Data[] uniqarr;
    Double[] freqarray;
    
    LinkedList mode = new LinkedList();

    public Grouped() {
        this.isOpenended = false;
        this.isFirstopened = false;
        this.isLastopened = false;
        this.setClassInterval();
        this.getInput();
    }

    public void setClassInterval() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the total number of class intervals.");
        int num = 0;
        do {
            try {
                System.out.print("Input: ");
                num = sc.nextInt();
                if (num < 0) {
                    System.out.println("Invalid Input. Input is out of range.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Input is not a number.");
                sc = new Scanner(System.in);
                num = 0;
            }
        } while (num < 0);
        data1 = new double[num][6];
    }

    public void setOpenended(int i) {
        if (i == 1) {
            isOpenended = true;
        } else {
            isOpenended = false;
        }
    }

    public void setWichOpen(int i) {
        if (i == 1) {
            isFirstopened = true;
        } else if (i == 2) {
            isLastopened = true;
        } else if (i == 3) {
            isFirstopened = true;
            isLastopened = true;
        }
    }

    public void getInput() {
        double temp1;
        Scanner temp = new Scanner(System.in);
        System.out.println("");
        double max = 0;

        for (int i = 0; i < data1.length; i++) {
            System.out.println("Interval [" + (i + 1) + "] :");

            do {
                try {
                    System.out.print(" LCL: ");
                    data1[i][0] = temp.nextDouble();
                    if (data1[i][0] <= max) {
                        System.out.println("Invalid Input. Input out of range.");             //lcl
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input. Input is not a number.");
                    temp = new Scanner(System.in);
                    data1[i][0] = max - 1;
                }
            } while (data1[i][0] <= max);

            do {
                try {
                    System.out.print(" UCL: ");
                    data1[i][1] = temp.nextDouble(); //ucl
                    if (data1[i][1] <= data1[i][0]) {
                        System.out.println("Invalid Input. Input out of range.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input. Input is not a number.");
                    temp = new Scanner(System.in);
                    data1[i][1] = data1[i][0] - 1;
                }
            } while (data1[i][1] <= data1[i][0]);

            max = data1[i][1];

            do {
                try {
                    System.out.print(" Frequency: ");
                    data1[i][2] = temp.nextDouble();    //frequency
                    if (data1[i][0] < 0) {
                        System.out.println("Invalid Input. Input out of range.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input. Input out of range.");
                    temp = new Scanner(System.in);
                    data1[i][2] = 0;
                }
            } while (data1[i][2] < 0);
            data1[i][3] = (data1[i][1] + data1[i][0]) / 2;    //midpoint
            data1[i][4] = data1[i][3] * data1[i][2];        //fxm
            data1[i][5] = data1[i][4] * data1[i][3];        //fxm^2
            System.out.println("");
        }
    }

    public void editInput(int num) {
        Scanner temp = new Scanner(System.in);
        System.out.println("\nInterval [" + (num) + "] :");
        num = num - 1;
        int ctr;
        if (num - 1 == 0) {
            ctr = 1;
        } else {
            ctr = num;
        }

        do {
            try {
                System.out.print(" LCL: ");
                data1[num][0] = temp.nextDouble();                //lcl
                if (data1[ctr - 1][1] > data1[num][0]) {
                    System.out.println("Invalid Input. Input is out of range.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Input is not a number.");
                temp = new Scanner(System.in);
                data1[num][0] = data1[ctr - 1][1] - 1;
            }
        } while (data1[ctr - 1][1] > data1[num][0]);

        do {
            try {
                System.out.print(" UCL: ");
                data1[num][1] = temp.nextDouble();    //ucl
                if (data1[num][0] > data1[num][1]) {
                    System.out.println("Invalid Input. Input is out of range.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Input is not a number.");
                temp = new Scanner(System.in);
                data1[num][1] = data1[num][0] - 1;
            }
        } while (data1[num][0] > data1[num][1]);

        do {
            try {
                System.out.print(" Frequency: ");
                data1[num][2] = temp.nextDouble();    //frequency
                if (data1[num][2] < 0) {
                    System.out.println("Invalid Input. Input is out of range.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Input is not a number.");
                temp = new Scanner(System.in);
                data1[num][2] = 0;
            }
        } while (data1[num][2] < 0);
        data1[num][3] = (data1[num][1] + data1[num][0]) / 2;    //midpoint
        data1[num][4] = data1[num][3] * data1[num][2];        //fxm
        data1[num][5] = data1[num][4] * data1[num][3];      //fxm^2
    }

    public void display() {
        this.sumofF = 0;
        this.sumofFmX = 0;
        System.out.println("\nInterval |LCL     |UCL     |F      |M       |FxM        |FxM^2");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < data1.length; i++) {
            if (isOpenended) {
                if (isFirstopened && i == 0) {
                    System.out.println(i + 1 + "\t |" + "<=" + "      |" + data1[i][1] + "\t  |" + data1[i][2] + "\t  |" + data1[i][3] + "\t  |" + data1[i][4] + "\t  |" + data1[i][5]);
                } else if (isLastopened && i == data1.length - 1) {
                    System.out.println(i + 1 + "\t |" + data1[i][0] + "    |" + ">=" + "\t  |" + data1[i][2] + "\t  |" + data1[i][3] + "\t  |" + data1[i][4] + "\t  |" + data1[i][5]);
                } else {
                    System.out.println(i + 1 + "\t |" + data1[i][0] + "\t  |" + data1[i][1] + "\t  |" + data1[i][2] + "\t  |" + data1[i][3] + "\t  |" + data1[i][4] + "\t  |" + data1[i][5]);
                }
            } else {
                System.out.println(i + 1 + "\t |" + data1[i][0] + "\t  |" + data1[i][1] + "\t  |" + data1[i][2] + "\t  |" + data1[i][3] + "\t  |" + data1[i][4] + "\t  |" + data1[i][5]);
            }
            this.sumofF = this.sumofF + data1[i][2];
            this.sumofFmX = this.sumofFmX + data1[i][4];
            this.sumofFmXX = this.sumofFmXX + data1[i][5];
        }

        System.out.printf("\ntotal F: %.2f", this.sumofF);
        System.out.printf("\ntotal FxM: %.2f", this.sumofFmX);
        System.out.printf("\ntotal FxMM: %.2f\n", this.sumofFmXX);
    }

    public void displayMean() {
        double mean, var, std;
        mean = this.sumofFmX / this.sumofF;
        System.out.printf("\nMean: %.2f\n", mean);
        var = ((this.sumofF * this.sumofFmXX) - (this.sumofFmX * this.sumofFmX)) / (this.sumofF * (this.sumofF - 1));
        System.out.printf("Variance: %.2f\n", var);
        std = Math.sqrt(var);
        System.out.printf("Standard Deviation: %.2f\n", std);
    }

    public double max() {
        double m = 0;

        for (int i = 0; i < data1.length; i++) {
            if (m < data1[i][2]) {
                m = data1[i][2];
            }
        }
        return m;
    }

    public void Mode() {
        LinkedList Mod = new <Mode>LinkedList();
        double temp = max();
        int ctr = 0;
        Mode mode[];
        for (int i = 0; i < data1.length; i++) {
            if (data1[i][2] == temp) {
                ctr++;
            }
        }
        if(ctr != data1.length){
            mode = new Mode[ctr];
            for (int i = 0, modei = 0; i < data1.length; i++) {
                if (data1[i][2] == temp && temp != 1) {
                    mode[modei] = new Mode(data1[i][0], data1[i][1]);
                    modei++;
                }
            }
            if (mode.length == 0) {
                    System.out.println("\nMode: no mode");
            }
            else { 
                System.out.println("\nModes: ");
                for(int i = 0; i < data1.length; i++) {
                    if(data1[i][2] == temp) {
                        System.out.println(data1[i][0] + " - " + data1[i][1]);
                    }
                }
                if (ctr == 1) {
                    System.out.println("Unimodal");
                } else if (ctr == 2) {
                    System.out.println("Bimodal");
                } else if (ctr >= 3) {
                    System.out.println("Multimodal");
                }
            }
        }
        else
            System.out.println("\nMode: no mode");
    }


    void inputInterpretation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nPlease input an Interpretation for the output.");
        System.out.print("Your interpretation: ");
        dataInterpretation = sc.nextLine();
    }

    void showInterpretation() {
        System.out.println("\n\nInterpretation of output: " + dataInterpretation);
    }
}

class Mode {

    double lcl;
    double ucl;
    double freq;

    Mode(double l, double u) {
        this.lcl = l;
        this.ucl = u;
        this.freq = 0;
    }
    
    void setFreq(double frequ){
        this.freq = frequ;
    }

    void display() {
        System.out.print(this.lcl + " - " + this.ucl);
        System.out.println();
    }
}
