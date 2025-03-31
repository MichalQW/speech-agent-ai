package com.mydevcave.speech_agent_ai.service;

import com.mydevcave.speech_agent_ai.external.WhisperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VoiceMessagesServiceImpl implements VoiceMessagesService {
    private final WhisperService whisperService;

    public Mono<ResponseEntity<String>> convertSpeechToText(Mono<FilePart> file) {
        return whisperService.process(file);
    }
}
