package com.pamihnenkov.supplier.controller;

import com.pamihnenkov.supplier.dadata.domain.BasicRequest;
import com.pamihnenkov.supplier.dadata.domain.DadataOrganization;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@RestController
public class DadataController {

    @GetMapping("/organization/{inn}")
    public void printOrganization (String inn){
        Flux<DadataOrganization> organization = WebClient.create()
            .post()
            .uri("https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/party")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .header("Authorization", "Token ea55bf72b2a2948774e6e429989c70310c3f5b51")
            .body(BodyInserters.fromValue(new BasicRequest(inn)))
                .retrieve().bodyToFlux(DadataOrganization.class);


    }
}
