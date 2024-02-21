package com.ufund.api.ufundapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private int cost;
    @JsonProperty("description") private String description;

    static final String STRING_FORMAT = "Need [id=%d, name=%s, cost=%d, description=%s]";
    /* 
     * Create a need with a given name, cost, description
     * @param id ID of need
     * @param name name of need
     * @param cost cost of need
     * @param description description of need

    */
    public Need(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("cost") int cost, @JsonProperty("description") String description)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    /**
     * Retrieves the id of the hero
     * @return The id of the hero
     */
    public int getId() {return id;}

    /**
     * Sets the name of the hero - necessary for JSON object to Java object deserialization
     * @param name The name of the hero
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the hero
     * @return The name of the hero
     */
    public String getName() {return name;}

    public int getCost() {return cost;}

    public String getDesc() {return description; }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,cost,description);
    }
}
