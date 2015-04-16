/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Transaction;
import RowMapper.TransactionRowMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author yanyanzhou
 */
public class TransactionDao {
        private JdbcTemplate jdbcTemplate = null; 
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    }
    
    //search transactions and records by transaction_id
    public Transaction GetTransactionByID(String id) {
        String sql = "SELECT * FROM Transaction where transaction_id=?";
        return jdbcTemplate.queryForObject(sql,
                new Object[] {id},
                new int[] {java.sql.Types.INTEGER},
                new TransactionRowMapper());
    }

    //search transactions and records by customer_id
    public List<Transaction> GetTransactionByCustomerID(String id) {
        String sql = "select * from Transaction t where t.customer_id=?";
        return jdbcTemplate.query(sql,
                new Object[] {id},
                new int[] {java.sql.Types.INTEGER},
                new TransactionRowMapper());
    }

    //search transactions and records by salesperson_id
    public List<Transaction> GetTransactionBySalespersonID(String id) {
        String sql = "SELECT t.transaction_id, t.transaction_date,t.customer_id,t.salesperson_id,"
                + "p.product_id,p.product_name,r.amount, r.price "
                + "FROM Transaction t,Record r, Product p "
                + "WHERE t.transaction_id=r.transaction_id and r.product_id=p.product.id and t.salesperson_id=?";
        return jdbcTemplate.query(sql, 
                new Object[] {id},
                new int[] {java.sql.Types.INTEGER},
                new TransactionRowMapper());
    }

    //search transactions and records by salesperson_id and customer_id
    public List<Transaction> GetTransaction(String[] array) {
        String sql = "SELECT t.transaction_id, t.transaction_date,t.customer_id,t.salesperson_id,"
                + "p.product_id,p.product_name,r.amount, r.price "
                + "FROM Transaction t,Record r, Product p "
                + "WHERE t.transaction_id=r.transaction_id and r.product_id=p.product.id and ";
        if (array[0] != null) {
            sql += "t.customer_id = " + array[0] + " and";
        }
        if (array[1] != null) {
            sql += "and t.salesperson_id = " + array[1] + " and";
        }
        sql = sql.substring(0, sql.length() - 3);
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }
    
    public List<Transaction> GetAllTransaction(){
        String sql = "select * from Transaction";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }
    
    public int GetTranactionTotalAmount(String id){
        String sql = "SELECT sum(r.amount*r.price) from Transaction t, Record r where t.transaction_id = "
                + "r.transaction_id and t.transaction_id = ? group by t.transaction_id";
        return jdbcTemplate.update(sql, 
                new Object[] {id}, 
                new int[] {java.sql.Types.INTEGER});
    }

    //search transactions by date (a period of time)
    public List<Transaction> GetTransactionByDate(String[] array) {
        String sql = "SELECT t.transaction_id, t.transaction_date,t.customer_id,t.salesperson_id,"
                + "p.product_id,p.product_name,r.amount, r.price "
                + "FROM Transaction t,Record r, Product p "
                + "WHERE t.transaction_id=r.transaction_id and r.product_id=p.product.id "
                + "and t.transaction_date>=? and t.transaction_date<=?";
        return jdbcTemplate.query(sql,
                array,
                new int[] {java.sql.Types.TIMESTAMP,java.sql.Types.TIMESTAMP},
                new TransactionRowMapper());
    }

    //insert new transaction by Transaction_id
    public Transaction InsertTransactionByID(String[] array) {
        String sql = "insert into Transaction (transaction_date, customer_id, salesperson_id) "
                + "values (now(),?,?)";//not include transaction_id
        return jdbcTemplate.queryForObject(sql, 
                array, 
                new int[] {java.sql.Types.INTEGER,java.sql.Types.INTEGER},
                new TransactionRowMapper());
    }

    //delete transactions by id
    public int DeleteTransactionByID(String id) {
        String sql = "delete from Transaction where transaction_id=?";
        return jdbcTemplate.update(sql, 
                new Object[] {id}, 
                new int[] {java.sql.Types.INTEGER});
    }

    //update transactions by id
    public int UpdateTransactionByID(String[] array) {
        String sql = "update Transaction set transaction_date=?, customer_id=?,salesperson_id=?,";
        return jdbcTemplate.update(sql, 
                array, 
                new int[] {java.sql.Types.TIMESTAMP,java.sql.Types.INTEGER,java.sql.Types.INTEGER});
    }

}
