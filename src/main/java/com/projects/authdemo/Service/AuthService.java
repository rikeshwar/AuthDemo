package com.projects.authdemo.Service;


import com.projects.authdemo.Security.Model.Client;
import com.projects.authdemo.Security.Repo.ClientRepository;
import com.projects.authdemo.Security.Repo.JpaRegisteredClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private JpaRegisteredClientRepository jpaRegisteredClientRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<Client> registerClient(String name,String password)
    {

        RegisteredClient postmanClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(name)
                .clientSecret(bCryptPasswordEncoder.encode(password))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .postLogoutRedirectUri("http://127.0.0.1:8080/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        jpaRegisteredClientRepository.save(postmanClient);
        return clientRepository.findByClientName(name);
    }
}
