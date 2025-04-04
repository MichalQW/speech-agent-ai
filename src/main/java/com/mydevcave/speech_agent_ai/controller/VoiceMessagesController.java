package com.mydevcave.speech_agent_ai.controller;

import com.mydevcave.speech_agent_ai.service.VoiceMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VoiceMessagesController {
    private final VoiceMessagesService service;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> uploadVoiceMessage(@RequestPart("file") Mono<FilePart> file) {
        return service.convertSpeechToText(file);
    }
}