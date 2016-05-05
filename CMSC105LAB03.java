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
    static boolean open = false;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int in = 0;
        int playback = 0;
        do{
            try{
                System.out.println("\n\nWhat kind of data do you want to use?\n[1] Ungrouped    [2] Grouped");
                System.out.print("Your choice: ");
                in = sc.nextInt();
                getDescription();
                //showDescription();
                chooseData(in);
                System.out.println("\nDo you want to exit the program?");
                System.out.println("[1] Yes   [2] No");
                System.out.println("Your choice: ");
                playback = sc.nextInt();
            }
            catch(Exception e){
                sc = new Scanner(System.in);
            }
        } while((in < 1 || in > 2) || playback == 2);
    }
    static void chooseData(int x){
        switch(x){
            case 1:
                Ungrouped dataset = new Ungrouped(); 
                break;
            case 2:
                group();
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
    static void group() {
        Scanner sc = new Scanner(System.in);
        int num;
        int choice2;
        int playback = 1;
        
        Grouped eee = new Grouped();
        
        do{
            
            eee.display();

            do {
                System.out.println("\nDo you want to edit your data or continue?");
                System.out.println("[1] Edit   [2] Continue");
                System.out.print("Your choice: ");
                choice2 = sc.nextInt();
                if(choice2 == 1) {
                    System.out.println("\nEnter the interval #.");
                    System.out.print("Your choice: ");
                    num = sc.nextInt();
                    eee.editInput(num);
                    eee.display();
                }
            } while(choice2!=2);

            do {
                System.out.println("\nDo you want to indicate open-ended intervals? ");
                System.out.println("[1] Yes   [2] No");
                System.out.print("Your choice: ");
                choice2 = sc.nextInt();
                if(choice2 == 1) {
                    eee.setOpenended(choice2);
                    open = true;
                }
                else
                    open = false;
            } while(choice2 < 1 || choice2 > 2);

            if(open) {
                do {
                    System.out.println("\nWhich intervals do you want to be open-ended?");
                    System.out.println("[1] First   [2] Last   [3] Both");
                    System.out.print("Your choice: ");
                    choice2 = sc.nextInt();
                } while(choice2 < 1 || choice2 > 3);
                eee.setWichOpen(choice2);
                eee.display();
            }

            System.out.println("\nWhat do you want to be calculated?");
            System.out.println("[1] Mean   [2] Median   [3] Mode   [4] All");
            System.out.print("Your choice: ");
            choice2=sc.nextInt();

            switch(choice2) {
                case 1:
                    if(eee.isOpenended)
                        System.out.println("\nMean: not computed. Open-ended interval.");
                    else
                        eee.displayMean();
                    break;
                case 2:
                    System.out.println("\nMedian: not computed.");
                    break;
                case 3:
                    eee.Mode();
                    break;
                case 4:
                    if(eee.isOpenended)
                        System.out.println("\nMean: not computed. Open-ended interval.");
                    else
                        eee.displayMean();
                    System.out.println("\nMedian: not computed");
                    eee.Mode();
                    break;
                default:
                    break;
            }

            eee.inputInterpretation();
            System.out.println("\nWhat do you want to do?");
            System.out.println("[1] Input new data for grouped data   [2] Back to main menu   [3] Reuse Data");
            System.out.print("Your choice: ");
            playback = sc.nextInt();
//            if(playback == 2)
//                break;
            if(playback == 1) {
                eee = new Grouped();
                playback = 3;
            }
        }while(playback == 3);
    }
}
