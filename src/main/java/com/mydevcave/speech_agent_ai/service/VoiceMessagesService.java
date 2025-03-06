package com.mydevcave.speech_agent_ai.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface VoiceMessagesService {
    ResponseEntity<String> askWhisper(MultipartFile file);
}
