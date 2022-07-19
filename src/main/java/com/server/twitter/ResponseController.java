package com.server.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @Autowired
    MyService myService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getResponse (@PathVariable(value = "id") String id) {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(myService.getJson(id), httpHeaders, HttpStatus.OK);
    }

}
