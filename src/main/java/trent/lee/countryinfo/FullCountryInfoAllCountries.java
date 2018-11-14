package trent.lee.countryinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class FullCountryInfoAllCountries {

	public static void main(String[] args) {
		
		String url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
		
		String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <web:FullCountryInfoAllCountries/>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";	
		
		////////////////////////////////////////////////////////
		// Instantiate HttpClient
		////////////////////////////////////////////////////////
		HttpClient client = HttpClientBuilder.create().build();

		////////////////////////////////////////////////////////
		// Create Post Request
		////////////////////////////////////////////////////////		
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "text/xml; charset=UTF-8");
		try {
			post.setEntity(new StringEntity(requestBody));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		
		////////////////////////////////////////////////////////
		// Execute Post Request
		////////////////////////////////////////////////////////			
		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		////////////////////////////////////////////////////////
		// Process Response
		////////////////////////////////////////////////////////	
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());	

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		////////////////////////////////////////////////////////
		// Write Response Pay Load to Console
		////////////////////////////////////////////////////////	
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
