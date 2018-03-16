package br.com.integrator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	Properties prop = null;
	
	private Properties getProp() throws IOException { 
		Properties props = new Properties(); 
		
	 
		InputStream file = this.getClass().getResourceAsStream("/dados.conf");
		
		if (file != null) {
			System.out.println(this.getClass().getResource("/dados.conf"));
			props.load(file); 
			
		}
		file.close();
		
		
		
		return props;
		
	}
	
	public String getUser(){
		try {
			return getProp().getProperty("prop.user");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getPass(){
		try {
			return getProp().getProperty("prop.pass");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getHost(){
		try {
			return getProp().getProperty("prop.host");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getPort(){
		try {
			return getProp().getProperty("prop.port");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}	

}
