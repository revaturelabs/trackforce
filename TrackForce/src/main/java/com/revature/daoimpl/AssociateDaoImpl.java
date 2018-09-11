package com.revature.daoimpl;
import static com.revature.utils.HibernateUtil.runHibernateTransaction;
import static com.revature.utils.HibernateUtil.saveToDB;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
	 * @return list of associates matching criteria */
	public List<TfAssociate> getNAssociateMatchingCriteria(int startIdx, int numRes, int mktStatus, int clientId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteria = builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root = criteria.from(TfAssociate.class);
		
		if (clientId == -1 && mktStatus != -1) {
			criteria.where(builder.equal(root.get(MKTSTS), mktStatus));
		} else if (mktStatus == -1 && clientId != -1) {
			criteria.where(builder.equal(root.get(CLIENT), clientId));
		} else if (mktStatus != -1 && clientId != -1) {
			criteria.where(builder.and(
					builder.equal(root.get(MKTSTS), mktStatus),
					builder.equal(root.get(CLIENT), clientId)));
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
	
	/** getCount uses CriteriaQuery and a switch statement to return count which 
	 *  matches the marketingStatus. Special query for cases 11 and 12.
	 *  Called by all getCount methods
	 *  @author Paul C. - 1807
	 *  @param tfmsid is marketing status to be compared
	 *  @return returns count based on marketingStatus equivalent to tfmsid for case 1 to 10,
	 *  or statement used for case 11 and 12 */
	private Object getCount(int tfmsid) {
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
						qr.where(builder.or(builder.or(builder.equal(root.get(MKTSTS), 1), 
								builder.equal(root.get(MKTSTS), 2)), builder.or(builder.equal(
								root.get(MKTSTS), 3), builder.equal(root.get(MKTSTS), 4))));
						break;
				case 12:  
						qr.where(builder.or(builder.or(builder.equal(root.get(MKTSTS), 6), 
								builder.equal(root.get(MKTSTS), 7)), builder.or(builder.equal(
								root.get(MKTSTS), 8), builder.equal(root.get(MKTSTS), 9))));
						break;
				default:
						qr.where(builder.equal(root.get(MKTSTS), tfmsid));
						break;
			}//end switch
			
			results = session.createQuery(qr).setCacheable(true).getResultList();
			count = results.get(0);
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			if ( session != null )
				session.close();
		}
		return count;
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountUndeployedMapped() {
		return getCount(11);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountUndeployedUnmapped() {
		return getCount(12);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountDeployedMapped() {
		return getCount(5);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountDeployedUnmapped() {
		return getCount(10);
	}

	/** Gets count matching this marketing status */
	@Override
	public Object getCountUnmappedTraining() {
		return getCount(6);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountUnmappedOpen() {
		return getCount(7);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountUnmappedSelected() {
		return getCount(8);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountUnmappedConfirmed() {
		return getCount(9);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountMappedTraining() {
		return getCount(1);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountMappedReserved() {
		return getCount(2);
	}
	
	/** Gets count matching this marketing status */
	@Override
	public Object getCountMappedSelected() {
		return getCount(3);
	}
	
	/** Gets count matching this marketing status */
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