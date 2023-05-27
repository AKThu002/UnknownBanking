/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.unknownbanking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AKThu002
 */
public class BalanceManipulation {
    databaseConnection dbConnection = new databaseConnection();
    Connection dbconnection = dbConnection.databaseConn();
    
    String balance;
    String query;
    PreparedStatement preStatement;
    ResultSet result;
    
    public String formatBalance(String balance){
        String tempBalance = "";
        String finalBalance = "";
        char[] reversedBalanceChar =  balance.toCharArray();
        int count = 0;
        for(int x=reversedBalanceChar.length-1; x>=0; x--){
            if(count%3 == 0) {
                tempBalance += ",";
            }
            tempBalance += reversedBalanceChar[x];
            count++;
        }
        char[] balanceChar = tempBalance.toCharArray();
        for(int x=balanceChar.length-1; x>0; x-- ){
            finalBalance += balanceChar[x];
        }
        return finalBalance;
    }
    
    public String getBalance() {
        try {
            query = "SELECT balance_amount FROM balance WHERE id = 1";
            preStatement = dbconnection.prepareStatement(query);
            result = preStatement.executeQuery();
            while(result.next()) {
               balance = result.getString("balance_amount");
                   }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
//        balance = formatBalance(balance);
//        System.out.println(balance);
        return balance;
    }
    
    public void deposit(int amount) {
        try {
            query = "UPDATE balance SET balance_amount = "+(Integer.valueOf(getBalance()) + amount)+" WHERE id = 1";
            preStatement = dbconnection.prepareStatement(query);
            preStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BalanceManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdraw(int amount) {
        try {
            query = "UPDATE balance SET balance_amount = "+(Integer.valueOf(getBalance()) - amount)+" WHERE id = 1";
            preStatement = dbconnection.prepareStatement(query);
            preStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BalanceManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
