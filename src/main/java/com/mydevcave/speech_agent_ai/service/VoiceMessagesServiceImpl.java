package com.mydevcave.speech_agent_ai.service;

import com.mydevcave.speech_agent_ai.external.WhisperService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@NoArgsConstructor
public class VoiceMessagesServiceImpl implements VoiceMessagesService {

    @Autowired
    WhisperService whisperService;

    public Mono<ResponseEntity<String>> askWhisper(Mono<FilePart> file) {
        return whisperService.sendPostRequest(file);
    }
}
