/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Record;
import Bean.Transaction;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author yanyanzhou
 */
public class ZZTest {

    public static void main(String[] args) throws Exception {
        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            TransactionDao dao = (TransactionDao) ac.getBean("transactionDao");
//
//            String[] p={"5"};
//            String[] a={"6"};
//            System.out.println(dao.InsertTransactionByID(p));
//            System.out.println(dao.InsertTransactionByID(a));

//            System.out.println("GetTransaction");
//            SqlRowSet rows = dao.GetTransaction("5");
//            while(rows.next()){
//                System.out.println(rows.getInt(1)+" "+rows.getTimestamp(2)+" "+rows.getInt(4));
//            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
