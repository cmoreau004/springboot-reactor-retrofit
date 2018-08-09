package com.cmoreau.springbootreactorretrofit;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.cmoreau.springbootreactorretrofit.GitHubSamples.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class GitHubOrganizationIT {

    @Autowired
    private WebTestClient webTestClient;

    private WireMockServer wireMockServer;

    @BeforeEach
    void start() {
        wireMockServer = new WireMockServer(8081);
        wireMockServer.stubFor(get(urlEqualTo("/orgs/" + ORGANIZATION_NAME + "/members"))
                .willReturn(aResponse().withStatus(200)
                        .withBodyFile("organization_members.json")));
        wireMockServer.stubFor(get(urlEqualTo("/users/" + USER_LOGIN1))
                .willReturn(aResponse().withStatus(200)
                        .withBodyFile("user1.json")));
        wireMockServer.stubFor(get(urlEqualTo("/users/" + USER_LOGIN2))
                .willReturn(aResponse().withStatus(200)
                        .withBodyFile("user2.json")));
        wireMockServer.start();
    }

    @AfterEach
    void stop(){
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Get members stats should return ok and total stats")
    void getMembersStatsShouldReturnTotalAndOk(){
        webTestClient.get()
                .uri("/organizations/" + ORGANIZATION_NAME + "/members-stats")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo(getMembersStats());
    }

    @Test
    @DisplayName("Get members should return ok and members")
    void getMembersShouldReturnMembersAndOk(){
        webTestClient.get()
                .uri("/organizations/" + ORGANIZATION_NAME + "/members")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class).contains(USER_LOGIN1 + "(" + USER_NAME1+")", USER_LOGIN2 + "(" + USER_NAME2+")");
    }

}
