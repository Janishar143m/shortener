package com.url.shortener.controller;

import com.url.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.URIException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/redirect")
public class RedirectionController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/{key}")
    public ResponseEntity getOriginalUrl(@PathVariable String key)
    {
        ResponseEntity res = null;
        System.out.println("Inside redirection controller");
        String originalUrl= urlService.getOriginalUrlFromKey(key);
        System.out.println("Original url fetched is:"+originalUrl);
        if(originalUrl!=null) {

            try {
                res = ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(new URI(originalUrl)).build();
            } catch (URISyntaxException uri) {
                res = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else
            res= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return res;

    }

}
