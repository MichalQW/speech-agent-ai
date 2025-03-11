package com.mydevcave.speech_agent_ai.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface VoiceMessagesService {
    Mono<ResponseEntity<String>> askWhisper(Mono<FilePart> file);
}
