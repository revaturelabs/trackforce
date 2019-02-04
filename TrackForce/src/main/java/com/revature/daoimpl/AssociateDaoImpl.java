package com.revature.daoimpl;
import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import static com.revature.utils.HibernateUtil.saveToDB;

import java.util.HashMap;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.id.CompositeNestedGeneratedValueGenerator.GenerationContextLocator;
import org.hibernate.tuple.entity.EntityMetamodel.GenerationStrategyPair;
import org.openqa.selenium.InvalidArgumentException;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.Sessional;

/** Data Access Object implementation to access the associate entity from the Database 
 * @author Josh P. Chris S. Paul C. -1807 iteration */
public class AssociateDaoImpl implements AssociateDao {
	
	private static final String MKTSTS = "marketingStatus";
	private static final String CLIENT = "client";
	
	/** Sessional with instructions on how to approve an associate */
	private static Sessional<Boolean> approveAssociate = (Session session, Object... args) -> {
		TfAssociate temp = session.get(TfAssociate.class, (Integer) args[0]);
		temp.getUser().setIsApproved(TfUser.APPROVED);
		session.update(temp);
		LogUtil.logger.trace("Update Associate via Sessional for AssociateId: " + temp.getId());
		return true;
	};
	
	/** Gets list of associates matching criteria. Used by updated angular front end to perform 
	 * Pagination of results and improve performance.
	 * @author Joshua Pressley-1807
	 * @param startIdx starting index
	 * @param numRes the number of results to return
	 * @param mktStatus the marketing ID
	 * @param clientId the client ID
	 * @param sortText the text to sort by.
	 * @return list of associates matching criteria */
	public List<TfAssociate> getNAssociateMatchingCriteria(int startIdx, int numRes, int mktStatus, int clientId, String sortText, String firstName, String lastName)
	{		
		LogUtil.logger.trace("Hibernate Call to Match an Associate List to a criteria");
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteria = builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root = criteria.from(TfAssociate.class);
		Expression<String> expFirst = builder.lower(root.get("firstName"));
		Expression<String>  expLast = builder.lower(root.get("lastName"));
		sortText = "%" + sortText.toLowerCase() + "%";
		
		if(!firstName.isEmpty() && !lastName.isEmpty()) {
			criteria.where(
					builder.and( 
							builder.like(expFirst, firstName.toLowerCase()),
							builder.like(expLast, lastName.toLowerCase())));
		}
		else if (!sortText.isEmpty()) {
			if (clientId == -1 && mktStatus != -1) {
				criteria.where(builder.and(
						(
								builder.or( builder.like(expFirst, sortText),builder.like(expLast, sortText))),
						builder.equal(root.get(MKTSTS), mktStatus)));
			}
			else if (mktStatus == -1 && clientId != -1) {
				criteria.where(builder.and(
						(
								builder.or( builder.like(expFirst, sortText),builder.like(expLast, sortText))),
						builder.equal(root.get(CLIENT), clientId)));
			}
			else if (mktStatus != -1 && clientId != -1) {
				criteria.where(builder.and(
						(
								builder.or( builder.like(expFirst, sortText),builder.like(expLast, sortText))),
						builder.and(builder.equal(root.get(MKTSTS), mktStatus),builder.equal(root.get(CLIENT), clientId))));
			} else { 
				criteria.where(
						builder.or( 
								builder.like(expFirst, sortText),
								builder.like(expLast, sortText)));
			}
		} else {
			if (clientId == -1 && mktStatus != -1) {
				criteria.where(builder.equal(root.get(MKTSTS), mktStatus));
			} 
			else if (mktStatus == -1 && clientId != -1) {
				criteria.where(builder.equal(root.get(CLIENT), clientId));
			}
			else if (mktStatus != -1 && clientId != -1) {
				criteria.where(builder.and(
						builder.equal(root.get(MKTSTS), mktStatus),
						builder.equal(root.get(CLIENT), clientId)));
			}
		}
		
		if (startIdx==1) { startIdx = 0; }		
		List<TfAssociate> results = session.createQuery(criteria)
				.setCacheable(true)
				.setFirstResult(startIdx)
				.setMaxResults(numRes)
				.getResultList();
		
		session.close(); //close out the session
		if (results == null || results.isEmpty()) { 
			LogUtil.logger.error("Result List was empty.");
			return null; }
		LogUtil.logger.trace("Results: " + results);
		return results;
	}
	
	/** Gets a single associate with an id
	 * @param Integer associateId */
	@Override
	public TfAssociate getAssociate(Integer id) {
		LogUtil.logger.trace("Hibernate Call to get AssociateId: " + id);
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
	    session.createQuery("from TfAssociate a where a.id = :id", TfAssociate.class)
		.setParameter("id", id).setCacheable(true).getSingleResult());
	}

	/** Gets an associate by an associated user id
	 * @param int userId */
	@Override
	public TfAssociate getAssociateByUserId(int id) {
		LogUtil.logger.trace("Hibernate Call to get Associate via UserId: " + id);
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfAssociate where user.id = :id", TfAssociate.class)
				.setParameter("id", id).setCacheable(true).getSingleResult());
	}

	/** Gets all associates */
	@Override
	public List<TfAssociate> getAllAssociates() {
		LogUtil.logger.trace("Hibernate Call to get all Associates.");
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate", TfAssociate.class).setCacheable(true).getResultList());
	}
	
	@Override
	public List<TfAssociate> getNAssociates() {
		LogUtil.logger.trace("Hibernate Call to get first 60 Associates" );
		return HibernateUtil.runHibernate((Session session, Object ...args) -> session
				.createQuery("from TfAssociate", TfAssociate.class)
				.setMaxResults(60).setCacheable(true).getResultList());
	}

	/** 
	 * @author Art B.
	 * getAllStatusCounts returns a HashMap of Integer keys and values, representing the number of associates (value)
	 * for each status code id (key). Below is what each id represents:
	 * 1. Mapped: Training (name is null in the database)
	 * 2. Mapped: Reserved
	 * 3. Mapped: Selected
	 * 4. Mapped: Confirmed
	 * 5. Mapped: Deployed
	 * 6. Unmapped: Training
	 * 7. Unmapped: Open
	 * 8. Unmapped: Selected
	 * 9. Unmapped: Confirmed
	 * 10. Unmapped: Deployed
	 * 11. Directly Placed
	 * 12. Terminated
	 */

	private HashMap<Integer,Integer> getAllStatusCounts() {
		List<String> resultList = HibernateUtil.getSessionFactory().openSession().createQuery(
				"select " +
				"a.marketingStatus || ',' || " +
				"count( a.marketingStatus ) " +
				"from TfAssociate a " +
				"group by a.marketingStatus " +
				"order by a.marketingStatus", String.class).getResultList();
		HashMap<Integer,Integer> resultMap = new HashMap<>();
		for (String result : resultList) {
			String[] split = result.split(",");
			resultMap.put(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
		}
		if (resultMap.size() < 12) for (int i = 1; i < 13; i++) resultMap.putIfAbsent(i, 0);
		return resultMap;
	}
	
	/** 
	 * @author Art B.
	 * Sorts the values retrieved from getAllStatusCounts into the order that the AssociateResource uses them,
	 * and converts it to a HashMap with a String keys instead that represent the names.
	 * Removes 'Directly Placed' and 'Terminated' values, and adds "Undeployed Mapped" and "Undeployed Unmapped"
	 * aggregate values in the process.
	 * 
	 */
	
	public HashMap<String,Integer> getStatusCountsMap(){
		HashMap<Integer,Integer> rawMap = getAllStatusCounts();
		HashMap<String,Integer> stringMap = new HashMap<>();
		stringMap.put("Undeployed Mapped", rawMap.get(1)+rawMap.get(2)+rawMap.get(3)+rawMap.get(4));
		stringMap.put("Undeployed Unmapped", rawMap.get(6)+rawMap.get(7)+rawMap.get(8)+rawMap.get(9));
		stringMap.put("Deployed Mapped", rawMap.get(5));
		stringMap.put("Deployed Unmapped", rawMap.get(10));
		stringMap.put("Unmapped Training", rawMap.get(6));
		stringMap.put("Unmapped Open", rawMap.get(7));
		stringMap.put("Unmapped Selected", rawMap.get(8));
		stringMap.put("Unmapped Confirmed", rawMap.get(9));
		stringMap.put("Mapped Training", rawMap.get(1));
		stringMap.put("Mapped Reserved", rawMap.get(2));
		stringMap.put("Mapped Selected", rawMap.get(3));
		stringMap.put("Mapped Confirmed", rawMap.get(4));
		return stringMap;
	}

	@Override
	public boolean updateAssociatePartial(TfAssociate associate) {
		return HibernateUtil.runHibernateTransaction((Session session, Object ... args)-> {
			TfAssociate temp = session.get(TfAssociate.class, associate.getId());
			temp.setFirstName(associate.getFirstName());
			temp.setLastName(associate.getLastName());
			session.update(temp);
			return true;
		});
	}

	/** approves given associate
	 * @param int associateId */
	@Override
	public boolean approveAssociate(int associateId) {
		LogUtil.logger.trace("Hibernate Call to Approve AssociateId: " + associateId);
		return HibernateUtil.runHibernateTransaction(approveAssociate, associateId);
	}

	/** approves many given associates
	 * @param List<Integer> contains associate ids */
	@Override
	public boolean approveAssociates(List<Integer> associateIds) {
		LogUtil.logger.trace("Hibernate Call to Approve Multiple AssociateId: " + associateIds);
		return HibernateUtil.multiTransaction(approveAssociate, associateIds);
	}

	/** Creates new associate with a given associate object.
	 * @param TfAssociate the new associate you wish to persist */
	@Override
	public boolean createAssociate(TfAssociate newassociate) {
		LogUtil.logger.trace("Hibernate Call to Create Associate: " + newassociate.getId());
		return HibernateUtil.saveToDB(newassociate);
	}

	/** Gets Mapped Associates to Clients. */
	@Override
	public List<GraphedCriteriaResult> getMapped(int id) {
		LogUtil.logger.trace("List Mapping for Associates mapped to ClientId: " + id);
		return HibernateUtil.runHibernate((Session session, Object... args) -> {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);

			Root<TfAssociate> root = query.from(TfAssociate.class);

			Join<TfAssociate, TfClient> clientJoin = root.join(CLIENT);
			Join<TfAssociate, TfMarketingStatus> msJoin = root.join(MKTSTS);

			Path<?> clientId = clientJoin.get("id");
			Path<?> clientName = clientJoin.get("name");

			query.where(cb.equal(msJoin.get("id"), args[0]));
			query.groupBy(clientId, clientName);
			query.multiselect(cb.count(root), clientId, clientName);
			return session.createQuery(query).setCacheable(true).getResultList();
		}, id);
	}
	
	/** Gets undeployed (mapped or unmapped).
	 * Optimized to remove redundancy @author Paul C.-1807*/
	@Override
	public List<GraphedCriteriaResult> getUndeployed(String which) {
		LogUtil.logger.trace("List Mapping for Associates Undeployed currently. Mapping Type: " + which);
		if(which.equals("mapped") || which.equals("unmapped")) {
			return HibernateUtil.runHibernate((Session session, Object... args) -> {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);
				Root<TfAssociate> root = query.from(TfAssociate.class);
				
				if (which.equals("mapped")) {
					Join<TfAssociate, TfClient> clientJoin = root.join(CLIENT);
					Join<TfAssociate, TfMarketingStatus> msJoin = root.join(MKTSTS);
		
					Path<?> clientId = clientJoin.get("id");
					Path<?> clientName = clientJoin.get("name");
		
					query.where(cb.lessThanOrEqualTo(msJoin.get("id"), 4));
					query.where(cb.greaterThanOrEqualTo(msJoin.get("id"), 1));
		
					query.groupBy(clientId, clientName);
					query.multiselect(cb.count(root), clientId, clientName);
				}
				else if (which.equals("unmapped")) {
					Join<TfAssociate, TfBatch> batchJoin = root.join("batch");
					Join<TfBatch, TfCurriculum> curriculumJoin = batchJoin.join("curriculumName");
					Join<TfAssociate, TfMarketingStatus> msJoin = root.join(MKTSTS);
		
					Path<?> curriculumid = curriculumJoin.get("id");
					Path<?> curriculumName = curriculumJoin.get("name");
		
					query.where(cb.lessThanOrEqualTo(msJoin.get("id"), 9));
					query.where(cb.greaterThanOrEqualTo(msJoin.get("id"), 6));
					query.groupBy(curriculumid, curriculumName);
					query.multiselect(cb.count(root), curriculumid, curriculumName);
				}
				return session.createQuery(query).setCacheable(true).getResultList();
			});
		}
		throw new InvalidArgumentException("getUndeployed(): NOT MAPPED OR UNMAPPED");
	}

	@Override
	public boolean updateAssociate(TfAssociate associate) {
		LogUtil.logger.trace("Hibernate Transaction to update AssociateId: " + associate.getId());
		return runHibernateTransaction((Session session, Object... args) -> {
			TfAssociate temp = session.get(TfAssociate.class, associate.getId());
		//	if (associate.getFirstName() != null || !associate.getFirstName().equals("") || !temp.getFirstName().equals(associate.getFirstName())) {
				temp.setFirstName(associate.getFirstName());
				System.out.println(temp.getFirstName());
			//}
		//	if (associate.getLastName() != null || !associate.getLastName().equals("") || !temp.getLastName().equals(associate.getLastName())) {
				temp.setLastName(associate.getLastName());
				System.out.println(temp.getLastName());
			//}
			//if (associate.getStagingFeedback() != null || !associate.getStagingFeedback().equals("") 
			//		|| !temp.getStagingFeedback().equals(associate.getStagingFeedback())) {
				temp.setStagingFeedback(associate.getStagingFeedback());
				System.out.println(temp.getStagingFeedback());
			//}
			//v1811 - Temp code for updating client, marketing status, and isApproved via both update associate and List<Associates>
			//if (associate.getClient() != null || !temp.getClient().equals(associate.getClient())) {
				temp.setClient(associate.getClient());
				System.out.println(temp.getClient());
			//}
			//if (associate.getMarketingStatus() != null|| !temp.getMarketingStatus().equals(associate.getMarketingStatus())) {
				temp.setMarketingStatus(associate.getMarketingStatus());
				System.out.println(temp.getMarketingStatus());
			//}
			
			temp.getUser().setIsApproved(associate.getUser().getIsApproved());
			session.update(temp);
			return true;
		});
	}

	@Override
	public boolean updateAssociates(List<TfAssociate> associates) {
		LogUtil.logger.trace("Calls updateAssociate for List of <Associates>: " + associates);
		associates.forEach(this::updateAssociate);
		return true;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public <T> T countMappedAssociatesByValue(String column, T value, Integer mappedStatus) {
		Sessional<T> ss = (Session session, Object... args) -> {
			String condition = null;

			if (Integer.valueOf(value.toString()) != -1) {
				
				condition = column + " = " + args[0] + " AND ";
			} else {
				condition = "";
			}
			String hql = "SELECT COUNT(TF_ASSOCIATE_ID) FROM TfAssociate WHERE "
					+ condition + "TF_MARKETING_STATUS_ID = :status";
			Query query = session.createQuery(hql).setCacheable(true);
			return (T) query.setParameter("status", args[1]).getSingleResult();
		};
		return HibernateUtil.runHibernate(ss, value, mappedStatus);
	}*/
	
	@Override
	/*
	 * Sends back number of Associates compared against Column name. [Passed as a string-variable].
	 * Value - Conditional Clause against the Column name. TF_Client_Id - "some id variable"
	 * mappedStatus - the marketing status of mapped Associates.
	 */
	public long countMappedAssociatesByValue(String column, String value, int mappedStatus) {
		LogUtil.logger.trace("Number of Mapped Associates based of what ColumnName: " + column + " and the value: " + value);
		Sessional<Long> ss = (Session session, Object... args) -> {
			Long numValue;
			try {
				numValue = Long.valueOf((String) args[0]);
			}
			catch (NumberFormatException e) {
				numValue = null;
			}
			String hql = "SELECT COUNT(TF_ASSOCIATE_ID) FROM TfAssociate WHERE "
					+ column + " = :value AND TF_MARKETING_STATUS_ID = :status";
			Query query = session.createQuery(hql).setCacheable(true);
			query.setParameter("status", args[1]);
			if (numValue == null) {
				query.setParameter("value", args[0]);
			}
			else {
				query.setParameter("value", numValue);
			}
			return (Long) query.getSingleResult();
		};
		return HibernateUtil.runHibernate(ss, value, mappedStatus);
		
	}

	//Should probably only be used for testing. Deleting records for production is a bad idea.
	@Override
	public void deleteAssociate(TfAssociate associate) {
		LogUtil.logger.trace("Hibernate Call to delete an Associate.");
		runHibernateTransaction((Session session, Object... args) -> {
			session.delete(associate);
			return true;
		});
		
	}

	/*
	 *The method deleteOldAssociateProcedure() will call a stored procedure on the database side.
	 *the procedure is meant to delete associates who are pending approval 
	 *for over 30 days. Only deletes associates, not trainers, etc. 
	 */
	@Override
	public void deleteOldAssociateProcedure() {
		LogUtil.logger.trace("Automatic Deleting Unapproved Old Associates.");
		runHibernateTransaction((Session session, Object... args) -> {
			StoredProcedureQuery query = session.createStoredProcedureCall("delete_old_associates").registerStoredProcedureParameter(1, Integer.class, ParameterMode.OUT);
			query.execute();
			return ((Integer) query.getOutputParameterValue(1)) == 1;
		});	
	}
}