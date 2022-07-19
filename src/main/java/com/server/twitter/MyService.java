package com.server.twitter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyService {
    Map<String, String> responses;

    @PostConstruct
    public void init() {
        responses = new HashMap<>();
        readFile();
    }

    public String getJson(String id) {
        return responses.get(id);
    }

    public void readFile() {
        String line = "";

        try {
            InputStream resource = new ClassPathResource("responses.txt").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            int count = 1;
            while ((line = reader.readLine()) != null) {
                line = line.substring(0, line.length()-1);
                String id = createId(count++);
                responses.put(id, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String createId (int n) {
        String s = new String("server-");
        if (n <= 9)
            s = s + "000" + n;
        else if ((n >= 10) && (n <= 99))
            s = s + "00" + n;
        else if ((n >= 100) && (n <= 999))
            s = s + "0" + n;
        else
            s = s + n;

        return s;
    }
}
