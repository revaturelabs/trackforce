package com.revature.test.services;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.InterviewDao;
import com.revature.model.AssociateInfo;
import com.revature.model.InterviewInfo;
import com.revature.services.InterviewService;

public class InterviewServiceTest{

    @Mock
    private InterviewDao interviewDaoMock;

    private InterviewService interviewService;

    private void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);
        
        InterviewInfo ii = new InterviewInfo();
        ii.setId(1);
        ii.setTfAssociate(new AssociateInfo());
        ii.setTfClientName("some client");
        ii.setTfEndClientName("some end client");
        ii.setTfInterviewDate(new Timestamp(0));
        ii.setTfInterviewFeedback("feedback");
        ii.setTypeId(1);
        ii.setTypeName("Phone");
        
        Map<Integer, InterviewInfo> dummyInterviews = new HashMap<>();
        dummyInterviews.put(1, ii);
        
        Mockito.when(interviewDaoMock.getAllInterviews()).thenReturn(dummyInterviews);

        Mockito.when(interviewDaoMock.getInterviewsByAssociate(Matchers.anyInt())).thenReturn(dummyInterviews);

        interviewService = new InterviewService(interviewDaoMock);
    }

    @BeforeTest
    public void beforeAll() throws IOException {
        setupMocks();
    }

    @Test(enabled = true)
    public void testGetInterviews() throws Exception {
    	Set<InterviewInfo> expectedSet = interviewService.getAllInterviews();
    	assertNotNull(expectedSet);
    }
    
    @Test(enabled = true)
    public void testGetInterviewForAssociate() throws Exception {
    }
}