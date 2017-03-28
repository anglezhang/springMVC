package com.cyw.common.util;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

// TODO: Auto-generated Javadoc
/**
 * The Class TimestampTypeAdapter.
 */
public class TimestampTypeAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp>{  
    
    /** The format. */
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
    
    /* (non-Javadoc)
     * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
     */
    public JsonElement serialize(Timestamp ts, Type t, JsonSerializationContext jsc) {      
        String dfString = format.format(new Date(ts.getTime()));      
        return new JsonPrimitive(dfString);      
    }      
    
    /* (non-Javadoc)
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    public Timestamp deserialize(JsonElement json, Type t, JsonDeserializationContext jsc) throws JsonParseException {      
        if (!(json instanceof JsonPrimitive)) {      
            throw new JsonParseException("The date should be a string value");      
        }      
     
            Date date = null;
			try {
				date = format.parse(json.getAsString());
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      
            return new Timestamp(date.getTime());      
            
    }      
}   
