package com.wellsfargo.fsd.sba.ck.dao;

import java.util.List;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;

public interface ProductDao {

	ProductDetails add(ProductDetails product) throws CoronaKitException;
	ProductDetails edit(ProductDetails product) throws CoronaKitException;
	boolean deleteById(int itemId) throws CoronaKitException;
	
	List<ProductDetails> getAllProduct() throws CoronaKitException;
	ProductDetails getById(int productId) throws CoronaKitException;
}
