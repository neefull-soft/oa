package com.mvc.base.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectChangeUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Object changeObject(Object fst, Class sed){
        try {
            String jsonStr = MAPPER.writeValueAsString(fst);
            Object detail =  MAPPER.readValue(jsonStr , sed);

            return  detail ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
