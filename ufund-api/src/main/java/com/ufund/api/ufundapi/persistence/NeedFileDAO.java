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


@Component
public class NeedFileDAO implements NeedDAO{

    private static final Logger LOG = Logger.getLogger(NeedFileDAO.class.getName());

    Map<Integer,Need> Needs;   
    private ObjectMapper objectMapper;  
    private static int nextId;  
    private String filename;    

    public NeedFileDAO(@Value("${Needs.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private Need[] getNeedsArray() {
        return getNeedsArray(null);
    }

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

    private boolean save() throws IOException {
        Need[] NeedArray = getNeedsArray();
        objectMapper.writeValue(new File(filename),NeedArray);
        return true;
    }

    private boolean load() throws IOException {
        Needs = new TreeMap<>();
        nextId = 0;

        Need[] NeedArray = objectMapper.readValue(new File(filename),Need[].class);

        for (Need Need : NeedArray) {
            Need.put(Need.getId(),Need);
            if (Need.getId() > nextId)
                nextId = Need.getId();
        }
        ++nextId;
        return true;
    }

    @Override
    public Need[] getNeeds() {
        synchronized(Needs) {
            return getNeedsArray();
        }
    }

    @Override
    public Need[] findNeeds(String containsText) {
        synchronized(Needs) {
            return getNeedsArray(containsText);
        }
    }

    @Override
    public Need getNeed(int id) {
        synchronized(Needs) {
            if (Needs.containsKey(id))
                return Needs.get(id);
            else
                return null;
        }
    }

    @Override
    public Need createNeed(Need Need) throws IOException {
        synchronized(Needs) {
            Need newNeed = new Need(nextId(),Need.getName());
            Needs.put(newNeed.getId(),newNeed);
            save(); // may throw an IOException
            return newNeed;
        }
    }

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