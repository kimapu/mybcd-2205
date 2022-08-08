/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.bcd.sample.reference.demo;

import java.util.List;

import my.bcd.sample.reference.samples.adapter.TrnxPoolAdapter;
import my.bcd.sample.reference.samples.trnxpool.Transaction;

/**
 *
 * @author kimlee
 */
public class GetTrnxs {
 
    public static void main(String[] args) {
        tst1();
    }
    
    static void tst1(){
        List<Transaction> trnxPool = TrnxPoolAdapter.getTransactions();
        trnxPool.stream().forEach( System.out::println );
    }
    
}
