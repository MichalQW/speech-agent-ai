package com.mydevcave.speech_agent_ai.service;

import com.mydevcave.speech_agent_ai.external.WhisperServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@NoArgsConstructor
public class VoiceMessagesServiceImpl implements VoiceMessagesService {
    WhisperServiceImpl whisperService;

    public ResponseEntity<String> askWhisper(MultipartFile file) {
        return whisperService.sendPostRequest(file);
    }
}
