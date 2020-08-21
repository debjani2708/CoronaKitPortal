package com.wellsfargo.fsd.sba.ck.service;

import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.sba.ck.dao.KitDao;
import com.wellsfargo.fsd.sba.ck.dao.KitDaoJdbcImpl;
import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.KitDetails;
import com.wellsfargo.fsd.sba.ck.model.UserDetails;

public class KitServiceImpl implements KitService{

	KitDao kitDao;
	
	public KitServiceImpl() {
		kitDao=new KitDaoJdbcImpl();
	}
	
	 	private boolean isValidProductId(Integer itemId) {
	        return itemId!=null && itemId>0;
	    }
	 	 private boolean isValidQuantity(Integer pQuantity) {
		        return pQuantity!=null && pQuantity>=0;
		}
	    
	    private boolean isValidProduct(KitDetails order) throws CoronaKitException{
	        boolean isValid=true;
	        
	        List<String> errMsgs = new ArrayList<>();
	        
	        if(order!=null) {
	        	 if(!isValidProductId(order.getProductId())) {
		                isValid=false;
		                errMsgs.add("Product Id must be a positive non-repetative number");
		            }
	        	 if(!isValidQuantity(order.getProductQuantity())) {
		                isValid=false;
		                errMsgs.add("Product quanity must be zero or a postive non-zero number");
		            }
	        	 if(!isValidQuantity(order.getOrderQuantity())) {
		                isValid=false;
		                errMsgs.add("Product quanity must be zero or a postive non-zero number");
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
	public List<KitDetails> getAllProducts() throws CoronaKitException {
		return kitDao.getAllProduct();
	}

	@Override
	public KitDetails getProduct(int productId) throws CoronaKitException {
		return kitDao.getById(productId);
	}

	@Override
	public KitDetails validateAndSave(KitDetails order) throws CoronaKitException {
		if(isValidProduct(order)) {
			kitDao.updateQuantity(order);
		}
		return order;
	}

}
