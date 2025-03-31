package com.mydevcave.speech_agent_ai.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WhisperServiceImpl implements WhisperService {

    @Value("${external.whisper.url}")
    private String whisperUrl;

    @Value("${external.whisper.api-key}")
    private String apiKey;

    private final WebClient webClient;

    public WhisperServiceImpl(
            WebClient.Builder webClientBuilder,
            @Value("${external.whisper.url}") String whisperUrl) {
        this.webClient = webClientBuilder.baseUrl(whisperUrl).build();
    }

    @Override
    public Mono<ResponseEntity<String>> process(Mono<FilePart> fileMono) {
        return fileMono.flatMap(filePart ->
                DataBufferUtils.join(filePart.content())
                        .flatMap(dataBuffer -> {
                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(bytes);
                            DataBufferUtils.release(dataBuffer);

                            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes) {
                                @Override
                                public String getFilename() {
                                    return filePart.filename();
                                }
                            };

                            return webClient.post()
                                    .contentType(MediaType.MULTIPART_FORM_DATA)
                                    .header("X-API-KEY", apiKey)
                                    .body(BodyInserters.fromMultipartData("file", byteArrayResource))
                                    .retrieve()
                                    .toEntity(String.class);
                        })
        );
    }
}

