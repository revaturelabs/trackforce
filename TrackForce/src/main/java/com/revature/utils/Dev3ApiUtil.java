package com.revature.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.dao.BatchDao;
import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfBatch;
import com.revature.entity.TfTrainer;

public class Dev3ApiUtil {
	private static String encryptedToken=null;
	
	// This information should be changed to be valid login info for the dev3.revature.com
	// "jerry" and "gergich!" are just for the dummy database
	private static final String username = "employeee@yopmail.com";
	private static final String password = "Pass123$";
	private static final String url = "https://dev3.revature.com/caliber";
	
	public static boolean login() {
		String loginJson = "{" + 
				"    \"password\": \"Pass123$\",\r\n" + 
				"    \"userName\": \"employeee@yopmail.com\"" + 
				"}";
		HttpClient httpClient = HttpClientBuilder.create().build();

		try {

		    HttpPost request = new HttpPost(url + "/authentication/login");
		    StringEntity params =new StringEntity(loginJson);
		    params.setContentType("application/json");
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		    
		    String response = httpClient.execute(request, responseHandler);

		    //handle response here...

		    JSONObject obj = new JSONObject(response);
		    if (obj.getInt("statusCode")==200) {
				encryptedToken = obj.getString("data");
				return true;
			} else {
				return false;
			}

		}catch (Exception ex) {

		    //handle exception here
			return false;

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		
	}
	
	public static boolean isLoggedIn() {
		return encryptedToken!=null;
	}
	
	public static List<TfBatch> getBatches() {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		List<TfBatch> batches = new ArrayList<TfBatch>();
		BatchDao batchdao = new BatchDaoImpl();
		
		try {

		    HttpGet request = new HttpGet(url + "/secure/batches");
		    
		    request.addHeader("content-type", "application/json");
		    request.addHeader("encryptedToken", encryptedToken);

		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		    
		    String response = httpClient.execute(request, responseHandler);

		    //handle response here...

		    JSONObject obj = new JSONObject(response);
		    if (obj.getInt("statusCode")==200) {
		    	JSONArray jsonarray = obj.getJSONArray("data");
				for (int i = 0; i < jsonarray.length(); i++) {
//					TfBatch newbatch = new TfBatch(id, location, curriculumName, batchName, startDate, endDate, associates, trainer, coTrainer) 
					String salesforceId = jsonarray.getJSONObject(i).getString("salesforceId");
					TfBatch batch = new TfBatch();
					batch.setSalesforceId(salesforceId);
					batch.setBatchName(jsonarray.getJSONObject(i).getString("name"));
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss +SSSS");
				    Date startDate = dateFormat.parse(jsonarray.getJSONObject(i).getString("startDate"));
				    Date endDate = dateFormat.parse(jsonarray.getJSONObject(i).getString("endDate"));
				    
				    batch.setStartDate(new Timestamp(startDate.getTime()));
				    batch.setEndDate(new Timestamp(endDate.getTime()));
				    
//				    batch.setTrainer(trainer);
					
//					System.out.println(batchdao.g);
				    batches.add(batch);
				    return batches;
				}
			} else {
				return null;
			}
		    
		}catch (Exception ex) {

		    //handle exception here

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		return null;
	}
	
	public TfBatch getBatch(String salesforceId) {
		return null;
	}
}
