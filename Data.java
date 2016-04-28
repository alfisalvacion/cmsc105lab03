/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc.pkg105.lab03;

import java.util.LinkedList;

/**
 *
 * @author alfisalvacion
 */
public class Data {
    String strvalue;
    double value;
    int freq;
    LinkedList <Integer> indeces = new LinkedList <Integer>();
    
    Data(Double d, int count, LinkedList ind) {
        value = d;
        strvalue = "" + d;
        freq = count;
        indeces = ind;
    }
}
