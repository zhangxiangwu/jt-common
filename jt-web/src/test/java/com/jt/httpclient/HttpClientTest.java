package com.jt.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void httpClientTest() throws ClientProtocolException, IOException {
		
		/**
		 * 0.实例化请求类
		 * 1.url
		 * 2.请求方式
		 * 3.发送请求
		 */
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		String url = "http://www.baidu.com";
		
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse result = client.execute(httpGet);
		
		if(result.getStatusLine().getStatusCode() == 200) {
			
			String entity = EntityUtils.toString(result.getEntity(),"UTF-8");
			
			System.out.println(entity);
		}
	
	}
	
}
