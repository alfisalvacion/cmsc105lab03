/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.pkg105.lab03;

import java.util.Scanner;

/**
 *
 * @author alfisalvacion
 * @author shuako
 */
public class CMSC105LAB03 {

    /**
     * @param args the command line arguments
     */
    static String dataInterpretation = "";
    static String description = "";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int in = 0;
        do{
            try{
                System.out.println("\n\nWhat kind of data do you want to use?\n[1] Ungrouped    [2] Grouped");
                System.out.print("Your choice: ");
                in = sc.nextInt();
                getDescription();
                //showDescription();
                chooseData(in);
            }
            catch(Exception e){
                sc = new Scanner(System.in);
            }
        } while(in < 1 || in > 2);
    }
    static void chooseData(int x){
        switch(x){
            case 1:
                Ungrouped dataset = new Ungrouped(); 
                break;
            case 2:
                //Ungroup()
                break;
            default:
                System.out.println("Invalid Input. Input is not within range.");
                throw new IllegalArgumentException();
        }
    }
    static void getDescription() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nInput brief description for your dataset.");
        System.out.print("Description: ");
        description = sc.nextLine();
    }
    static void showDescription() {
        System.out.println("\nDescription: " + description);
    }
}
