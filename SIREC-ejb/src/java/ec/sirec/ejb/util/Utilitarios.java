/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.util;

/**
 *
 * @author DAVID GUAN
 */
public class Utilitarios {
    
    public Utilitarios(){
        
    }
    
    public static Boolean validarCedula(String cedula) {
        int total = 0;
        char[] cedulaSeparada = cedula.toCharArray();
        if (cedula.length() == 10) {
            for (int i = 0; i < 9; i++) {
                if (i % 2 == 0) {
                    int aux = Integer.valueOf(String.valueOf(cedulaSeparada[i])) * 2;
                    if (aux > 9) {
                        char[] auxString = String.valueOf(aux).toCharArray();
                        aux = Integer.valueOf(String.valueOf(auxString[0])) + Integer.valueOf(String.valueOf(auxString[1]));
                    }
                    total += aux;
                } else {
                    total += Integer.valueOf(String.valueOf(cedulaSeparada[i])) * 1;
                }
            }
            if ((total % 10 + Integer.valueOf(String.valueOf(cedulaSeparada[9]))) % 10 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
