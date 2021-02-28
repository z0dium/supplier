package com.pamihnenkov.supplier.dadata;

import com.pamihnenkov.supplier.dadata.domain.*;
import com.pamihnenkov.supplier.dadata.exception.DadataException;
import com.pamihnenkov.supplier.dadata.exception.ErrorDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Should be build using {@link DadataClientBuilder}
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class DadataClient {

    private static final String FIND_BY_ID_PREFIX = "/findById";

    private final WebClient webClient;

    protected <T> Mono<Suggestion<T>> findById(SuggestionType<T> suggestionType, BasicRequest request) {
        Mono<Suggestion<T>> result = executeOperation(suggestionType.getResponseClass(), request, FIND_BY_ID_PREFIX,
                suggestionType.getFindByIdOperationPrefix()).next();
        System.out.println(result);

        return result;
    }

    protected <T> Flux<Suggestion<T>> executeOperation(ParameterizedTypeReference<DadataResponse<T>> responseClass,
                                                       BasicRequest request, String operationPrefix,
                                                       String suggestionTypePrefix) {
        return webClient
                .post().uri(operationPrefix + suggestionTypePrefix)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .flatMap(clientResponse -> responseToBody(clientResponse, responseClass))
                .map(DadataResponse::getSuggestions)
                .flatMapMany(Flux::fromIterable);
    }

    private static <T> Mono<T> responseToBody(ClientResponse response, ParameterizedTypeReference<T> type) {
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(type);
        } else {
            return response.bodyToMono(ErrorDetails.class)
                    .flatMap(error -> Mono.error(new DadataException(response.statusCode(), error)));
        }
    }
}
