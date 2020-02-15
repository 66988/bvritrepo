package com.cg.pms.service;

import java.util.List;
import java.util.Random;


import com.cg.pms.bean.Product;
import com.cg.pms.dao.ProductDao;
import com.cg.pms.dao.ProductDaoMapImpl;
import com.cg.pms.exception.ProductException;



public class ProductServiceImpl implements ProductService
{

	
	private ProductDao productDao;
	public ProductServiceImpl()
	{
		productDao= new ProductDaoMapImpl();
	}
	
	public boolean validateName(String productName)
	{
		boolean flag=false;
		
		flag=productName.matches("[a-zA-z]+");
		return flag;
	}

	public int addProduct(Product product) throws ProductException 
	{

	String name=product.getProductName();
	boolean flag=validateName(name);
	if(!flag)
	{
		throw new ProductException("Name should contain only characters");
	}
	
	Random random=new Random();
	
	int id=random.nextInt(100)+1000;
	product.setProductId(id);
	id=productDao.addProduct(product);
		return id;
	}

	public Product findProductById(int productId) throws ProductException
	{
       String empid=String.valueOf(productId);
       boolean flag=empid.matches("[0-9]{4}");
       if(!flag)
       {
    	   throw new ProductException("ID should be 4 digits");
       }
       Product employee=productDao.findProductById(productId);
		return employee;
	}

	public Product deleteProductById(int productId) throws ProductException
	{
		
		return productDao.deleteProductById(productId);
	}

	public List<Product> findAllProduct() throws ProductException 
	{
		return productDao.findAllProduct();
		//return employee;
	}

	
}

