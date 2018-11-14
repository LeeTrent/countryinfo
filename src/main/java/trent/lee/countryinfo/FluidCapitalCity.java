package trent.lee.countryinfo;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class FluidCapitalCity {
	
	public static void main(String[] args) {

		String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <web:CapitalCity>\n" + 
				"         <web:sCountryISOCode>FR</web:sCountryISOCode>\n" + 
				"      </web:CapitalCity>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
	
		try {
			Request.Post("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
			        .useExpectContinue()
			        .addHeader("Content-type", "text/xml; charset=UTF-8")
			        .version(HttpVersion.HTTP_1_1)
			        .bodyString(requestBody, ContentType.APPLICATION_XML)
			        //.execute().returnContent().asStream();
					.execute().saveContent(new File("CapitalCity.out"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
