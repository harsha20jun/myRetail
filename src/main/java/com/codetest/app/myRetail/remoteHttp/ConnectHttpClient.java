/**
 * 
 */
package com.codetest.app.myRetail.remoteHttp;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codetest.app.myRetail.exception.MyRetailException;

/**
 * @author rpcha
 *
 */
@Component
public class ConnectHttpClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product-api-endpoint}")
	private String apiEndpointURL;

	private String product_URI = "/v2/pdp/tcin/";
	private String productName= null;


	public String getProductNameByRemoteCall(String productId) throws MyRetailException{

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiEndpointURL+product_URI+productId).queryParam("excludes","taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");

			// Send request with GET method, and Headers.	
			String jsonResponse = restTemplate.getForObject(builder.build().encode().toUri(),String.class);

			if(jsonResponse != null) {
				JSONObject jsonObject=new JSONObject(jsonResponse);

				if(jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description") != null) {
					JSONObject productDescription = jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description");
					productName = productDescription.getString("title");
				}
				else {
					throw new MyRetailException(HttpStatus.NO_CONTENT.value() ,"The title does not exists for the product" );
				}
			}
		} catch (RestClientException e) {
			throw new MyRetailException(HttpStatus.NOT_FOUND.value() ,"Product Remote API unavailable");
		}
		return productName;
	}


}
