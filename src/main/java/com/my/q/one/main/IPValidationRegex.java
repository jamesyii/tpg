package com.my.q.one.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPValidationRegex {

	private Pattern pattern;
	private Matcher matcher;

	
	//IP address regex pattern
	private static final String IP_ADDRESS_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
													"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
													"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
													"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public IPValidationRegex() {
		pattern = Pattern.compile(IP_ADDRESS_REGEX);
	}
	
	/**
	 * Validate if a given string is an ip or not
	 * @param ip
	 * @return 
	 */
	public boolean validateIp(String ip) {
		matcher = pattern.matcher(ip);
		return matcher.matches();	
	}
	
	/**
	 * Get list of IPs from file in resources folder
	 * @param fileName
	 * @return
	 */
	public List<String> getIpList(String fileName) {

		List<String> ipList = new ArrayList<String>();

		try {
			
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream is = loader.getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null)
			{
				ipList.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ipList;
	}
	
	public static void main (String args[]) {
		
		//the file is at resources folder.
		String fileName =  "question1test.txt";
		
		IPValidationRegex ivr = new IPValidationRegex();
		List<String> ipList = ivr.getIpList(fileName);
		
		for (String ip : ipList) {
			
			if(ivr.validateIp(ip)) {
				System.out.println("Ip address : "+ip+" is valid");
			}else {
				System.out.println("Ip address : "+ip+" is invalid");
			}
		}
		
	}
	
}
