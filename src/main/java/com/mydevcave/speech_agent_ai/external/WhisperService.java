package com.mydevcave.speech_agent_ai.external;

import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface WhisperService {
    Mono<ResponseEntity<String>> sendPostRequest(Mono<FilePart> fileMono);
};