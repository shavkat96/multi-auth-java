/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutorial.auth;

/**
 *
 * @author Maiwand
 */
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;
import com.microsoft.aad.msal4j.UserNamePasswordParameters;
import java.net.MalformedURLException;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AuthenticationService {

    private static final String CLIENT_ID = "aa26af6c-7472-4927-807c-7ab1c372da81";
    private static final String CLIENT_SECRET = "5D38Q~DeFfYR8mNzzqv03U-YO6jYsoqP8JI6ka3A";
    private static final String AUTHORITY = "https://login.microsoftonline.com/c4dd7b62-b4a4-427d-a083-1e42ed34ace0/";
    private static final String REDIRECT_URI = "http://localhost:8080/auth-web-two/login";
    private static final String[] SCOPES = {"openid", "profile", "offline_access"};

    private PublicClientApplication msalClient;

    public void initializeMsalClient() throws MalformedURLException {
        msalClient = PublicClientApplication.builder(CLIENT_ID)
                .authority(AUTHORITY)
                .build();
    }

    public boolean validateOneTimePasscode(String username, String password) {
        try {
            UserNamePasswordParameters parameters = UserNamePasswordParameters.builder(
                    Collections.singleton("User.Read"),
                    username,
                    password.toCharArray())
                    .build();

            Future<IAuthenticationResult> resultFuture = msalClient.acquireToken(parameters);
            IAuthenticationResult result = resultFuture.get();

            return result != null && result.account() != null;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }
}
