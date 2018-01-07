/**
 * 
 */
package com.codetest.app.myRetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codetest.app.myRetail.exception.MyRetailException;
import com.codetest.app.myRetail.service.ProductService;
import com.codetest.app.myRetail.vo.response.ErrorResponse;
import com.codetest.app.myRetail.vo.response.ProductVO;

/**
 * @author rpcha
 *
 */

@RequestMapping ("/products")
@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductVO> getProductById(@PathVariable("id") String productId) throws MyRetailException {
		
		ProductVO productResponse= new ProductVO();
		productResponse = productService.getProductById(productId);
		
		System.out.println(productResponse);
		return new ResponseEntity<ProductVO>(productResponse, HttpStatus.OK);
	}
	
    @ExceptionHandler(MyRetailException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(MyRetailException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(ex.getErrorCode());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<UpdateResponse> updatePrice(@RequestBody ProductVO product,
			@PathVariable("id") String productId) {
		if (!product.productId.equalsIgnoreCase(productId)) {
			throw new MyRetailException();
		}
		try {
			productService.updateProductById(product);
		} catch (Exception e) {
			logger.debug("Product Not Found Exception while update " + e);
			throw new MyRetailException();
		}

		return new ResponseEntity<UpdateResponse>(
				new UpdateResponse(HttpStatus.OK.value(), "Product price has been updated"), HttpStatus.OK);
	}*/
    
		
}
