package com.revature.daoimpl;
import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import static com.revature.utils.HibernateUtil.saveToDB;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.openqa.selenium.InvalidArgumentException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.Sessional;

/** Data Access Object implementation to access the associate entity from the Database 
 * @author Josh P. Chris S. Paul C. -1807 iteration */
public class AssociateDaoImpl implements AssociateDao {
	
	private static final String MKTSTS = "marketingStatus";
	private static final String CLIENT = "client";
	
	/** Gets list of associates matching criteria. Used by updated angular front end to perform 
	 * Pagination of results and improve performance.
	 * @author Joshua Pressley-1807
	 * @param startIdx starting index
	 * @param numRes the number of results to return
	 * @param mktStatus the marketing ID
	 * @param clientId the client ID
	 * @param sortText the text to sort by.
	 * @return list of associates matching criteria */
	public List<TfAssociate> getNAssociateMatchingCriteria(int startIdx, int numRes, int mktStatus, int clientId, String sortText)
	{		
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteria = builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root = criteria.from(TfAssociate.class);
		//Expression<String>  expName = root.get("batchName");
		Expression<String> expFirst = builder.lower(root.get("firstName"));
		Expression<String>  expLast = builder.lower(root.get("lastName"));
		sortText = "%" + sortText.toLowerCase() + "%";
		
		if (!sortText.isEmpty()) {
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
		if (results == null || results.isEmpty()) { return null; }
		return results;
	}//end getNAssociateMatchingCriteria()
	
	/** Gets a single associate with an id
	 * @param Integer associateId */
	@Override
	public TfAssociate getAssociate(Integer id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfAssociate a where a.id = :id", TfAssociate.class)
		.setParameter("id", id).setCacheable(true).getSingleResult());
	}

	/** Gets an associate by an associated user id
	 * @param int userId */
	@Override
	public TfAssociate getAssociateByUserId(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfAssociate where user.id = :id", TfAssociate.class)
				.setParameter("id", id).setCacheable(true).getSingleResult());
	}

	/** Gets all associates */
	@Override
	public List<TfAssociate> getAllAssociates() {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate", TfAssociate.class).setCacheable(true).getResultList());
	}
	
	@Override
	public List<TfAssociate> getNAssociates() {
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
		HashMap<Integer,Integer> resultMap = new HashMap<Integer,Integer>();
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
		HashMap<String,Integer> stringMap = new HashMap<String,Integer>();
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

	/** Sessional with instructions on how to approve an associate */
	private Sessional<Boolean> approveAssociate = (Session session, Object... args) -> {
		TfAssociate temp = session.get(TfAssociate.class, (Integer) args[0]);
		temp.getUser().setIsApproved(TfUser.APPROVED);
		session.update(temp);
		return true;
	};

	/** approves given associate
	 * @param int associateId */
	@Override
	public boolean approveAssociate(int associateId) {
		return HibernateUtil.runHibernateTransaction(approveAssociate, associateId);
	}

	/** approves many given associates
	 * @param List<Integer> contains associate ids */
	@Override
	public boolean approveAssociates(List<Integer> associateIds) {
		return HibernateUtil.multiTransaction(approveAssociate, associateIds);
	}

	/** Creates new associate with a given associate object.
	 * @param TfAssociate the new associate you wish to persist */
	@Override
	public boolean createAssociate(TfAssociate newassociate) {
		return saveToDB(newassociate);
	}

	/** Does something */
	@Override
	public List<GraphedCriteriaResult> getMapped(int id) {
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
	}//end getUndeployed()

	@Override
	public boolean updateAssociate(TfAssociate associate) {
		return runHibernateTransaction((Session session, Object... args) -> {
			session.update(associate);
			return true;
		});
	}

	@Override
	public boolean updateAssociates(List<TfAssociate> associates) {
		associates.forEach(this::updateAssociate);
		return true;
	}

	@SuppressWarnings("unchecked")
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
	}
}