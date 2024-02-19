package com.ufund.api.ufundapi.model

import java.util.logging.Logger;


public class Need {
    private static final Logger LOG = Logger.getLogger(Need.class.getName());

    @JsonProperty("id") private int id;
    @JsonProperty("name") private string name;
    @JsonProperty("cost") private int cost;
    @JsonProperty("description") private string description;

    /* 
     * Create a need with a given name, cost, description
     * @param id ID of need
     * @param name name of need
     * @param cost cost of need
     * @param description description of need

    */
    public Need(@JsonProperty("id") private int id, @JsonProperty("name") private String name, @JsonProperty("cost") private int cost, @JsonProperty("description") private String description)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }
}
