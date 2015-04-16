/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.ProductCategory;
import RowMapper.ProductCategoryRowMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author yanyanzhou
 */
public class ProductCategoryDao {

    private JdbcTemplate jdbcTemplate = null;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * get Product_Category only by id
     *
     * @param id
     * @return Product Category
     */
    public ProductCategory getProductCategoryById(String id) {
        String sql = "select * from Product_Category where category_id = ?";
        List<ProductCategory> list = jdbcTemplate.query(sql,
                new Object[]{id},
                new int[]{java.sql.Types.VARCHAR},
                new ProductCategoryRowMapper());
        if (list != null) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<ProductCategory> getProductCategory() {
        String sql = "select * from Product_Category";
        List<ProductCategory> list = jdbcTemplate.query(sql, new ProductCategoryRowMapper());
        if (list != null) {
            return list;
        } else {
            return null;
        }
    }

    /**
     * update Product_Category by a given id
     *
     * @param para
     * @return int
     */
    public int updateProductCategoryById(String[] para) {
        String sql = "update Product_Category set category_name = ? where category_id = ?";
        return jdbcTemplate.update(sql, 
                para, 
                new int[] {java.sql.Types.VARCHAR,java.sql.Types.INTEGER});
    }

    /**
     * add new Product_Category
     *
     * @param para
     * @return ProductCategory
     */
    public int addProductCategory(String[] para) {
        String sql = "insert into Product_Category (category_name) values (?)";
        return jdbcTemplate.update(sql, para, new int[] {java.sql.Types.VARCHAR});
    }

    /**
     * delete Product_Category by a given id
     *
     * @param id
     * @return int
     */
    public int deleteProductCategoryById(String id) {
        String sql = "delete from Product_Category where category_id = ?";
        return jdbcTemplate.update(sql, 
                new Object[] {id}, 
                new int[] {java.sql.Types.INTEGER});
    }
}
