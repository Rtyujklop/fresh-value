package com.ufund.api.ufundapi.Controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufund.api.ufundapi.controller.NeedController;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedDAO;

@Tag("Controller-tier")
public class createNeedTests {
    private NeedController needController;
    private NeedDAO mockNeedDAO;
        
    @BeforeEach
    public void setupNeedController() {
        mockNeedDAO = mock(NeedDAO.class);
        needController = new NeedController(mockNeedDAO);
    }

    @Test
    public void testCreateNeed() throws IOException {
        // Setup
        Need testedNeed = new Need(1,"Pine Tree", 100, "A pine tree");
        when(mockNeedDAO.createNeed(testedNeed)).thenReturn(testedNeed);

        // Invoke
        ResponseEntity<Need> response = needController.createNeed(testedNeed);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(testedNeed,response.getBody());
    }

    @Test
    public void testCreateNeedFailed() throws IOException { 
        // Setup
        Need testedNeed = new Need(1,"Pine Tree", 100, "A pine tree");
        // when createNeed is called, return false simulating failed
        when(mockNeedDAO.createNeed(testedNeed)).thenReturn(null);

        // Invoke
        ResponseEntity<Need> response = needController.createNeed(testedNeed);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateNeedHandleException() throws IOException {   
        // Setup
        Need testedNeed = new Need(1,"Pine Tree", 100, "A pine tree");

        // When createNeed is called on the Mock Need DAO, throw an IOException
        doThrow(new IOException()).when(mockNeedDAO).createNeed(testedNeed);

        // Invoke
        ResponseEntity<Need> response = needController.createNeed(testedNeed);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}