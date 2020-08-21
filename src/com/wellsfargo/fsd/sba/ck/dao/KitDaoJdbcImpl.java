package com.wellsfargo.fsd.sba.ck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.KitDetails;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;

public class KitDaoJdbcImpl implements KitDao{

	public static final String GET_ALL_PRD_QRY =
			"SELECT Item_Id,Item_Name,Item_Desc,Quantity,Price FROM coronaKit where Quantity > 0";
	public static final String GET_BY_ID_QRY = 
			"SELECT Item_Id,Item_Name,Item_Desc,Quantity,Price FROM coronaKit WHERE Item_Id=?";
	public static final String UPD_QRY = 
			"UPDATE coronakit set Quantity=?-? where Item_Id=?";
	
	

	@Override
	public List<KitDetails> getAllProduct() throws CoronaKitException {

		List<KitDetails> products = new ArrayList<>();
			
		 try (Connection con = ConnectionFactory.getConnection();
	                PreparedStatement pst = con.prepareStatement(GET_ALL_PRD_QRY);) { 
			 
	            ResultSet rs = pst.executeQuery();
	            
	            while(rs.next()) {
	            	
	                KitDetails product = new KitDetails();
	                
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
	public KitDetails getById(int productId) throws CoronaKitException {
		
		KitDetails product=null;     
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement pst = con.prepareStatement(GET_BY_ID_QRY);) {        
            pst.setInt(1, productId);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
            	product = new KitDetails();
            	
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
	
	@Override
	public KitDetails updateQuantity(KitDetails order) throws CoronaKitException {
		if (order != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_QRY);) {
				
				pst.setInt(1, order.getProductQuantity());
				pst.setInt(2, order.getOrderQuantity());
				pst.setInt(3, order.getProductId());

				pst.executeUpdate();
				

			} catch (SQLException exp) {
				throw new CoronaKitException("An error occured, Could not update the Product quantity details!");
			}
		}
		return order;
	}
}
