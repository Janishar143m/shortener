# shortener

URL Shortening low level Design

Implements below functionalities as of now

1)Given a long url and userid,it creates a short url for it like Bitly.

2)It also creates user if the userid passed doesnot exist in database.

3)If user enters the generated shortened url in browser,he gets redirected to the original page for which the short url was created.

3)Old urls whose expiration date time is less than current server time gets deleted through async service. 
