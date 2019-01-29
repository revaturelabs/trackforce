package com.revature.daoimpl;
import java.util.List;
import com.revature.entity.TfEndClient;
import org.hibernate.Session;
import com.revature.dao.ClientDao;
import com.revature.entity.TfClient;
import com.revature.utils.HibernateUtil;
import com.revature.utils.ListOp;
import static com.revature.utils.LogUtil.logger;
public class ClientDaoImpl implements ClientDao {

	@Override
	public List<TfClient> getAllTfClients() {
		logger.trace("Hibernate call to get all TF Clients.");
		return HibernateUtil.runHibernate((Session session, Object ...args) -> session
				.createQuery("from TfClient order by tf_client_name ", TfClient.class)
				.setCacheable(true).getResultList());
	}
	
	public List<TfClient> getFirstFiftyTfClients() {
		logger.trace("Hibernate call to get First Fifty TF Clients.");
		return HibernateUtil.runHibernate((Session session, Object ...args) -> session
				.createQuery("from TfClient order by tf_client_name ", TfClient.class)
				.setMaxResults(50).setCacheable(true).getResultList());
	}

	@Override
	public List<TfClient> getAllClientsWithMappedAssociates(){
		logger.trace("Hibernate call to get all Clients that have Associates mapped to them but not deployed.");
		return HibernateUtil.runHibernate((Session session, Object ...args) -> 
			session.createQuery(
					  "FROM TfClient c WHERE "
					+ "(SELECT COUNT(tf_associate_id) FROM TfAssociate a "
					+ "WHERE a.client.id = c.id AND a.marketingStatus.id < 5) > 0", 
			TfClient.class).setCacheable(true).getResultList()
		);
	}
	
	//this function retrieves specific columns from the client table
	//pass arguments such as "c.id" to get list of all IDs or c.name to get list of all names
	//repaired 10/29/18 - BSS
	@Override
	public List getAllTfClients(String[] columns) {
		logger.trace("Hibernate Call to retrieve specific columns from the Client Table");
		return HibernateUtil.runHibernate( (Session session, Object ...args) -> session
		.createQuery("SELECT " + String.join(",", (String[]) args) + " FROM TfClient c")
		.setCacheable(true).getResultList(),(Object[])columns);
	}

	@Override
	public TfClient getClient(String name) {
		logger.trace("Hibernate Call to get Client: " + name);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfClient c where c.name like :name", TfClient.class)
				.setParameter("name", name).setCacheable(true).getSingleResult());
	}

	@Override
	public TfClient getClient(Integer id) {
		logger.trace("Hibernate Call to get ClientId: " + id);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfClient c where c.id like :id", TfClient.class)
				.setParameter("id", id).setCacheable(true).getSingleResult());
	}

	@Override
	public TfEndClient getEndClient(int id) {
		logger.trace("Hibernate Call to get EndClientId: " + id);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfEndClient c where c.id like :id", TfEndClient.class)
				.setParameter("id", id).setCacheable(true).getSingleResult());
	}
}