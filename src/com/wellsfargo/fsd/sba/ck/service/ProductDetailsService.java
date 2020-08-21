package com.wellsfargo.fsd.sba.ck.service;

import java.util.List;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;


public interface ProductDetailsService {

	ProductDetails validateAndAdd(ProductDetails product) throws CoronaKitException;
	ProductDetails validateAndSave(ProductDetails product) throws CoronaKitException;   
    boolean deleteProduct(int productId) throws CoronaKitException;
    
    ProductDetails getProduct(int productId) throws CoronaKitException;
    List<ProductDetails> getAllProducts() throws CoronaKitException;
}
