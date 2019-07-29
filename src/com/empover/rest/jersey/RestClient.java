/**
 * @author ${Nithin.i}
 *
 * @date ${27-07-2019}
 */

package com.empover.rest.jersey;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestClient {

//	  Variables declaration
	  private static Scanner scanner = new Scanner(System.in);
	  private static URL url = null;
	  private static HttpURLConnection connection = null;
	  private static BufferedReader bufferedReader = null;
	  private static OutputStream outputStream = null;
	  private static String output = null;
	  private static String orderQuantity = null;
	  private static String input = null;
	  private static String id = null;
	  private static String httpUrl = "http://localhost:8080/RestClientOperations/JavaCodeRest/restService/";
	  
	public static void main(String[] args) {
	  try {

		  System.out.println("\nAvailable Operation's - Codes are : Create - 1, Get - 2, GetAll - 3, Update - 4, Fulfil - 5, Exit - 6");
		  System.out.println("Please Enter any one of the code above");
		  
		  clientOperations(scanner.nextLine());
		  System.out.println("\nOperations completed successfully..!");
		  
		  } catch (Exception e) {
			e.printStackTrace();
		  } 
	}
	
	public static String initializingMethod() {
		main(null);
		return "";
	}
	
	private static String clientOperations(String operation) {
		
		try {
			
			if(operation != null && !operation.equals("")) {
				  // Create Order
				  if(operation.equalsIgnoreCase("1")) {
					  
					  System.out.println("Creating Order Enter Number of bricks : ");
					  orderQuantity = scanner.nextLine();
					  input = "{\"orderQuantity\":\""+orderQuantity+"\"}";
					  url = new URL(httpUrl+"createOrder");
					  
				  } 
				  // Get Single Order
				  else if(operation.equalsIgnoreCase("2")) {
					  
					  System.out.println("Getting Order Enter Reference Number : ");
					  id = scanner.nextLine();
					  input = "{\"id\":\""+id+"\"}";
					  url = new URL(httpUrl+"getOrder");
					  
				  } 
				  // Get All Orders
				  else if(operation.equalsIgnoreCase("3")) {
					  
					  url = new URL(httpUrl+"getAllOrder");
					  
				  } 
				  // Update Order
				  else if(operation.equalsIgnoreCase("4")) {
					  
					  System.out.println("Updating Order Enter Reference Number : ");
					  id = scanner.nextLine();
					  System.out.println("Enter quantity to update : ");
					  orderQuantity = scanner.nextLine();
					  input = "{\"id\":\""+id+"\",\"orderQuantity\":\""+orderQuantity+"\"}";
					  url = new URL(httpUrl+"updateOrder");
					  
				  } 
				  //Fulfill Order
				  else if(operation.equalsIgnoreCase("5")) {
					  
					  System.out.println("Getting Dispatched Enter Reference Number : ");
					  id = scanner.nextLine();
					  input = "{\"id\":\""+id+"\"}";
					  url = new URL(httpUrl+"dispatchOrder");
					  
				  } else {
					  System.out.println("Not a valid request");
					  return null;
				  }
				    
				  	connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestMethod("POST");
					connection.setRequestProperty("Content-Type", "application/json");
					outputStream = connection.getOutputStream();
					outputStream.write(input.getBytes());
					outputStream.flush();
					bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
					while ((output = bufferedReader.readLine()) != null) {
						System.out.println("Final Result : "+output);
					}
				  
				  System.out.println("\nContinue Operation's - Code are : Create - 1, Get - 2, GetAll - 3, Update - 4, Fulfil - 5, Exit - 6");
				  System.out.println("Please Enter any one of the code above");
				  
				  operation = scanner.nextLine();
				  
				  if(operation != null && !operation.equals("6") ) {
					  clientOperations(operation);
				  } 
				  
			  }
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
}