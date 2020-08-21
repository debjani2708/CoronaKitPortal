package com.wellsfargo.fsd.sba.ck.dao;

import java.util.List;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.KitDetails;

public interface KitDao {

	List<KitDetails> getAllProduct() throws CoronaKitException;
	KitDetails getById(int productId) throws CoronaKitException;
	KitDetails updateQuantity(KitDetails order) throws CoronaKitException;
}
