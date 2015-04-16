/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Product;
import Bean.ProductCategory;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yanyanzhou
 */
public class ZZTest {

    public static void main(String[] args) throws Exception {
        try {
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            ProductDao dao = (ProductDao) ac.getBean("productDao");

//            String[] p={"bba","130","23","2","32"};
//            String[] a={"zzz","230","30","2","32","3"};
//            System.out.println(dao.InsertProduct(p));
//            System.out.println(dao.InsertProduct(a));
            System.out.println("DeleteProductByID " + dao.DeleteProductByID("3"));
//            List<Product> list = dao.GetAllProduct();
//            for (Product e : list) {
//                System.out.println(e.getProduct_id() + " " + e.getProduct_name());
//            }
            

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
