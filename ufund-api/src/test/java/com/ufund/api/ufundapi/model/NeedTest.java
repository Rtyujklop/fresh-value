package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("Model-tier")
public class NeedTest {

    @Test
    public void testCtor() {
        // Setup
        int id = 99;
        String name = "White Spruce";
        int cost = 25;
        int age = 10;
        String description = "An extremely hardy evergreen conifer, the White Spruce Tree adds\n" +
                             "gorgeous year-round blue-green color to the landscape. Native to the\n" +
                             "northern United States and Canada, it grows 40-60 ft. tall with a 10-20 ft.\n" +
                             "spread with a uniform, pyramidal habit. Its short, sharp needles are\n" +
                             "aromatic when crushed.";
        
        // Invoke
        Need need = new Need(id, name, cost, age, description);
        
        // Analyze
        assertEquals(id, need.getId());
        assertEquals(name, need.getName());
        assertEquals(cost, need.getCost());
        assertEquals(age, need.getAge());
        assertEquals(description, need.getDescription());

    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "White Spruce";
        int cost = 25;
        int age = 10;
        String description = "An extremely hardy evergreen conifer, the White Spruce Tree adds\n" +
                             "gorgeous year-round blue-green color to the landscape. Native to the\n" +
                             "northern United States and Canada, it grows 40-60 ft. tall with a 10-20 ft.\n" +
                             "spread with a uniform, pyramidal habit. Its short, sharp needles are\n" +
                             "aromatic when crushed.";
        Need need = new Need(id, name, cost, age, description);
        String expected_name = "Norway Spruce";

        // Invoke
        need.setName(expected_name);

        // Analyze
        assertEquals(expected_name, need.getName());
        
    }

    @Test 
    public void testToString() {
        // Setup
        int id = 99;
        String name = "White Spruce";
        int cost = 25;
        int age = 10;
        String description = "An extremely hardy evergreen conifer, the White Spruce Tree adds\n" +
                             "gorgeous year-round blue-green color to the landscape. Native to the\n" +
                             "northern United States and Canada, it grows 40-60 ft. tall with a 10-20 ft.\n" +
                             "spread with a uniform, pyramidal habit. Its short, sharp needles are\n" +
                             "aromatic when crushed.";
        String expected_string = String.format(Need.STRING_FORMAT,id,name,cost,age,description);
        Need need = new Need(id,name,cost,age,description);

        // Invoke
        String actual_string = need.toString();

        // Analyze
        assertEquals(expected_string, actual_string);
    }
}
