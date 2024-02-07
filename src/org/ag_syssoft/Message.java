package org.ag_syssoft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Message {

    public Message () {
        header = new HashMap<>();
        payload = new HashMap<>();
    }

    public Message add ( String key, String value ) {
        payload.put(key,value);
        return this;
    }

    public Message add ( String key, int value ) {
        payload.put(key,String.valueOf(value));
        return this;

    }

    public Message addHeader ( String key, String value ) {
        header.put(key,value);
        return this;
    }

    public Message addHeader ( String key, int value ) {
        header.put(key,String.valueOf(value));
        return this;
    }

    public String query ( String key ) {
        return payload.get(key);
    }

    public String queryHeader ( String key ) {
        return header.get(key);
    }

    public Map<String,String> getPayload () {
        return payload;
    }

    public Map<String,String> getHeader () {
        return header;
    }

    public String toJson () throws JsonProcessingException {
        return serializer.writeValueAsString(this);
    }

    public static Message fromJson ( String s ) throws IOException {
        return serializer.readValue(s,Message.class);
    }

    private final HashMap<String,String> header;
    private final HashMap<String,String> payload;
    private static final ObjectMapper serializer = new ObjectMapper();
}
