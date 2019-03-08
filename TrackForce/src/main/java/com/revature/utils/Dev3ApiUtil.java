package com.revature.utils;

import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.revature.dao.AssociateDao;
import com.revature.dao.BatchDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfRole;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import static com.revature.utils.LogUtil.logger;

public class Dev3ApiUtil {
	
	private static TrainerService trainServ = new TrainerService();
    private static AssociateService assServ = new AssociateService();
    private static UserService userv = new UserService();
    private static BatchDao batchdao = new BatchDaoImpl();
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
					JSONObject data = jsonarray.getJSONObject(i);
					String salesforceId = data.getString("salesforceId");
					TfBatch batch = new TfBatch();
					batch.setSalesforceId(salesforceId);
					batch.setBatchName(data.getString("name"));
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss +SSSS");
				    Date startDate = dateFormat.parse(data.getString("startDate"));
				    Date endDate = dateFormat.parse(data.getString("endDate"));
				    
				    batch.setStartDate(new Timestamp(startDate.getTime()));
				    batch.setEndDate(new Timestamp(endDate.getTime()));
				    
				    TfTrainer trainer = new TfTrainer();
				    trainer.setFirstName(data.getJSONObject("trainer").getString("firstName"));
				    trainer.setLastName(data.getJSONObject("trainer").getString("lastName"));
				    
				    batch.setTrainer(trainer);
					

				    batches.add(batch);
				    
				}
				return batches;
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
	
	public static List<TfBatch> getBatchesEndingWithinLastNMonths(int nMonths) {
		
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		List<TfBatch> batches = new ArrayList<TfBatch>();
		SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss +SSSS");
		SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1*nMonths);
		
		String todaysDate = shortDateFormat.format(cal.getTime());
		try {
			Set<String> curruculumNames = new HashSet<String>();
		    HttpGet request = new HttpGet(url + "/secure/batches");
		    
		    request.addHeader("content-type", "application/json");
		    request.addHeader("encryptedToken", encryptedToken);
		    URI uriBuilder = new URIBuilder(request.getURI()).addParameter("endDateAfter", todaysDate).build();

		    request.setURI(uriBuilder);
		    
		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		    
		    String response = httpClient.execute(request, responseHandler);

		    //handle response here...

		    JSONObject obj = new JSONObject(response);
		    if (obj.getInt("statusCode")==200) {
		    	JSONArray jsonarray = obj.getJSONArray("data");
				for (int i = 0; i < jsonarray.length(); i++) {
//					TfBatch newbatch = new TfBatch(id, location, curriculumName, batchName, startDate, endDate, associates, trainer, coTrainer) 
					JSONObject data = jsonarray.getJSONObject(i);
					String salesforceId = data.getString("salesforceId");
					TfBatch batch = new TfBatch();
					batch.setSalesforceId(salesforceId);
					batch.setBatchName(data.getString("name"));
					
					
				    Date startDate = fullDateFormat.parse(data.getString("startDate"));
				    Date endDate = fullDateFormat.parse(data.getString("endDate"));
				    
				    batch.setStartDate(new Timestamp(startDate.getTime()));
				    batch.setEndDate(new Timestamp(endDate.getTime()));
				    
				    TfTrainer trainer = new TfTrainer();
				    trainer.setFirstName(data.getJSONObject("trainer").getString("firstName"));
				    trainer.setLastName(data.getJSONObject("trainer").getString("lastName"));
				    
				    batch.setTrainer(trainer);
					

				    batches.add(batch);
				    if (!data.isNull("skill")) {
						curruculumNames.add(data.getString("skill"));
					}
				    
				}
				System.out.println(curruculumNames);
				return batches;
			} else {
				return null;
			}
		    
		}catch (Exception ex) {

		    //handle exception here
			ex.printStackTrace();

		} finally {
		    //Deprecated
		    //httpClient.getConnectionManager().shutdown(); 
		}
		return null;
	}

	public static boolean loadBatchAndAssociatesIntoDB(String salesforceId) {
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		BatchDao batchdao = new BatchDaoImpl();
		
		try {

		    HttpGet request = new HttpGet(url + "/secure/batch/" + salesforceId + "/associates");
		    
		    request.addHeader("content-type", "application/json");
		    request.addHeader("encryptedToken", encryptedToken);

		    ResponseHandler<String> responseHandler=new BasicResponseHandler();
		    
		    String response = httpClient.execute(request, responseHandler);

		    //handle response here...
		    AssociateDao assdao = new AssociateDaoImpl();
		    JSONObject obj = new JSONObject(response);
		    if (obj.getInt("statusCode")==200) {
		    	JSONObject data = obj.getJSONObject("data");
		    	String salesforceIdRes = data.getString("salesforceId");
		    	logger.debug(salesforceIdRes);
		    	TfBatch batch = new TfBatch();
		    	batch.setSalesforceId(salesforceId);
				batch.setBatchName(data.getString("name"));
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss +SSSS");
			    Date startDate = dateFormat.parse(data.getString("startDate"));
			    Date endDate = dateFormat.parse(data.getString("endDate"));
			    
			    batch.setStartDate(new Timestamp(startDate.getTime()));
			    batch.setEndDate(new Timestamp(endDate.getTime()));
			    
			    TfTrainer trainer = getOrBuildTrainer(data);

			    batch.setTrainer(trainer);
			    
			    Set<TfAssociate> associates = new HashSet<TfAssociate>();
			    JSONArray jsonAssocs;
				try {
					jsonAssocs = data.getJSONArray("batchTrainees");
					for (int i = 0; i < jsonAssocs.length(); i++) {
				    	associates.add(getOrBuildAssociate(jsonAssocs.getJSONObject(i)));
				    }
					batch.setAssociates(associates);
				} catch (Exception e) {
					logger.debug("No \"batchTrainees\" field in this batch");
				}
				
			    boolean createdBatch = batchdao.createBatch(batch);
			    
			    //This if statement sets each associate's batch and persists it in the DB
			    if (createdBatch) {
			    	Set<TfAssociate> asses = batch.getAssociates();
			    	
			    	if (asses.size()>0) {
						for (TfAssociate ass : asses) {
							TfAssociate persistentAss = assdao.getAssociate(ass.getId());
							TfBatch persistentBatch = batchdao.getBatchBySalesforceId(batch.getSalesforceId());
							persistentAss.setBatch(persistentBatch);
							assdao.updateAssociate(persistentAss);
							logger.debug("***** " + persistentAss.getUser().getUsername() + " was added to batch: "
									+ batch.getSalesforceId());
						} 
					}
			    	
					return true;
			    } else {
			    	return false;
			    }

			} else {
				return false;
			}
		    
		}catch (Exception ex) {

			//handle exception here
			ex.printStackTrace();

		}
		return false;
	}
	
	private static TfAssociate getOrBuildAssociate(JSONObject jsonAssoc) {

		String email = jsonAssoc.getString("email");
		TfUser user = userv.getUser(email);
		try {
			if (user!=null && user.getId() != 0) {
				return assServ.getAssociateByUserId(user.getId());
			} else {
				user=new TfUser();
				user.setUsername(email);
				user.setPassword(RandomStringUtils.randomAlphanumeric(10));
				user.setIsApproved(1); //What exactly does "approved" mean?
				user.setTfRole(new TfRole(5));
				userv.insertUser(user);
				
				TfAssociate associate = new TfAssociate();
				associate.setFirstName(jsonAssoc.optString("firstName"));
				associate.setLastName(jsonAssoc.optString("lastName"));
				associate.setUser(user);
				
				assServ.createAssociate(associate);
				return assServ.getAssociateByUserId(userv.getUser(email).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static TfTrainer getOrBuildTrainer(JSONObject data) {

		String email = data.getJSONObject("trainer").getString("email");
		TfUser user = userv.getUser(email);
		try {
			if (user!=null && user.getId() != 0) {
				return trainServ.getTrainerByUserId(user.getId());
			} else {
				user=new TfUser();
				user.setUsername(email);
				user.setPassword(RandomStringUtils.randomAlphanumeric(10));
				user.setIsApproved(1);
				user.setTfRole(new TfRole(2));
				userv.insertUser(user);
				
				TfTrainer trainer = new TfTrainer();
			    trainer.setFirstName(data.getJSONObject("trainer").getString("firstName"));
			    trainer.setLastName(data.getJSONObject("trainer").getString("lastName"));
			    trainer.setTfUser(user);
			    trainServ.createTrainer(trainer);
			    return trainServ.getTrainerByUserId(userv.getUser(email).getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return null;
	}
}
