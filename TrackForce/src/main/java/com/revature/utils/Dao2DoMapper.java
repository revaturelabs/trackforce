package com.revature.utils;

import java.sql.Timestamp;

import com.revature.entity.*;
import com.revature.model.*;

// maps dao objects to domain model objects
public class Dao2DoMapper {

    private static final String OTHER_VALUE = "Other";
    private static final String UNKNOWN_VALUE = "None";

    // This is the end mapper
    public static AssociateInfo map(TfAssociate tfa) {
        AssociateInfo ai = new AssociateInfo();
        ai.setId(tfa.getTfAssociateId());
        if (tfa.getTfClientStartDate() != null) {
        	ai.setClientStartDate(tfa.getTfClientStartDate().getTime());
        }
        if (tfa.getTfBatch() == null) {  //TF_BATCH_ID
            ai.setBatchId(new Integer(-1));
            ai.setBatchName(UNKNOWN_VALUE);
            ai.setCurriculumId(new Integer(-1));
            ai.setCurriculumName(UNKNOWN_VALUE);
        } else {
            ai.setBatchId(tfa.getTfBatch().getTfBatchId());
            ai.setBatchName(tfa.getTfBatch().getTfBatchName());
            if (tfa.getTfBatch().getTfCurriculum() == null) {
                ai.setCurriculumId(new Integer(-1));
                ai.setCurriculumName(UNKNOWN_VALUE);
            } else {
                ai.setCurriculumId(tfa.getTfBatch().getTfCurriculum().getTfCurriculumId());
                ai.setCurriculumName(tfa.getTfBatch().getTfCurriculum().getTfCurriculumName());
            }
        }
        if (tfa.getTfClient() == null) {	//TF_CLIENT_ID
            ai.setClientId(new Integer(-1));
            ai.setClient(UNKNOWN_VALUE);
        } else {
            ai.setClientId(tfa.getTfClient().getTfClientId());
            ai.setClient(tfa.getTfClient().getTfClientName());
        }
        if (tfa.getTfEndClient() == null) {		//TF_END_CLIENT_ID
            ai.setEndClientId(new Integer(-1));
            ai.setEndClient(UNKNOWN_VALUE);
        } else {
            ai.setEndClientId(tfa.getTfEndClient().getTfEndClientId());
            ai.setEndClient(tfa.getTfEndClient().getTfEndClientName());
        }
        if (tfa.getTfAssociateFirstName() == null)		//TF_ASSOCIATE_FIRST_NAME
            ai.setFirstName(UNKNOWN_VALUE);
        else
            ai.setFirstName(tfa.getTfAssociateFirstName());
        if (tfa.getTfAssociateLastName() == null)		//TF_ASSOCIATE_LAST_NAME
            ai.setLastName(UNKNOWN_VALUE);
        else
            ai.setLastName(tfa.getTfAssociateLastName());
        if (tfa.getTfMarketingStatus() == null) {		//TF_MARKETING_STATUS_ID
            ai.setMarketingStatusId(tfa.getTfMarketingStatus().getTfMarketingStatusId());
            ai.setMarketingStatus(OTHER_VALUE);
        } else {
            ai.setMarketingStatusId(tfa.getTfMarketingStatus().getTfMarketingStatusId());
            ai.setMarketingStatus(tfa.getTfMarketingStatus().getTfMarketingStatusName());
        }
        return ai;
    }


    /**
     * map TfBatch object to format consumed by front end, properly checking for
     * null values
     *
     * @param batch
     * @return
     */
    public static BatchInfo map(TfBatch batch) {
        String batchName = batch.getTfBatchName();
        Timestamp start = batch.getTfBatchStartDate();
        Timestamp end = batch.getTfBatchEndDate();
        TfCurriculum curriculum = batch.getTfCurriculum();
        TfBatchLocation location = batch.getTfBatchLocation();


        String startDate = (start != null) ? start.toString() : UNKNOWN_VALUE;
        String endDate = (end != null) ? end.toString() : UNKNOWN_VALUE;
        String curriculumName = (curriculum != null) ? curriculum.getTfCurriculumName() : OTHER_VALUE;
        String locationName = (location != null) ? location.getTfBatchLocationName() : OTHER_VALUE;

        BatchInfo bi = new BatchInfo(batch.getTfBatchId(), batchName, curriculumName, locationName, startDate, endDate);
        if (batch.getTfBatchStartDate() != null)
            bi.setStartLong(batch.getTfBatchStartDate().getTime());
        if (batch.getTfBatchEndDate() != null)
            bi.setEndLong(batch.getTfBatchEndDate().getTime());
        bi.setStartTs(batch.getTfBatchStartDate());
        if (batch.getTfAssociates() != null)
            for (TfAssociate tfa : batch.getTfAssociates()) {
                bi.getAssociates().add(map(tfa));
            }
        return bi;

    }

    public static CurriculumInfo map(TfCurriculum tfc) {
        CurriculumInfo ci = new CurriculumInfo();
        ci.setId(tfc.getTfCurriculumId());
        ci.setName(tfc.getTfCurriculumName());
        return ci;
    }

    public static TechInfo map(TfTech tft) {
    	TechInfo ti = new TechInfo();
        ti.setId(tft.getTfTechId());
        ti.setName(tft.getTechName());
        return ti;
    }

    public static MarketingStatusInfo map(TfMarketingStatus tfms) {
        MarketingStatusInfo msi = new MarketingStatusInfo();
        msi.setId(tfms.getTfMarketingStatusId());
        msi.setName(tfms.getTfMarketingStatusName());
        return msi;
    }

    //Faster Implementation
    public static ClientInfo map(TfClient client) {
        ClientInfo cli = new ClientInfo();
        cli.setTfClientId(client.getTfClientId());
        cli.setTfClientName(client.getTfClientName());

        if (client.getTfAssociates() != null)
            for (TfAssociate tfa : client.getTfAssociates()) {
                if (tfa.getTfMarketingStatus() != null) {
                    cli.appendToMap(tfa.getTfMarketingStatus());
                }
            }
        return cli;
    }

    public static PlacementInfo map(TfPlacement tfp) {
        PlacementInfo pi = new PlacementInfo();
        pi.setId(tfp.getTfPlacementId());
        if (tfp.getTfAssociate() != null)
            pi.setTfAssociate(map(tfp.getTfAssociate()));
        if (tfp.getTfPlacementStartDate() != null)
            pi.setTfPlacementStartDate(tfp.getTfPlacementStartDate());
        if (tfp.getTfPlacementEndDate() != null)
            pi.setTfPlacementEndDate(tfp.getTfPlacementEndDate());
        return pi;
    }

	public static InterviewInfo map(TfInterview tfi) {
        InterviewInfo ii = new InterviewInfo();
        ii.setId(tfi.getTfInterviewId());
        if (tfi.getTfAssociate() != null)
            ii.setTfAssociate(map(tfi.getTfAssociate()));
        if (tfi.getTfClient() != null)
        	ii.setTfClientName(tfi.getTfClient().getTfClientName());
        if (tfi.getTfInterviewDate() != null)
            ii.setTfInterviewDate(tfi.getTfInterviewDate());
        ii.setTfInterviewFeedback(tfi.getTfInterviewFeedback());
        if (tfi.getTfInterviewType() != null) {
            ii.setTypeId(tfi.getTfInterviewType().getTfInterviewTypeId());
            ii.setTypeName(tfi.getTfInterviewType().getTfInterviewTypeName());
        }
        return ii;
    }

    public static EndClientInfo map(TfEndClient tec) {
        EndClientInfo eci = new EndClientInfo();
        eci.setId(tec.getTfEndClientId());
        eci.setTfEndClientName(tec.getTfEndClientName());

        return eci;
    }
}
