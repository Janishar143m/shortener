package com.url.shortener.utility;

import org.springframework.stereotype.Component;

@Component
public interface HashUtility {

    public String getKey(String url);
}
