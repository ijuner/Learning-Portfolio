package com.bank.auth.userService;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {
@Id
    private String id;

    private String username;
    private String password;
     @Field("roles")
    private List<String> roles;
   

}
