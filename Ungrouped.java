/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.pkg105.lab03;

import static cmsc.pkg105.lab03.CMSC105LAB03.dataInterpretation;
import static cmsc.pkg105.lab03.CMSC105LAB03.showDescription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author alfisalvacion
 */
public class Ungrouped {
    
    Double[] inputarray;
    Data[] uniqarr;
    int maxin;
    int cont;
    double mean;
    double median;
    double variance;
    double standarddeviation;
    double max;
    double min;
    double range;
    LinkedList mode = new LinkedList();
    
    Ungrouped() {
        runUngroupedanalysis();
    }
    
    void runUngroupedanalysis() {
        Scanner sc = new Scanner(System.in);
        getInputdata();
        do {
            showDescription();
            displayInputdata();
            changeInput();
            find();
            inputInterpretation();
            showInterpretation();
            System.out.print("\n\nWhat do you want to do?\n"
                    + "[1] Input new data for ungrouped data   [2] Back to main menu   [Any number except "
                    + "1 and 2] Reuse data\nYour choice: ");
            cont = sc.nextInt();
            if(cont == 1)
                runUngroupedanalysis();
            if(cont == 2)
                break;
        } while((cont < 1 || cont > 2));
    }//DONE BUT COULD BE BETTER
    
    void getMaxin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the maximum number of input.");
        do{
            try{
                System.out.print("Your maximum number of inputs is: ");
                maxin = sc.nextInt();
                if(maxin < 1)
                    throw new IllegalArgumentException();
            }
            catch(Exception e){
                System.out.println("\nEnter a positive number for maximum number of inputs.");
                sc = new Scanner(System.in);
            }
        }while(maxin < 1);
    }//DONE
    
    void getInputdata() {
        getMaxin();
        Scanner sc = new Scanner(System.in);
        inputarray = new Double[maxin];
        double inp;
        System.out.println();
        for(int i = 0; i < maxin; i++) {
            try {
                System.out.print("[" + (i + 1) + "] ");
                inputarray[i] = sc.nextDouble();
                
            } catch(Exception e) {
                System.out.println("Invalid input. Input not a number.");
                sc = new Scanner(System.in);
                i--;
            }
        }
    }//DONE
    
    void displayInputdata() {
        System.out.println("\nYour input data: ");
        for(int i = 0; i < maxin; i++) {
            System.out.println("[" + (i+1) + "] "  + inputarray[i] + "  ");
        }
    }//DONE
    
    void changeInput() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            try {    
                System.out.println("\nDo you want to change anything from your input?");
                System.out.println("[1] Yes    [2] No");
                System.out.print("You choose: ");
                choice = sc.nextInt();
                if(choice < 1 || choice > 2)
                    throw new IllegalArgumentException();
            } catch(Exception e) {
                System.out.println("Choice not in range. ");
                sc = new Scanner(System.in);
            }
        } while(choice < 1 || choice > 2);
        if(choice == 1) {
            changeInputYes();
        } 
        else
        if(choice == 2) {
            System.out.println("Your data is final.");
        }
    }//DONE
    
    private void changeInputYes() {
        Scanner sc = new Scanner(System.in);
        int ind;
        double newvalue;
        int cont = 0;
        System.out.println("\nEnter the index of the data you want to change.");
        System.out.println("Choose between 1 and " + maxin);
        do {
            try {
                System.out.print("\nIndex you want to change: ");
                ind = sc.nextInt();
                if(ind < 1 || ind > maxin)
                    throw new IllegalArgumentException("Input out of range.");
                System.out.println("You chose index [" + ind + "] " + inputarray[(ind-1)]);
                System.out.print("Enter new value: ");
                newvalue = sc.nextDouble();
                inputarray[(ind-1)] = newvalue;
                displayInputdata();
                do {
                    System.out.println("\nDo you want to continue changing values?");
                    System.out.printf("Enter 0 if Yes, 1 if No.\nYou choose: ");
                    cont = sc.nextInt();
                    if(cont < 0 || cont > 1)
                        System.out.println("Input not in range.");
                } while(cont < 0 || cont > 1);
            } catch(Exception e) {
                System.out.println("Invalid input.");
                sc = new Scanner(System.in);
            }
        } while(cont == 0);
    }//DONE
    
    void find() {
        Scanner sc = new Scanner(System.in);LinkedList Mod = new <Mode>LinkedList();
//        double temp = max();
//        int ctr = 0;
//        Mode mode[];
//        for (int i = 0; i < data1.length; i++) {
//            if (data1[i][2] == temp) {
//                ctr++;
////                for(int cnt = 0; cnt < Mod.size(); cnt++){
////                    if(data1[i][2] == ((Mode)(Mod.get(cnt))).freq)
////                            Mod.add(data1[i]);
////                }
//            }
//        }
//        if(ctr != data1.length){
//            mode = new Mode[ctr];
//            for (int i = 0, modei = 0; i < data1.length; i++) {
//                if (data1[i][2] == temp && temp != 1) {
//                    mode[modei] = new Mode(data1[i][0], data1[i][1]);
//                    modei++;
//                }
//            }
//            if (mode.length == 0) {
//                    System.out.println("\nMode: no mode");
//            }
//            else { 
//                System.out.println("\nModes: ");
//                for(int i = 0; i < data1.length; i++) {
//                    if(data1[i][2] == temp) {
//                        System.out.println(data1[i][0] + " - " + data1[i][1]);
//                    }
//                }
//                if (ctr == 1) {
//                    System.out.println("Unimodal");
//                } else if (ctr == 2) {
//                    System.out.println("Bimodal");
//                } else if (ctr >= 3) {
//                    System.out.println("Multimodal");
//                }
//            }
//        }
//        else
//            System.out.println("\nMode: no mode");
        int choice = 0;
        do {
            System.out.println("\nWhat do you want to do?");
            System.out.println("[1] Find Mean    [2] Find Median    [3] Find Mode   [4] All Measures");
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    displayMean();
                    break;
                case 2:
                    displayMedian();
                    break;
                case 3:
                    displayMode();
                case 4:
                    displayAll();
                    break;
                default:
                    System.out.println("Invalid Input. Input not in range.");
            }
        } while(choice < 1 || choice > 4);
    }//DONE
    
    void displayMean() {
        mean = findMean(inputarray);
        variance = findVariance(inputarray);
        standarddeviation = findStandardDeviation(variance);
        System.out.printf("\nMean: %.2f\n", mean);
        System.out.printf("\nVariance: %.2f\n", variance);
        System.out.printf("\nStandard Deviation: %.2f\n", standarddeviation);
    }//DONE
    
    void displayMedian() {
        median = findMedian(inputarray);
        range = findRange(inputarray);
        System.out.printf("\nMedian: %.2f\n", median);
        System.out.printf("\nRange: %.2f\n", range);
    }//DONE
    
    void displayMode() {
        mode = findMode(inputarray);
        System.out.println("\nModes: " + mode);
        if(mode.isEmpty())
            System.out.print("No Mode");
        else
        if(mode.size() == 1)
            System.out.print("Unimodal Distribution");
        else
        if(mode.size() == 2)
            System.out.print("Bimodal Distribution");
        else
        if(mode.size() > 2)
            System.out.print("Multimodal Distribution");
    }//DONE
    
    void displayAll() {
        displayMean();
        displayMedian();
        displayMode();
    }//DONE
    
    double findMean(Double[] arrinp) {
        double ans;
        double sum = 0;
        for(int i = 0; i < maxin; i++) {
            sum += inputarray[i];
        }
        ans = sum/maxin;
        return ans;
    }//DONE
    
    double findVariance(Double[] arrinp) {
        double ave;
        double sqrd;
        double sum = 0;
        double minus = 0;
        for(int i = 0; i < maxin; i++) {
            minus = (inputarray[i] - mean);
            sqrd = minus*minus;
            sum = sum + sqrd;
        }
        ave = sum/(maxin-1);
        return ave;
    }//DONE
    
    double findStandardDeviation(Double variance) {
        return Math.sqrt(variance);
    }//DONE
    
    double findMedian(Double[] arrinp) {
        double ans;
        if(maxin % 2 == 0) {
            double lowmid = arrinp[(maxin/2)-1];//minus one because indeces in array starts in 0
            double himid = arrinp[((maxin+2)/2)-1];
            ans = (lowmid + himid)/2;
        }
        else
            ans = arrinp[(maxin/2)];
        
        return ans;
    }//DONE
    
    double findRange(Double[] arrinp) {
        min = findMin(arrinp);
        max = findMax(arrinp);
        return max-min;
    }//DONE
    
    double findMin(Double[] arrinp) {
        double tempmin = arrinp[0];
        for(int i = 1; i < maxin; i++) {
            if(arrinp[i] < tempmin)
                tempmin = arrinp[i];
        }
        return tempmin;
    }//DONE
    
    double findMax(Double[] arrinp) {
        double tempmax = arrinp[0];
        for(int i = 1; i < maxin; i++) {
            if(arrinp[i] > tempmax)
                tempmax = arrinp[i];
        }
        return tempmax;
    }//DONE
    
    Set findUniqvalues(Double[] arrinp) {
        Set<Double> uniqKeys = new TreeSet<Double>();
        uniqKeys.addAll(Arrays.asList(arrinp));
        //System.out.println("uniqKeys: " + uniqKeys);
        return uniqKeys;
    }//DONE
    
    void setUniqvalues() {
        Set<Double> uniqKeys = findUniqvalues(inputarray);
        Object[] unique = uniqKeys.toArray();
        uniqarr = new Data[unique.length];
        LinkedList index;
        for(int i = 0, count = 0; i < unique.length; i++) {
            index = new LinkedList();
            count = 0;
            for(int j = 0; j < maxin; j++) {
                if(unique[i].equals(inputarray[j])) {
                    count++;
                    index.add(j+1);
                    uniqarr[i] = new Data((Double)unique[i], count, index);
                }
            }
        }         
    }//DONE
    
    void printUniqvalues() {
        System.out.println("");
        for(int i = 0; i < uniqarr.length; i++) {
            System.out.println("Value: " + uniqarr[i].strvalue + "    Count: " 
                    + uniqarr[i].freq + "    Indeces: " + uniqarr[i].indeces);
        }
    }//DONE
    
    LinkedList findMode(Double[] arrinp) {
        LinkedList mod = new LinkedList();
        setUniqvalues();
        printUniqvalues();
        int max = uniqarr[0].freq;
        int min = uniqarr[0].freq;
        for(int i = 1; i < uniqarr.length; i++) {
            if(uniqarr[i].freq > max) {
                max = uniqarr[i].freq;
            }
            if(uniqarr[i].freq < min)
                min = uniqarr[i].freq;
        }
        for(int i = 0; i < uniqarr.length; i++) {
            if(uniqarr[i].freq == max)
                mod.add(uniqarr[i].strvalue);
        }
        if(uniqarr.length != 1)
            if(max == min)
                return new LinkedList();
            
        return mod;
    }//DONE
    
    void inputInterpretation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nPlease input an Interpretation for the output.");
        System.out.print("Your interpretation: ");
        dataInterpretation = sc.nextLine();
    }//DONE
    
    void showInterpretation() {
        System.out.println("\n\nInterpretation of output: " + dataInterpretation);
    }//DONE
}
