/**
 * @author ${Nithin.i}
 *
 * @date ${27-07-2019}
 */

package com.empover.rest.jersey;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/restService")
public class RestService {
	
	private static Map<String, CustomerPojo> customerOrdersMap = new HashMap<String, CustomerPojo>();
	
	//Create Order
	@POST
	@Path("/createOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public String createOrder(String jsonRequestData) {
		
		try {
			CustomerPojo customerObject = new ObjectMapper().readValue(jsonRequestData, CustomerPojo.class);
			Integer.parseInt(customerObject.getOrderQuantity().trim());
			
			if(customerOrdersMap != null) {
				customerObject.setId((""+customerOrdersMap.size() == null || "0".equals(""+customerOrdersMap.size())) ? ""+1 : ""+(customerOrdersMap.size()+1));
				customerObject.setOrderCreatedDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
				customerObject.setDispatched(false);
			}
			customerOrdersMap.put(customerObject.getId().trim(), customerObject);
			return "Order Created Successfully, Use Reference Number "+customerObject.getId()+" for further track";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed, Error in Creation";
		}
		
	}
	
	//Get Order
	@POST
	@Path("/getOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrder(String jsonRequestData) {
		
		try {
			CustomerPojo customerObject = new ObjectMapper().readValue(jsonRequestData, CustomerPojo.class);
			Integer.parseInt(customerObject.getId().trim());
			
			if(customerOrdersMap != null && customerOrdersMap.size() > 0)
				return customerOrdersMap.get(customerObject.getId()).toString();
			else 
				return "No order details are found";
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Invalid Order reference Number";
		} catch(Exception e) {
			e.printStackTrace();
			return "No order details are found";
		}
	}
	
	//Get All Orders
	@POST
	@Path("/getAllOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllOrder() {
		
		try {
			return customerOrdersMap.toString();
			
		}catch(Exception e) {
			e.printStackTrace();
			return "No order details are found";
		}
	}
	
	//Update Order
	@POST
	@Path("/updateOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateOrder(String jsonRequestData) {
		
		try {
			CustomerPojo customerObject = new ObjectMapper().readValue(jsonRequestData, CustomerPojo.class);
			Integer.parseInt(customerObject.getId().trim());
			CustomerPojo updatedCustomerObject = customerOrdersMap.get(customerObject.getId().trim());
			if(updatedCustomerObject.getDispatched()) {
				return "Order has been dispatched, No Update is Possible";
			}
			updatedCustomerObject.setOrderQuantity(customerObject.getOrderQuantity());
			updatedCustomerObject.setOrderCreatedDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
			customerOrdersMap.put(customerObject.getId().trim(), updatedCustomerObject);
			return "Order Updated Successfully for given reference Number "+customerObject.getId();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Invalid Order reference Number";
		} catch(Exception e) {
			e.printStackTrace();
			return "400 bad request, No order details are found";
		}
	}
	
	//Fulfill Order
	@POST
	@Path("/dispatchOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public String dispatchOrder(String jsonRequestData) {
		
		try {
			CustomerPojo customerObject = new ObjectMapper().readValue(jsonRequestData, CustomerPojo.class);
			Integer.parseInt(customerObject.getId().trim());
			CustomerPojo updatedCustomerObject = customerOrdersMap.get(customerObject.getId().trim());
			updatedCustomerObject.setDispatched(true);
			customerOrdersMap.put(customerObject.getId().trim(), updatedCustomerObject);
			return "Order Dispatched Successfully for given reference Number "+customerObject.getId();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Invalid Order reference Number";
		} catch(Exception e) {
			e.printStackTrace();
			return "400 bad request, No order details are found";
		}
	}
	
}
