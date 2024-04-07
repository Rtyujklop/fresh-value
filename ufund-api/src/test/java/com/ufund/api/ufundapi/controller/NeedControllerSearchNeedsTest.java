package com.ufund.api.ufundapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Hero Controller searchNeeds method
 * 
 * @author Zach Herring
 */

@Tag("Controller-tier")
public class NeedControllerSearchNeedsTest {
    private NeedController needController;
    private NeedDAO mockNeedDAO;

    /**
     * Before each test, create a new NeedController object and inject
     * a mock Need DAO
     * 
     */

    @BeforeEach
    public void setupNeedController() {
        mockNeedDAO = mock(NeedDAO.class);
        needController = new NeedController(mockNeedDAO);
    }

    @Test
    public void testSearchNeeds() throws IOException { // findNeeds may throw IOException
        // Setup
        String searchString = "pr";
        Need[] needs = new Need[2];
        needs[0] = new Need(99, 
                            "White Spruce", 
                            25, 
                            100,
                            "straight trunk; long, stout branches form broad conical head.");
        needs[1] = new Need(100,
                            "Norway Spruce",
                            26,
                            100,
                            "The bark of a young tree is thin and thickens into gray-brown" +
                            "flaky scales with maturity. It has four-sided needles that are about 1 inch long.");
        // When findNeeds is called with the search string, return the two
        // needs above
        when(mockNeedDAO.findNeeds(searchString)).thenReturn(needs);

        // Invoke
        ResponseEntity<Need[]> response = needController.searchNeeds(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(needs,response.getBody());
    }

    @Test
    public void testSearchNeedsHandleException() throws IOException { // findNeeds may throw IOException
    // Setup
    String searchString = "an";
    // When createNeed is called on the Mock Need DAO, throw an IOException
    doThrow(new IOException()).when(mockNeedDAO).findNeeds(searchString);

    // Invoke
    ResponseEntity<Need[]> response = needController.searchNeeds(searchString);

    // Analyze
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
