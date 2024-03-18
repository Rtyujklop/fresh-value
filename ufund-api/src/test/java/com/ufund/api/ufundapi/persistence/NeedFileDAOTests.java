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

    @Test
    public void testFindNeeds()
    {   
        Need[] needs = needFileDAO.findNeeds("Oa");

        assertEquals(needs.length, 2, "Wrong amount of needs found");
        assertEquals(needs[0], fakeNeeds[1], "Wrong need found");
        assertEquals(needs[1], fakeNeeds[2], "Wrong need found");
    }

    @Test
    public void testCreateNeed()
    {
        Need need = new Need(55, "Olive Tree", 104, "This is an olive tree");

        Need result = assertDoesNotThrow(() -> needFileDAO.createNeed(need), "Creating need threw exception");
        assertNotNull(result);

        Need check = needFileDAO.getNeed(55);
        assertEquals(check.getId(), need.getId(), "Need does not match");
    }

    @Test
    public void testDeleteNeed()
    {
        boolean delete = assertDoesNotThrow(() -> needFileDAO.deleteNeed(50), "Deletion threw exception");
        assertEquals(delete, true, "Deletion failed");
        assertEquals(needFileDAO.Needs.size(), 4);
    }

    @Test
    public void testUpdateNeed()
    {
        Need need = new Need(52, "Olive Tree", 104, "This is an olive tree");

        Need update = assertDoesNotThrow(() -> needFileDAO.updateNeed(need), "Update threw exception");

        assertNotNull(update);
        Need check = needFileDAO.getNeed(52);
        assertEquals(check, need);

    }

    @Test
    public void testNeedNotFound()
    {
        Need need = needFileDAO.getNeed(12);

        assertEquals(need, null);
    }

    public void testUpdateNotFound()
    {
        Need need = new Need(12, "Olive Tree", 104, "This is an olive tree");

        Need update = assertDoesNotThrow(() -> needFileDAO.updateNeed(need), "Update threw exception");

        assertNull(update);

    }

    public void testDeleteeNotFound()
    {
        boolean delete = assertDoesNotThrow(() -> needFileDAO.deleteNeed(12), "Deletion threw exception");
        assertEquals(delete, false);
        assertEquals(needFileDAO.Needs.size(), fakeNeeds.length);
    }
    
}