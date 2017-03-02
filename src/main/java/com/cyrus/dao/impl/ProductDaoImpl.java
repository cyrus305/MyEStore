package com.cyrus.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cyrus.dao.ProductDao;
import com.cyrus.model.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory session;

	public void addProduct(Product product) {
		session.getCurrentSession().saveOrUpdate(product);
	}

	public Product getProductById(Long id) {
		return (Product) session.getCurrentSession().get(Product.class, id);

	}
	
	public List<Product> getAllProduct() {
		 return session.getCurrentSession().createQuery("from Product").list();
	}	 

	/*public List<Product> getAllProduct() {
		Session session2 = session.getCurrentSession();
		org.hibernate.Query query = session2.createQuery("from Product");
		List<Product> products = query.list();
		System.out.println(products);
		session2.flush();
		return products;
	}*/

	public boolean deleteProduct(Long id) {
		try {
			session.getCurrentSession().delete(getProductById(id));
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

}
