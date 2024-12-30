package com.lcwd.user.config.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private OAuth2AuthorizedClientManager manager;

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        String token = manager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-client").principal("internal").build())
                .getAccessToken().getTokenValue();
        request.getHeaders().add("Authorization", "Bearer " + token);
        return execution.execute(request, body);
    }

}
