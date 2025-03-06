package com.mydevcave.speech_agent_ai.external;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WhisperService {
    ResponseEntity<String> sendPostRequest(MultipartFile file) throws IOException;
}