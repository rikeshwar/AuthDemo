package com.projects.authdemo.Controller;


import com.projects.authdemo.DTO.RegisterClientResponse;
import com.projects.authdemo.Security.Model.Client;
import com.projects.authdemo.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registerClient")
    public ResponseEntity<RegisterClientResponse> registerClient(@RequestHeader("name") String name,
                                                                 @RequestHeader("password") String password)
    {
        Optional<Client> client=authService.registerClient(name,password);
        if(client.isEmpty())
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        RegisterClientResponse registerClientResponse=new RegisterClientResponse();
        registerClientResponse.setClientName(client.get().getClientName());
        return new ResponseEntity<>(registerClientResponse, HttpStatus.OK);
    }
}
