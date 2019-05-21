package com.thyoun.casino;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@ServletComponentScan
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext=
				SpringApplication.run(App.class, args);
		startBrowser("http://localhost:9090");
	}
	
	private static void startBrowser(String url) {
		String os = System.getProperty("os.name").toLowerCase();
	        Runtime rt = Runtime.getRuntime();
		
		try{

		    if (os.indexOf( "win" ) >= 0) {

		        rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);

		    } else if (os.indexOf( "mac" ) >= 0) {

		        rt.exec( "open " + url);

	            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

		        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
		       			             "netscape","opera","links","lynx"};
		        	
		        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
		        StringBuffer cmd = new StringBuffer();
		        for (int i=0; i<browsers.length; i++)
		            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
		        	
		        rt.exec(new String[] { "sh", "-c", cmd.toString() });

	           } else {
	                return;
	           }
	       }catch (Exception e){
		    return;
	       }
	      return;		
	  
	}
}
