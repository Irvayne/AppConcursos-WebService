package br.com.wolfes.scraping;

import com.gargoylesoftware.htmlunit.WebClient;

public abstract class Scraping {
	

	public static WebClient getWebClient(){
		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		
		return client;
	}
}
