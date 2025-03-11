package com.mydevcave.speech_agent_ai.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

@Service
public class WhisperServiceImpl implements WhisperService {

    private final WebClient webClient;

    public WhisperServiceImpl(
            WebClient.Builder webClientBuilder,
            @Value("${external.whisper.url}") String whisperUrl) {
        this.webClient = webClientBuilder.baseUrl(whisperUrl).build();
    }

    @Override
    public Mono<ResponseEntity<String>> sendPostRequest(Mono<FilePart> fileMono) {
        return fileMono.flatMap(filePart ->
                filePart.content()
                        .reduce(new ByteArrayOutputStream(),
                                (baos, dataBuffer) -> {
                                    try {
                                        Channels.newChannel(baos).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                                        return baos;
                                    } catch (IOException e) {
                                        throw new RuntimeException("Can't read dataBuffer to ByteArrayOutputStream", e);
                                    } finally {
                                        DataBufferUtils.release(dataBuffer);
                                    }
                                }
                        ).flatMap(baos -> {
                            ByteArrayResource byteArrayResource = new ByteArrayResource(baos.toByteArray()) {
                                @Override
                                public String getFilename() {
                                    return filePart.filename();
                                }
                            };

                            return webClient.post()
                                    .contentType(MediaType.MULTIPART_FORM_DATA)
                                    .body(org.springframework.web.reactive.function.BodyInserters.fromMultipartData("file", byteArrayResource))
                                    .retrieve()
                                    .toEntity(String.class);
                        })
        );
    }
}