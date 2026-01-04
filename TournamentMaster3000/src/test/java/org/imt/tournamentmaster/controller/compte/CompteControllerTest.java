package org.imt.tournamentmaster.controller.compte;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // on demande un port random disponible sur la machine
@AutoConfigureRestTestClient
@Disabled("À activer au cours du TP")
public class CompteControllerTest {

    @LocalServerPort
    private int port; // on injecte le port aléatoire pour le réutiliser

    @Autowired
    RestTestClient restTestClient; // encapsule un RestClient pour faire des appels HTTP puis des assertions sur la réponse

    @Test
    public void testWhoAmI_notAuthenticated_ko() {
        restTestClient.get()
                .uri("http://localhost:%d/compte/whoami".formatted(port)) // on configure l'uri avec le port aléatoire
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void testWhoAmI_authenticatedAsUser_ok() {
        restTestClient.get()
                .uri("http://localhost:%d/compte/whoami".formatted(port))
                .exchangeSuccessfully()
                .expectBody(String.class)
                .isEqualTo("user");
    }

    @Test
    public void testWhoAmI_authenticatedAsAdmin_ok() {
        restTestClient.get()
                .uri("http://localhost:%d/compte/whoami".formatted(port))
                .exchangeSuccessfully()
                .expectBody(String.class)
                .isEqualTo("admin");
    }

    @Test
    public void testSecret_notAuthenticated_ko() {
        restTestClient.get()
                .uri("http://localhost:%d/compte/secret".formatted(port))
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void testSecret_authenticatedAsUser_ko() {
        restTestClient.get()
                .uri("http://localhost:%d/compte/secret".formatted(port))
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void testSecret_authenticatedAsAdmin_ok() {
        restTestClient.get()
                .uri("http://localhost:%d/compte/secret".formatted(port))
                .exchangeSuccessfully()
                .expectBody(String.class)
                .isEqualTo("This is a secret admin page!");
    }

    /**
     * Encode username et password en Basic Auth
     * La chaîne retournée s'utilise dans un header http "Authorization"
     * Exemple de valeur pour le header Authorization: Basic am91ZXrDoDpjbGFpcm9ic2N1cg==
     * Le 1er élément de la chaîne indique le type d'authentification (ici Basic), le 2nd est le secret encodé, ici en base64 au format user:password
     * L'authentification Basic n'est pas sécurisée en soi, il faut l'utiliser avec HTTPS pour que le secret ne soit pas interceptable en clair
     * Il existe d'autres modes, comme le Bearer token qui est très répandu avec le format JWT https://www.jwt.io/
     */
    private String getBasicAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }
}
