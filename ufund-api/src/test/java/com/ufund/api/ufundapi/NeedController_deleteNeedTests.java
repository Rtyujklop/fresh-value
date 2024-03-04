package com.ufund.api.ufundapi;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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
public class NeedController_deleteNeedTests {
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
}