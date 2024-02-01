package com.tutorial.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import java.io.IOException;

public class GoogleAuthHelper {

    private static final String CLIENT_ID = "514809554910-2itsnr8fve4u6fi4mft51ch23v0q8vn4.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/auth-web-openid/login";
    private static final String SCOPE = "email profile";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static GoogleAuthorizationCodeFlow codeFlow = null;

    private static GoogleAuthorizationCodeFlow getGoogleAuthorizationCodeFlow() {
        if (codeFlow == null) {
            codeFlow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, java.util.Collections.singleton(SCOPE))
                    .setAccessType("offline")
                    .build();
        }
        return codeFlow;
    }
    
    public static String buildLoginUrl() {
        GoogleAuthorizationCodeFlow flow = getGoogleAuthorizationCodeFlow();
        return flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    }

    public static String getToken(String code) throws IOException {
        GoogleAuthorizationCodeFlow flow = getGoogleAuthorizationCodeFlow();
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        return response.getAccessToken();
    }

    public static String getUserInfo(String accessToken) throws IOException {
        Oauth2 oauth2 = new Oauth2.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                .setApplicationName("Google Login")
                .setHttpRequestInitializer(request -> request.getHeaders().setAuthorization("Bearer " + accessToken))
                .build();
        Userinfo userinfo = oauth2.userinfo().get().execute();
        return userinfo.getName();
    }
}
