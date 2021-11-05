package com.url.shortener.service;

import com.url.shortener.configuration.ExpirationConfig;
import com.url.shortener.exchanges.CreateUrlRequest;
import com.url.shortener.exchanges.CreateUrlResponse;
import com.url.shortener.model.Url;
import com.url.shortener.model.User;
import com.url.shortener.repository.UrlRepository;
import com.url.shortener.utility.HashUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    HashUtility hashUtility;



    @Autowired
    private ExpirationConfig expirationConfig;

    public CreateUrlResponse createUrlRequest(CreateUrlRequest createUrlRequest)
    {
        System.out.println("Inside service layer create url request");
        User user1=urlRepository.getUser(createUrlRequest.getUserId());
        System.out.println("After get user call in  service layer request");
        if(user1==null)
        {
            user1 = User.builder().build();
            urlRepository.createUser(user1);
            System.out.println("After creating user  in  service layer request");
        }
        System.out.println("After creating user with userid:"+user1.getUserId());
        Url url= Url.builder().originalUrl(createUrlRequest.getOriginalUrl()).createdAt(new Date()).expirationDate(getexpiryDate()).user(user1).urlKey(hashUtility.getKey(createUrlRequest.getOriginalUrl())).build();
        urlRepository.createUrl(url);
        System.out.println("After creating short url with id:"+url.getUrlKey());
        String response="http://localhost:8080/redirect/"+url.getUrlKey();
        System.out.println("Short url is"+response);
         CreateUrlResponse c1=CreateUrlResponse.builder().shortUrl(response).build();
        System.out.println("CreateUrlResponse:"+c1);

        return c1;
       // CreateUrlResponse
                //CreateUrlResponse.builder().shortUrl(response).build();
    }

    /*public Date getExpirationDate()
    {
        Date date=new Date();
        date.
    }*/

    public String getOriginalUrlFromKey(String urlKey)
    {
        Url url= urlRepository.getUrl(urlKey);
        if (url!=null)
        {
            return url.getOriginalUrl();
        }
        else
            return null;
    }

    public String deleteUrlFromKey(String urlKey)
    {
        Url url=urlRepository.getUrl(urlKey);
        urlRepository.deleteUrl(url);
        return "Success";
    }
    private Date getexpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,expirationConfig.getDefDays());
        return calendar.getTime();
    }

}
