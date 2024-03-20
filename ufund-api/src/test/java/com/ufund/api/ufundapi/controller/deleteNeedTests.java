package com.ufund.api.ufundapi.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedDAO;

@Tag("Controller-tier")
public class deleteNeedTests {
    private NeedController needController;
    private NeedDAO mockNeedDAO;
        
    @BeforeEach
    public void setupNeedController() {
        mockNeedDAO = mock(NeedDAO.class);
        needController = new NeedController(mockNeedDAO);
    }

    @Test
    public void testDeleteNeed() throws IOException {
        // Setup
        int NeedId = 1;
        Need need = new Need(NeedId, "Pine Tree", 100, "A pine tree");
        when(mockNeedDAO.getNeed(NeedId)).thenReturn(need); 
        when(mockNeedDAO.deleteNeed(NeedId)).thenReturn(true);

        // Invoke
        ResponseEntity<Need> response = needController.deleteNeed(NeedId);

        // Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteNeedNotFound() throws IOException { // 
        // Setup
        int needId = 1;
        // when deleteNeed is called return false, simulating failed deletion
        when(mockNeedDAO.deleteNeed(needId)).thenReturn(false);

        // Invoke
        ResponseEntity<Need> response = needController.deleteNeed(needId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteNeedHandleException() throws IOException {
        // Setup
        int needId = 1;
        Need need = new Need(needId, "Pine Tree", 100, "A pine tree");
        // When getNeed is called on the Mock Need DAO, return the Need object
        when(mockNeedDAO.getNeed(needId)).thenReturn(need);
        when(mockNeedDAO.deleteNeed(needId)).thenThrow(new IOException());

        // Invoke
        ResponseEntity<Need> response = needController.deleteNeed(needId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
