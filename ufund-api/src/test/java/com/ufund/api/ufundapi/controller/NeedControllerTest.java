package com.ufund.api.ufundapi.controller;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NeedControllerTest {

    @Mock
    private NeedDAO needDAO;

    @InjectMocks
    private NeedController needController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetNeed() throws IOException {
        // Arrange
        int id = 1;
        Need expectedNeed = new Need(id, "Test Need", 100, 100, "Test description");
        when(needDAO.getNeed(id)).thenReturn(expectedNeed);

        // Act
        ResponseEntity<Need> response = needController.getNeed(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedNeed, response.getBody());
        verify(needDAO, times(1)).getNeed(id);
    }

    @Test
    void testGetNeedNotFound() throws IOException 
    {
        int id = 5;
        when (needDAO.getNeed(id)).thenReturn(null);

        ResponseEntity<Need> response = needController.getNeed(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetNeedException() throws IOException
    {   
        int id = 1;
        when(needDAO.getNeed(id)).thenThrow(new IOException());

        ResponseEntity<Need> response = needController.getNeed(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetNeedsException() throws IOException
    {
        when(needDAO.getNeeds()).thenThrow(new IOException());

        ResponseEntity<Need[]> response = needController.getNeeds();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetNeeds() throws IOException {
        // Arrange
        Need[] expectedNeeds = {
                new Need(1, "Need 1", 100, 100,"Description 1"),
                new Need(2, "Need 2", 200, 100, "Description 2")
        };
        when(needDAO.getNeeds()).thenReturn(expectedNeeds);

        // Act
        ResponseEntity<Need[]> response = needController.getNeeds();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedNeeds.length, response.getBody().length);
        for (int i = 0; i < expectedNeeds.length; i++) {
            assertEquals(expectedNeeds[i], response.getBody()[i]);
        }
        verify(needDAO, times(1)).getNeeds();
    }

    
}


