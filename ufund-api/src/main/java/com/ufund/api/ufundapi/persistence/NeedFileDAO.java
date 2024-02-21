package com.ufund.api.ufundapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ufund.api.ufundapi.model.Need;

/**
 * Implements the functionality for JSON file-based peristance for Needs
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Team Trees
 */
@Component
public class NeedFileDAO implements NeedDAO{

    private static final Logger LOG = Logger.getLogger(NeedFileDAO.class.getName());

    Map<Integer,Need> Needs;   
    private ObjectMapper objectMapper;  
    private static int nextId;  
    private String filename;    

    /**
     * Creates a Need File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public NeedFileDAO(@Value("${Needs.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  
    }

    /**
     * Generates the next id for a new {@linkplain Need need}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map
     * 
     * @return  The array of {@link Need needs}, may be empty
     */
    private Need[] getNeedsArray() {
        return getNeedsArray(null);
    }

    /**
     * Generates an array of {@linkplain Need needs} from the tree map for any
     * {@linkplain Need needs} that contains the text specified by containsText
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Need needs}
     * in the tree map
     * 
     * @return  The array of {@link Need needs}, may be empty
     */
    private Need[] getNeedsArray(String containsText) { 
        ArrayList<Need> NeedArrayList = new ArrayList<>();

        for (Need Need : Needs.values()) {
            if (containsText == null || Need.getName().contains(containsText)) {
                NeedArrayList.add(Need);
            }
        }

        Need[] NeedArray = new Need[NeedArrayList.size()];
        NeedArrayList.toArray(NeedArray);
        return NeedArray;
    }

    /**
     * Saves the {@linkplain Need needs} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Need needs} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Need[] NeedArray = getNeedsArray();
        objectMapper.writeValue(new File(filename),NeedArray);
        return true;
    }

    /**
     * Loads {@linkplain Need needs} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        Needs = new TreeMap<>();
        nextId = 0;

        Need[] NeedArray = objectMapper.readValue(new File(filename),Need[].class);

        for (Need Need : NeedArray) {
            Needs.put(Need.getId(),Need);
            if (Need.getId() > nextId)
                nextId = Need.getId();
        }
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] getNeeds() {
        synchronized(Needs) {
            return getNeedsArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need[] findNeeds(String containsText) {
        synchronized(Needs) {
            return getNeedsArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need getNeed(int id) {
        synchronized(Needs) {
            if (Needs.containsKey(id))
                return Needs.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need createNeed(Need Need) throws IOException {
        synchronized(Needs) {
            Need newNeed = new Need(nextId(),Need.getName(),Need.getCost(),Need.getDescription());
            Needs.put(newNeed.getId(),newNeed);
            save(); // may throw an IOException
            return newNeed;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Need updateNeed(Need Need) throws IOException {
        synchronized(Needs) {
            if (Needs.containsKey(Need.getId()) == false)
                return null;  

            Needs.put(Need.getId(),Need);
            save(); 
            return Need;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteNeed(int id) throws IOException {
        synchronized(Needs) {
            if (Needs.containsKey(id)) {
                Needs.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}