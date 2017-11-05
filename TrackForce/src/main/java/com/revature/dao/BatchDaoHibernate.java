package com.revature.dao;

import java.util.List;


import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

public class BatchDaoHibernate implements BatchDao {

    /**
     * Get a batch from the database given its name.
     * @param batchName - The name of the batch to get information about
     */
    @Override
    public TfBatch getBatch(String batchName) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();

        // CriteriaBuilder builder = session.getCriteriaBuilder();
        // CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
        // Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
        // criteriaQuery.select(root).where(builder.equal(root.get("tfBatchName"),
        // batchName));
        // Query<TfBatch> query = session.createQuery(criteriaQuery);
        // TfBatch batch = query.getSingleResult();

        ProcedureCall call = session.createStoredProcedureCall("ADMIN.rows_by_batchname");
        call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(1);
        call.registerParameter(2, Class.class, ParameterMode.REF_CURSOR);

        Output output = call.getOutputs().getCurrent();
        TfBatch batch = (TfBatch) ((ResultSetOutput) output).getSingleResult();

        session.close();

        return batch;
    }


	

	
	public List<TfBatch> getBatchDetails(String fromdate,String todate){
		SessionFactory sessionFactory = HibernateUtil.getSession();
		Session session = sessionFactory.unwrap( Session.class );
		ProcedureCall sp=session.createStoredProcedureCall("ADMIN.batch_by_date_range_PROC");
		sp.registerParameter(1,String.class,ParameterMode.IN).bindValue(fromdate);
		sp.registerParameter(2, String.class, ParameterMode.IN).bindValue(todate);
		sp.registerParameter(3,Class.class,ParameterMode.REF_CURSOR);
		Output output=sp.getOutputs().getCurrent();
		List<TfBatch> postComments=((ResultSetOutput) output).getResultList();
		return postComments;	
	}
	
}

