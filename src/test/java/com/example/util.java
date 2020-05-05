package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author: ZL
 * @Date: 2020/4/30 20:55
 * @Description:
 */
@Component
public class util {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"data\": {\"title\": \"Pool Party\",\"date\": {\"start\": \"2018-08-14T15:44:44.625Z\",\"end\": \"2018-08-14T18:44:44.625Z\"}}}";
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};

        HashMap<String,String> o = mapper.readValue(json, typeRef);
        System.out.println("Got " + o);
    }
}
