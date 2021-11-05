package com.url.shortener.controller;

import com.url.shortener.exchanges.CreateUrlRequest;
import com.url.shortener.exchanges.CreateUrlResponse;
import com.url.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/create")
    @Transactional
    public CreateUrlResponse createUrl(@RequestBody CreateUrlRequest createUrlRequest)
    {
        System.out.println("Inside post method");

        if(createUrlRequest.getUserId()!=null) {

            System.out.println("Inside userid not null");
            CreateUrlResponse c1=urlService.createUrlRequest(createUrlRequest);
            System.out.println("CreateUrlResponse in controller:"+c1);
            return c1;
        }
        else
            return CreateUrlResponse.builder().shortUrl("User Id Mandatory").build();

    }

    @DeleteMapping("/delete/{key}")
    @Transactional
    public ResponseEntity deleteUrl(@PathVariable String Key)
    {
        String response=urlService.deleteUrlFromKey(Key);
        return ResponseEntity.ok().body(response);
    }
}
