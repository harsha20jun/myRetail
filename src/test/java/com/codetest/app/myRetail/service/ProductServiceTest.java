package com.codetest.app.myRetail.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.codetest.app.myRetail.repository.ProductRepository;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productRepository;
	
	/*@Mock
	ConnectHttpClient ConnectHttpClient;*/

	/**
	 * Setup for Mockito before any test run.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
}
