package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Need entity
 * 
 * @author Team Trees
 */
public class Need {

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private int cost;
    @JsonProperty("age") private int age;
    @JsonProperty("description") private String description;

    static final String STRING_FORMAT = "Need [id=%d, name=%s, cost=%d, description=%s]";
    /* 
     * Create a need with a given name, cost, description
     * @param id ID of need
     * @param name name of need
     * @param cost cost of need
     * @param description description of need

    */
    public Need(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("cost") int cost, @JsonProperty("age") int age, @JsonProperty("description") String description)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.age = age;
        this.description = description;
    }

    /**
     * Retrieves the id of the need
     * @return The id of the need
     */
    public int getId() {return id;}

    /**
     * Sets the name of the need - necessary for JSON object to Java object deserialization
     * @param name The name of the need
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the need
     * @return The name of the need
     */
    public String getName() {return name;}

    /**
     * Retrieves the cost of the need
     * @return The cost of the need
     */
    public int getCost() {return cost;}

    /**
     * Retrieves the description of the need
     * @return The description of the need
     */
    public String getDescription() {return description; }

    public int getAge() {return age;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,cost,description);
    }
}
