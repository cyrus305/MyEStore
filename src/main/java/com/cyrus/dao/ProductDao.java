package com.cyrus.dao;

import java.util.List;

import com.cyrus.model.Product;

public interface ProductDao {

	void addProduct(Product product);

	public Product getProductById(Long id);

	public List<Product> getAllProduct();

	boolean deleteProduct(Long id);

}
