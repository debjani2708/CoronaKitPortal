package com.wellsfargo.fsd.sba.ck.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;

public class ProductDaoJdbcImpl implements ProductDao{
	
	public static final String INS_PRD_QRY = 
			"INSERT INTO coronaKit(Item_Id,Item_Name,Item_Desc,Quantity,Price) VALUES(?,?,?,?,?)";
	public static final String UPD_PRD_QRY =
			"UPDATE coronaKit SET Item_Name=?,Item_Desc=?,Quantity=?,Price=? WHERE Item_Id=?";
	public static final String DEL_PRD_QRY = 
			"DELETE FROM coronaKit WHERE Item_Id=?";
	public static final String GET_BY_ID_PRD_QRY = 
			"SELECT Item_Id,Item_Name,Item_Desc,Quantity,Price FROM coronaKit WHERE Item_Id=?";
	public static final String GET_ALL_PRD_QRY =
			"SELECT Item_Id,Item_Name,Item_Desc,Quantity,Price FROM coronaKit";

	@Override
	public ProductDetails add(ProductDetails product) throws CoronaKitException {
		if (product != null) {

			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_PRD_QRY);) {

				pst.setInt(1, product.getProductId());
				pst.setString(2, product.getProductName());
				pst.setString(3, product.getProductDescription());
				pst.setInt(4, product.getProductQuantity());
				pst.setDouble(5, product.getProductPrice());

				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new CoronaKitException("An error occured, Could not add the Product details!");
			}
		}
		return product;
	}

	@Override
	public ProductDetails edit(ProductDetails product) throws CoronaKitException {
		if (product != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_PRD_QRY);) {
				
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setInt(3, product.getProductQuantity());
				pst.setDouble(4, product.getProductPrice());
				pst.setInt(5, product.getProductId());

				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new CoronaKitException("An error occured, Could not Edit the Product details!");
			}
		}
		return product;
	}

	@Override
	public boolean deleteById(int itemId) throws CoronaKitException {
		
		boolean isDeleted = false;
		
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_PRD_QRY);) {

			pst.setInt(1, itemId);
			int rowsCount = pst.executeUpdate();
			
			isDeleted= rowsCount>0 ;

		} catch (SQLException exp) {
			throw new CoronaKitException("An error occured, Could not delete the product details!");
		}

		return isDeleted;
	}

	@Override
	public List<ProductDetails> getAllProduct() throws CoronaKitException {
		
		List<ProductDetails> products = new ArrayList<>();
			
		 try (Connection con = ConnectionFactory.getConnection();
	                PreparedStatement pst = con.prepareStatement(GET_ALL_PRD_QRY);) { 
			 
	            ResultSet rs = pst.executeQuery();
	            
	            while(rs.next()) {
	            	
	                ProductDetails product = new ProductDetails();
	                
	                product.setProductId(rs.getInt(1));
	                product.setProductName(rs.getString(2));
	                product.setProductDescription(rs.getString(3));
	                product.setProductQuantity(rs.getInt(4));
	                product.setProductPrice(rs.getDouble(5));
	                
	                products.add(product);
	                
	            }
	            if(products.isEmpty()) {
	            	products=null;
	            }
	        } catch (SQLException exp) {
	            throw new CoronaKitException("An error occured, Could not retrive the product details!");
	        }
	                
	        return products;
	}
	
	@Override
	public ProductDetails getById(int productId) throws CoronaKitException {
		
        ProductDetails product=null;     
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(GET_BY_ID_PRD_QRY);) {        
            pst.setInt(1, productId);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
            	product = new ProductDetails();
            	
            	product.setProductId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductDescription(rs.getString(3));
                product.setProductQuantity(rs.getInt(4));
                product.setProductPrice(rs.getDouble(5));
            }
            
        } catch (SQLException exp) {
            throw new CoronaKitException("An error occured, Could not retrive the Product details!");
        }
        
        return product;
    }

}
