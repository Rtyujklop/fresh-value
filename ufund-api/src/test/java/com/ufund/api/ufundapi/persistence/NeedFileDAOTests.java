package com.ufund.api.ufundapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.persistence.NeedFileDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class NeedFileDAOTests
{
    NeedFileDAO needFileDAO;
    Need[]  fakeNeeds;
    ObjectMapper mockObjectMapper;


    @BeforeEach
    public void setupHeroFileDAO() throws IOException
    {
        mockObjectMapper = mock(ObjectMapper.class);
        
        fakeNeeds = new Need[5];
        fakeNeeds[0] = new Need(50, "Birch Tree", 100, "This is a birch tree");
        fakeNeeds[1] = new Need(51, "Oakley", 20, "This is not a tree");
        fakeNeeds[2] = new Need(52, "Oak Tree", 67, "This is an Oak tree");
        fakeNeeds[3] = new Need(53, "Sycamore Tree", 120, "This is a Sycamore tree");
        fakeNeeds[4] = new Need(54, "Willow Tree", 150, "This is a willow tree");

        when(mockObjectMapper
            .readValue(new File("example.txt"), Need[].class))
                .thenReturn(fakeNeeds);
        needFileDAO = new NeedFileDAO("example.txt", mockObjectMapper);
    }

    @Test
    public void testGetNeeds()
    {
        Need[] needs = needFileDAO.getNeeds();

        assertEquals(needs.length, fakeNeeds.length, "Wrong number of needs");
    }

    @Test
    public void testGetNeed()
    {
        Need need = needFileDAO.getNeed(51);

        assertEquals(need, fakeNeeds[1]);
    }
    
}
