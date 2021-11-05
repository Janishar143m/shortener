package com.url.shortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.Date;
@Entity(name="Url")
@Table(name="url")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {
    @Id
    private  String urlKey;
    private String originalUrl;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id",referencedColumnName ="userId")
    private User user;
    Date createdAt;
    Date expirationDate;
}
