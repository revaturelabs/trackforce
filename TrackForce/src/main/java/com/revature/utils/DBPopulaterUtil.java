package com.revature.utils;

import com.revature.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Object that facilitates in quick methods to populate the various tables
 */
public class DBPopulaterUtil {

    /**
     * clear database, LEAVING USERS AND ROLES ALONE; uses stored procedure
     *
     * @param session
     * @throws HibernateException
     */
    public void truncateDB(Session session) throws HibernateException {
        StoredProcedureQuery spq = session.createStoredProcedureCall("admin.truncateAllDevTeam");
        spq.execute();
    }

    /**
     * clear database; uses hibernate; does not clear user and role tables
     * permission issues may occur
     *
     * @param session
     * @throws HibernateException
     */
    public void clearDB(Session session) throws HibernateException {
        session.createQuery("DELETE FROM TfAssociate").executeUpdate();
        session.createQuery("DELETE FROM TfBatch").executeUpdate();
        session.createQuery("DELETE FROM TfBatchLocation").executeUpdate();
        session.createQuery("DELETE FROM TfClient").executeUpdate();
        session.createQuery("DELETE FROM TfClient").executeUpdate();
        session.createQuery("DELETE FROM TfEndClient").executeUpdate();
        session.createQuery("DELETE FROM TfCurriculum").executeUpdate();
        session.createQuery("DELETE FROM TfInterview").executeUpdate();
        session.createQuery("DELETE FROM TfInterviewType").executeUpdate();
        session.createQuery("DELETE FROM TfMarketingStatus").executeUpdate();
        session.createQuery("DELETE FROM TfPlacement").executeUpdate();
    }

    /**
     * add a user role
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateRole(Integer id, String name, Session session) {
        TfRole tfr = new TfRole();
        tfr.setTfRoleId(id == null ? null : new BigDecimal(id));
        tfr.setTfRoleName(name);

        session.saveOrUpdate(tfr);
    }

    /**
     * add a new user
     *
     * @param passwordHash
     * @param username
     * @param roleId
     * @param session
     */
    public void populateUser(String passwordHash, String username, Integer roleId, Session session) {
        TfUser tfu = new TfUser();
        tfu.setTfUserHashpassword(passwordHash);
        tfu.setTfUserUsername(username);
        tfu.setTfRole(roleId == null ? null : session.load(TfRole.class, new BigDecimal(roleId)));

        session.saveOrUpdate(tfu);
    }

    /**
     * add a new batch
     *
     * @param id
     * @param name
     * @param start
     * @param end
     * @param curriculumId
     * @param batchLocationId
     * @param session
     */
    public void populateBatch(Integer id, String name, LocalDate start, LocalDate end, Integer curriculumId,
                              Integer batchLocationId, Session session) {
        TfBatch batch = new TfBatch();
        TfCurriculum tfc = curriculumId == null ? null : session.load(TfCurriculum.class, new BigDecimal(curriculumId));
        TfBatchLocation tfbl = batchLocationId == null ? null : session.load(TfBatchLocation.class, new BigDecimal(batchLocationId));
        batch.setTfBatchId(id == null ? null : new BigDecimal(id));
        batch.setTfBatchName(name);
        batch.setTfBatchStartDate(start == null ? null : Timestamp.valueOf(start.atStartOfDay()));
        batch.setTfBatchEndDate(end == null ? null : Timestamp.valueOf(end.atStartOfDay()));
        batch.setTfCurriculum(tfc);
        batch.setTfBatchLocation(tfbl);

        session.saveOrUpdate(batch);
    }

    /**
     * add new batch location
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateBatchLocation(Integer id, String name, Session session) {
        TfBatchLocation tfbl = new TfBatchLocation();
        tfbl.setTfBatchLocationId(id == null ? null : new BigDecimal(id));
        tfbl.setTfBatchLocationName(name);

        session.saveOrUpdate(tfbl);
    }

    /**
     * add new placement
     *
     * @param id
     * @param start
     * @param end
     * @param clientId
     * @param endClientId
     * @param associateId
     * @param session
     */
    public void populatePlacement(Integer id, LocalDate start, LocalDate end, Integer clientId, Integer endClientId,
                                  Integer associateId, Session session) {
        TfPlacement placement = new TfPlacement();
        TfClient client = clientId == null ? null : session.load(TfClient.class, new BigDecimal(clientId));
        TfEndClient ec = endClientId == null ? null : session.load(TfEndClient.class, new BigDecimal(endClientId));
        TfAssociate assoc = associateId == null ? null : session.load(TfAssociate.class, new BigDecimal(associateId));
        placement.setTfPlacementId(id == null ? null : new BigDecimal(id));
        placement.setTfPlacementStartDate(start == null ? null : Timestamp.valueOf(start.atStartOfDay()));
        placement.setTfPlacementEndDate(end == null ? null : Timestamp.valueOf(end.atStartOfDay()));
        placement.setTfClient(client);
        placement.setTfEndClient(ec);
        placement.setTfAssociate(assoc);

        session.saveOrUpdate(placement);
    }

    /**
     * add new interview
     *
     * @param id
     * @param date
     * @param feedback
     * @param clientId
     * @param endClientId
     * @param typeId
     * @param associateId
     * @param session
     */
    public void populateInterview(Integer id, LocalDateTime date, String feedback, Integer clientId,
                                  Integer endClientId, Integer typeId, Integer associateId, Session session) {
        TfInterview tfi = new TfInterview();
        TfClient tfc = clientId == null ? null : session.load(TfClient.class, new BigDecimal(clientId));
        TfEndClient tfec = endClientId == null ? null : session.load(TfEndClient.class, new BigDecimal(endClientId));
        TfInterviewType tfit = typeId == null ? null : session.load(TfInterviewType.class, new BigDecimal(typeId));
        TfAssociate tfa = associateId == null ? null : session.load(TfAssociate.class, new BigDecimal(associateId));
        tfi.setTfInterviewId(id == null ? null : new BigDecimal(id));
        tfi.setTfInterviewDate(Timestamp.valueOf(date));
        tfi.setTfInterviewFeedback(feedback);
        tfi.setTfClient(tfc);
        tfi.setTfEndClient(tfec);
        tfi.setTfInterviewType(tfit);
        tfi.setTfAssociate(tfa);

        session.saveOrUpdate(tfi);
    }

    /**
     * add new interview type
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateInterviewType(Integer id, String name, Session session) {
        TfInterviewType tfit = new TfInterviewType();
        tfit.setTfInterviewTypeId(id == null ? null : new BigDecimal(id));
        tfit.setTfInterviewTypeName(name);

        session.saveOrUpdate(tfit);
    }

    /**
     * add new associate
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param marketStatusId
     * @param clientId
     * @param endClientId
     * @param batchId
     * @param session
     */
    public void populateAssociate(Integer id, String firstName, String lastName, Integer marketStatusId,
                                  Integer clientId, Integer endClientId, Integer batchId, Session session) {
        TfAssociate tfa = new TfAssociate();
        TfMarketingStatus tfms = marketStatusId == null ? null : session.load(TfMarketingStatus.class, new BigDecimal(marketStatusId));
        TfClient tfc = clientId == null ? null : session.load(TfClient.class, new BigDecimal(clientId));
        TfEndClient tfec = endClientId == null ? null : session.load(TfEndClient.class, new BigDecimal(endClientId));
        TfBatch tfb = batchId == null ? null : session.load(TfBatch.class, new BigDecimal(batchId));
        tfa.setTfAssociateId(id == null ? null : new BigDecimal(id));
        tfa.setTfAssociateFirstName(firstName);
        tfa.setTfAssociateLastName(lastName);
        tfa.setTfMarketingStatus(tfms);
        tfa.setTfClient(tfc);
        tfa.setTfEndClient(tfec);
        tfa.setTfBatch(tfb);

        session.saveOrUpdate(tfa);
    }

    /**
     * add new marketing status
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateMarketingStatus(Integer id, String name, Session session) {
        TfMarketingStatus tfms = new TfMarketingStatus();
        tfms.setTfMarketingStatusId(id == null ? null : new BigDecimal(id));
        tfms.setTfMarketingStatusName(name);

        session.saveOrUpdate(tfms);
    }

    /**
     * add new curriculum
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateCurriculum(Integer id, String name, Session session) {
        TfCurriculum tfc = new TfCurriculum();
        tfc.setTfCurriculumId(id == null ? null : new BigDecimal(id));
        tfc.setTfCurriculumName(name);

        session.saveOrUpdate(tfc);
    }

    /**
     * add new client
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateClient(Integer id, String name, Session session) {
        TfClient tfc = new TfClient();
        tfc.setTfClientId(id == null ? null : new BigDecimal(id));
        tfc.setTfClientName(name);

        session.saveOrUpdate(tfc);
    }

    /**
     * add new end client
     *
     * @param id
     * @param name
     * @param session
     */
    public void populateEndClient(Integer id, String name, Session session) {
        TfEndClient tfec = new TfEndClient();
        tfec.setTfEndClientId(id == null ? null : new BigDecimal(id));
        tfec.setTfEndClientName(name);

        session.saveOrUpdate(tfec);
    }
}
