package com.mydevcave.speech_agent_ai.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class WhisperServiceImpl implements WhisperService {

    @Value("${external.whisper.url}")
    private String whisperUrl;

    @Value("${external.whisper.api-key}")
    private String apiKey;

    private final WebClient webClient;

    public WhisperServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(whisperUrl).build();
    }

    public ResponseEntity<String> sendPostRequest(MultipartFile file) {
        try {
            ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            return webClient.post()
                    .uri(whisperUrl)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .header("X-API-KEY", apiKey)
                    .body(Mono.just(fileResource), ByteArrayResource.class)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file contents", e);
        }
    }
}
