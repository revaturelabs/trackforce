package com.revature.test.services;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.TechDao;
import com.revature.model.TechInfo;
import com.revature.services.TechService;

public class TechServiceTest{

    @Mock
    private TechDao techDaoMock;

    private TechService techService;

    private Map<Integer,TechInfo> mockTechs;

    private void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);
        
        // mock dao used by tech resource to return these techs
        TechInfo ti1 = new TechInfo();
        ti1.setId(1);
        ti1.setName("technology1");
        
        TechInfo ti2 = new TechInfo();
        ti1.setId(2);
        ti1.setName("technology2");

        mockTechs = new HashMap<>();
        mockTechs.put(1, ti1);
        mockTechs.put(2, ti2);

        Mockito.when(techDaoMock.getAllTechs()).thenReturn(mockTechs);
        
        //Mockito.when(techDaoMock.getTechById(Matchers.anyInt())).thenReturn(mockTech);

        techService = new TechService(techDaoMock);
    }

    @BeforeTest
    public void beforeAll() throws IOException {
        setupMocks();
    }

    @Test(enabled = true)
    public void testGetTechs() throws Exception {
    	List<TechInfo> actualList = techService.getTechs();
    	List<TechInfo> expectedList = new ArrayList<>(mockTechs.values());
    	assertEquals(expectedList, actualList);
    }
}
