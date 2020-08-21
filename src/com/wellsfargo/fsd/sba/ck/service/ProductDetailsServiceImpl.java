package com.wellsfargo.fsd.sba.ck.service;

import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.sba.ck.dao.ProductDao;
import com.wellsfargo.fsd.sba.ck.dao.ProductDaoJdbcImpl;
import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;

public class ProductDetailsServiceImpl implements ProductDetailsService{
	
	ProductDao prdDao;
	
	public ProductDetailsServiceImpl() {
		prdDao=new ProductDaoJdbcImpl();
	}
	
	 private boolean isValidProductId(Integer itemId) {
	        return itemId!=null && itemId>0;
	    }
	    
	    private boolean isValidProductName(String pName) {
	        return pName!=null && (pName.length()>3);
	    }
	    
	    private boolean isValidProductDescription(String pDescription) {
	        return pDescription!=null && (pDescription.length()>3);
	    }
	    
	    private boolean isValidQuantity(Integer pQuantity) {
	        return pQuantity!=null && pQuantity>=0;
	    }
	    
	    private boolean isValidPrice(Double pPrice) {
	        return pPrice!=null && pPrice>=0;
	    }
	 
	    
	    private boolean isValidProduct(ProductDetails product) throws CoronaKitException{
	        boolean isValid=true;
	        
	        List<String> errMsgs = new ArrayList<>();
	        
	        if(product!=null) {
	            if(!isValidProductId(product.getProductId())) {
	                isValid=false;
	                errMsgs.add("Product Id must be a positive non-repetative number");
	            }
	            if(!isValidProductName(product.getProductName())) {
	                isValid=false;
	                errMsgs.add("Product Name must be relevant to product and greater than 3 chars in length");
	            }
	            if(!isValidProductDescription(product.getProductDescription())) {
	                isValid=false;
	                errMsgs.add("Product description must be relevant to product and greater than 3 chars in length");
	            }
	            if(!isValidQuantity(product.getProductQuantity())) {
	                isValid=false;
	                errMsgs.add("Product quanity must be zero or a postive non-zero number");
	            }
	            if(!isValidPrice(product.getProductPrice())) {
	                isValid=false;
	                errMsgs.add("Product price must be a postive non-zero number");
	            }
	            
	            if(!errMsgs.isEmpty()) {
	                throw new CoronaKitException(errMsgs.toString());
	            }
	        }else {
	            isValid=false;
	        }
	        
	        return isValid;
	    }

	@Override
	public ProductDetails validateAndAdd(ProductDetails product) throws CoronaKitException {
		if(isValidProduct(product)) {
			prdDao.add(product);
		}
		return product;
	}

	@Override
	public ProductDetails validateAndSave(ProductDetails product) throws CoronaKitException {
		if(isValidProduct(product)) {
			prdDao.edit(product);
		}
		return product;
	}

	@Override
	public boolean deleteProduct(int productId) throws CoronaKitException {
		return prdDao.deleteById(productId);
		
	}

	@Override
	public List<ProductDetails> getAllProducts() throws CoronaKitException {
		return prdDao.getAllProduct();
	}

	@Override
	public ProductDetails getProduct(int productId) throws CoronaKitException {
		return prdDao.getById(productId);
	}

}
