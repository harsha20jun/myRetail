/**
 * 
 */
package com.codetest.app.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codetest.app.myRetail.entity.Product;

/**
 * @author rpcha
 *
 */
public interface ProductRepository extends MongoRepository<Product,String> {
	
	
	/**
	 * @param productId
	 * @return
	 */
	public Product findProductByproductId(String productId);
	

}
