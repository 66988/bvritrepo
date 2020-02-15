package com.cg.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.pms.bean.Product;
import com.cg.pms.exception.ProductException;
import com.cg.pms.util.DBUtil;


	public class ProductDaoMapImpl implements ProductDao
	{
	  private Map<Integer, Product> map;
	  
	  public ProductDaoMapImpl()
	  {
		  map=new HashMap<Integer,Product>();
	  }
	  
	  
		@Override
		public int addProduct(Product product) throws ProductException
		{
			int id=0;
			
			try
			{
			   Connection connection=DBUtil.getConnection();
			   String cmd="insert into product_tb1 values(?,?,?)";
			   PreparedStatement pstmt=connection.prepareStatement(cmd);
			   pstmt.setInt(1,product.getProductId());
			   pstmt.setString(2, product.getProductName());
			   pstmt.setDouble(3, product.getPrice());
			   int n=pstmt.executeUpdate();
			   connection.close();
			}
			catch(Exception e)
			{
				throw new ProductException(e.getMessage());
			}
			id=product.getProductId();
			return id;
		}

		
		
		@Override
		public Product findProductById(int productId) throws ProductException
		{
			Product product=new Product();
			try
			{
			   Connection connection=DBUtil.getConnection();
			   String cmd="select empid,empname,empsal from employee_tb1 where empid=?";
			   PreparedStatement pstmt=connection.prepareStatement(cmd);
			   pstmt.setInt(1,productId);
			  
			   ResultSet rst=pstmt.executeQuery();
			   if(rst.next())
			   {
				   int id=rst.getInt("empid");
				   String name=rst.getString("empname");
				   double sal=rst.getDouble("empsal");
				   product.setProductId(id);
				   product.setProductName(name);
				   product.setPrice(sal);
			   }
			   else
			   {
				   throw new ProductException(productId +"doesnot exsist");
			   }
			   
			   connection.close();
			}
			catch(Exception e)
			{
				throw new ProductException(e.getMessage());
			}
			return product;
		}

		
		@Override
		public Product deleteProductById(int productId) throws ProductException 
		{
			Product employee=new Product();
		
			try
			{
			    Connection connection=DBUtil.getConnection();
			   String cmd="delete from employee_tb1 where employeeid=(?)";
			   PreparedStatement pstmt=connection.prepareStatement(cmd);
			  
			  
			pstmt.setInt(1,productId);

			   int n=pstmt.executeUpdate();
			   System.out.println("Delete Success");
			   connection.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			return employee;
			   
		}

		@Override
		public List<Product> findAllProduct() throws ProductException
		{
			Collection<Product> col=map.values();
			List<Product> list=new ArrayList<Product>(col);
			return list;
		}

}
