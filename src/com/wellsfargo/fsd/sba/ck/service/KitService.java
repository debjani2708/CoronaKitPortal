package com.wellsfargo.fsd.sba.ck.service;

import java.util.List;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.KitDetails;

public interface KitService {

     List<KitDetails> getAllProducts() throws CoronaKitException;
     KitDetails getProduct(int productId) throws CoronaKitException;
     
     KitDetails validateAndSave(KitDetails order) throws CoronaKitException; 
}
