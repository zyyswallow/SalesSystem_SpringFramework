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
import org.springframework.jdbc.support.rowset.SqlRowSet;

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
        String sql = "SELECT * FROM Transactions where transaction_id=?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                new int[]{java.sql.Types.INTEGER},
                new TransactionRowMapper());
    }

    //search transactions by customer_id
    public List<Transaction> GetTransactionByCustomerID(String id) {
        String sql = "select * from Transactions t where t.customer_id=?";
        return jdbcTemplate.query(sql,
                new Object[]{id},
                new int[]{java.sql.Types.INTEGER},
                new TransactionRowMapper());
    }

    //search transactions and records by customer_id
    public SqlRowSet GetTransaction(String id) {
        String sql = "SELECT t.transaction_id, t.transaction_date,t.customer_id,"
                + "p.product_id,p.product_name,r.amount, r.price "
                + "FROM Transactions t,Record r, Product p "
                + "WHERE t.transaction_id=r.transaction_id and r.product_id=p.product_id "
                + "and t.customer_id =? ";
        return jdbcTemplate.queryForRowSet(sql, 
                new Object[] {id}, 
                new int[] {java.sql.Types.INTEGER});
    }

    public List<Transaction> GetAllTransaction() {
        String sql = "select * from Transactions";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

    public SqlRowSet GetTranactionTotalAmount(String id) {
        String sql = "SELECT sum(r.amount*r.price) from Transactions t, Record r where t.transaction_id = "
                + "r.transaction_id and t.transaction_id = ? group by t.transaction_id";
        return jdbcTemplate.queryForRowSet(sql,
                new Object[]{id},
                new int[]{java.sql.Types.INTEGER});
    }

    //search transactions by date (a period of time)
    public SqlRowSet GetTransactionByDate(String[] array) {
        String sql = "SELECT t.transaction_id, t.transaction_date,t.customer_id,"
                + "p.product_id,p.product_name,r.amount, r.price "
                + "FROM Transactions t,Record r, Product p "
                + "WHERE t.transaction_id=r.transaction_id and r.product_id=p.product_id "
                + "and t.transaction_date>=? and t.transaction_date<=?";
        return jdbcTemplate.queryForRowSet(sql,
                array,
                new int[]{java.sql.Types.TIMESTAMP, java.sql.Types.TIMESTAMP});
    }

    //insert new transaction by Transaction_id
    public int InsertTransactionByID(String[] array) {
        String sql = "insert into Transactions (transaction_date, customer_id) "
                + "values (CURRENT_TIMESTAMP,?)";//not include transaction_id
        return jdbcTemplate.update(sql,
                array,
                new int[]{java.sql.Types.INTEGER});
    }

    //delete transactions by id
    public int DeleteTransactionByID(String id) {
        String sql = "delete from Transactions where transaction_id=?";
        return jdbcTemplate.update(sql,
                new Object[]{id},
                new int[]{java.sql.Types.INTEGER});
    }

    //update transactions by id
    public int UpdateTransactionByID(String[] array) {
        String sql = "update Transactions set transaction_date=?, customer_id=? where transaction_id=?";
        return jdbcTemplate.update(sql,
                array,
                new int[]{java.sql.Types.TIMESTAMP, java.sql.Types.INTEGER, java.sql.Types.INTEGER});
    }

}
