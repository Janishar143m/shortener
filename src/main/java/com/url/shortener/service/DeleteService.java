package com.url.shortener.service;

import com.url.shortener.model.Url;
import com.url.shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@EnableScheduling
@Component
public class DeleteService {

    @Autowired
    private UrlRepository urlRepository;

    @Async
    @Scheduled(fixedDelay =1)
    @Transactional
    public void delete()
    {
        List<Url> urlList=urlRepository.expiredUrl();
        for(Url url:urlList)
        {
            urlRepository.deleteUrl(url);
        }
    }


}
