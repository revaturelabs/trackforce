package com.revature.daoimpl;
import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import static com.revature.utils.HibernateUtil.saveToDB;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
import com.revature.utils.Sessional;

/**
 * Data Access Object implementation to access the associate entity from the Database
 */
public class AssociateDaoImpl implements AssociateDao {
	
	/** Gets list of associates matching criteria. Used by updated angular front end to perform 
	 * Pagination of results and improve performance.
	 * @author Joshua-Pressley-1807
	 * @param startIdx starting index
	 * @param numRes the number of resuts to return
	 * @param mktStatus the marketing ID
	 * @param clientId the client ID
	 * @return list of associates matching criteria */
	public List<TfAssociate> getNAssociateMatchingCriteria(int startIdx, int numRes, int mktStatus, int clientId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteria = builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root = criteria.from(TfAssociate.class);
		List<TfAssociate> results;
		ArrayList<TfAssociate> resultList;
		
		if (clientId == -1 && mktStatus != -1) {
			criteria.where(builder.equal(root.get("marketingStatus"), mktStatus));
			results = session.createQuery(criteria).getResultList();
		} 
		else if (mktStatus == -1 && clientId != -1) {	
			criteria.where(builder.equal(root.get("client"), clientId));
			results = session.createQuery(criteria).getResultList();
		} 
		else if (mktStatus != -1 && clientId != -1) {
			criteria.where(builder.equal(root.get("marketingStatus"), mktStatus));
			results = session.createQuery(criteria).getResultList();
			System.out.println("Starting to remove lines. Initial size: " + results.size());
		    for(Iterator<TfAssociate> iterator=results.iterator(); iterator.hasNext(); ) {
		          TfAssociate rfa = iterator.next();
		          if (rfa.getClient() != null) {
		        	  int clID = rfa.getClient().getId();
			          if(clID != clientId) { iterator.remove(); }
		          } else { iterator.remove(); }
		    }//end for
		} else { results = session.createQuery(criteria).getResultList(); }
		
		if (results == null || results.size() == 0) { return null; }
		if (startIdx==1) { startIdx = 0; }
		int endPoint = startIdx + numRes;
		resultList = new ArrayList<>(results);
		if (endPoint >= results.size()) { endPoint = resultList.size(); }
		session.close(); //close out the session
		return resultList.subList(startIdx, endPoint);
	}//end getNAssociateMatchingCriteria()
	
	/**
	 * Gets a single associate with an id
	 * @param Integer associateId
	 */
	@Override
	public TfAssociate getAssociate(Integer id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfAssociate a where a.id = :id", TfAssociate.class).setParameter("id", id).getSingleResult());
	}

	/**
	 * Gets an associate by an associated user id
	 * @param int userId
	 */
	@Override
	public TfAssociate getAssociateByUserId(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfAssociate where user.id = :id", TfAssociate.class).setParameter("id", id).getSingleResult());

	}

	/**
	 * Gets all associates
	 */
	@Override
	public List<TfAssociate> getAllAssociates() {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfAssociate", TfAssociate.class).getResultList());
	}
	
	@Override
	public List<TfAssociate> getNAssociates() {
		return HibernateUtil.runHibernate((Session session, Object ...args) -> session
				.createQuery("from TfAssociate", TfAssociate.class)
				.setMaxResults(60)
				.getResultList());
	}
	
	/** getCount uses CriteriaQuery and cascading switch statement to return count which 
	 *  matche the marketingStatus. Special query for cases 11 and 12
	 *  @author Paul Capellan - 1807
	 *  @param tfmsid is marketing status to be compared
	 *  @return returns count based on marketingStatus equivalent to tfmsid for case 1 to 10,
	 *  or statement used for case 11 and 12
	 */
	//Called by all getCount methods
	public Object getCount(int tfmsid) {
		Session session = null;
		Object count = null;
		 
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> qr = builder.createQuery(Long.class);
			Root<TfAssociate> root = qr.from(TfAssociate.class);
			List<Long> results;
			qr.select(builder.count(root));
			
			switch(tfmsid) {	
				case 11: 
						qr.where(builder.or(builder.or(builder.equal(root.get("marketingStatus"), 1), 
								builder.equal(root.get("marketingStatus"), 2)), builder.or(builder.equal(
								root.get("marketingStatus"), 3), builder.equal(root.get("marketingStatus"), 4))));
						break;
				case 12:  
						qr.where(builder.or(builder.or(builder.equal(root.get("marketingStatus"), 6), 
								builder.equal(root.get("marketingStatus"), 7)), builder.or(builder.equal(
								root.get("marketingStatus"), 8), builder.equal(root.get("marketingStatus"), 9))));
						break;
				default:
						qr.where(builder.equal(root.get("marketingStatus"), tfmsid));
						break;
			}//end switch
			
			results = session.createQuery(qr).getResultList();
			count = results.get(0);
			//System.out.println(count);
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			if ( session != null )
				session.close();
		}
		return count;
	}
	
	/** Calls the getCount method above and passes respective parameter
	 *  First 2 methods preserve the same query of tf_marketing_status_id, 11 or 12 not valid marketingStatus 
	 *  @author Paul Capellan - 1807
	 *  @return returns count from getCount() method based on marketingStatus
	 */
	//Call by getCount(11), but preserve the same tf_marketing_status_id!
	@Override
	public Object getCountUndeployedMapped() {
		return getCount(11);
	}
	
	//Call by getCount(12), but preserve the same tf_marketing_status_id!
	@Override
	public Object getCountUndeployedUnmapped() {
		return getCount(12);
	}
	
	@Override
	public Object getCountDeployedMapped() {
		return getCount(5);
	}
	
	@Override
	public Object getCountDeployedUnmapped() {
		return getCount(10);
	}

	@Override
	public Object getCountUnmappedTraining() {
		return getCount(6);
	}
	
	@Override
	public Object getCountUnmappedOpen() {
		return getCount(7);
	}
	
	@Override
	public Object getCountUnmappedSelected() {
		return getCount(8);
	}
	
	@Override
	public Object getCountUnmappedConfirmed() {
		return getCount(9);
	}
	
	@Override
	public Object getCountMappedTraining() {
		return getCount(1);
	}
	
	@Override
	public Object getCountMappedReserved() {
		return getCount(2);
	}
	
	@Override
	public Object getCountMappedSelected() {
		return getCount(3);
	}
	
	@Override
	public Object getCountMappedConfirmed() {
		return getCount(4);
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

	/**
	 * Sessional with instructions on how to approve an associate
	 */
	private Sessional<Boolean> approveAssociate = (Session session, Object... args) -> {
		TfAssociate temp = session.get(TfAssociate.class, (Integer) args[0]);

		temp.getUser().setIsApproved(TfUser.APPROVED);

		session.update(temp);
		return true;
	};

	/**
	 * approves given associate
	 * 
	 * @param int associateId
	 */
	@Override
	public boolean approveAssociate(int associateId) {
		return HibernateUtil.runHibernateTransaction(approveAssociate, associateId);
	}

	/**
	 * approves many given associates
	 * 
	 * @param List<Integer> contains associate ids
	 */
	@Override
	public boolean approveAssociates(List<Integer> associateIds) {
		return HibernateUtil.multiTransaction(approveAssociate, associateIds);
	}

	/**
	 * Creates new associate with a given associate object.
	 * 
	 * @param TfAssociate the new associate you wish to persist
	 */
	@Override
	public boolean createAssociate(TfAssociate newassociate) {
		return saveToDB(newassociate);
	}

	/**
	 * Does something
	 */
	@Override
	public List<GraphedCriteriaResult> getMapped(int id) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);

			Root<TfAssociate> root = query.from(TfAssociate.class);

			Join<TfAssociate, TfClient> clientJoin = root.join("client");
			Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");

			Path<?> clientId = clientJoin.get("id");
			Path<?> clientName = clientJoin.get("name");

			query.where(cb.equal(msJoin.get("id"), args[0]));
			query.groupBy(clientId, clientName);
			query.multiselect(cb.count(root), clientId, clientName);
			return session.createQuery(query).getResultList();
		}, id);
	}
	
	/** Optimized getUndeployed to remove redundancy
	 * @author Paul C.-1807*/
	@Override
	public List<GraphedCriteriaResult> getUndeployed(String which) {
		if(which.equals("mapped") || which.equals("unmapped")) {
			return HibernateUtil.runHibernate((Session session, Object... args) -> {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<GraphedCriteriaResult> query = cb.createQuery(GraphedCriteriaResult.class);
				Root<TfAssociate> root = query.from(TfAssociate.class);
				
				if (which.equals("mapped")) {
					Join<TfAssociate, TfClient> clientJoin = root.join("client");
					Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");
		
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
					Join<TfAssociate, TfMarketingStatus> msJoin = root.join("marketingStatus");
		
					Path<?> curriculumid = curriculumJoin.get("id");
					Path<?> curriculumName = curriculumJoin.get("name");
		
					query.where(cb.lessThanOrEqualTo(msJoin.get("id"), 9));
					query.where(cb.greaterThanOrEqualTo(msJoin.get("id"), 6));
					query.groupBy(curriculumid, curriculumName);
					query.multiselect(cb.count(root), curriculumid, curriculumName);
				}
				return session.createQuery(query).getResultList();
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
			Query query = session.createQuery(hql);
			
			return (T) query
					.setParameter("status", args[1])
					.getSingleResult();
		};

		return HibernateUtil.runHibernate(ss, value, mappedStatus);
	}

}
