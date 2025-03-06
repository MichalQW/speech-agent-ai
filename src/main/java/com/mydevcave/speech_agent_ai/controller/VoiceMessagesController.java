package com.mydevcave.speech_agent_ai.controller;

import com.mydevcave.speech_agent_ai.service.VoiceMessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class VoiceMessagesController {
    @Autowired
    VoiceMessagesServiceImpl voiceMessagesServiceImpl;

    @PostMapping("/upload")
    private ResponseEntity<String> uploadVoiceMessage(@RequestParam("file") MultipartFile file) {

//        if (!VoiceMessagesValidation.isValid(file)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File has to be max 10mb and extensions: mp3 or .wav");
//        }

        return voiceMessagesServiceImpl.askWhisper(file);
    }
}
