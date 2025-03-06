package com.mydevcave.speech_agent_ai.validation;

import org.springframework.web.multipart.MultipartFile;

public class VoiceMessagesValidation {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String[] ALLOWED_EXTENSIONS = {"mp3", "wav"};

    public static boolean isValid(MultipartFile file) {
        return isNotEmpty(file) && isSizeValid(file) && isExtensionValid(file);
    }

    public static boolean isNotEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty.");
        }
        return true;
    }

    public static boolean isSizeValid(MultipartFile file) {
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File size exceeds the maximum limit of 10MB.");
        }
        return true;
    }

    public static boolean isExtensionValid(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IllegalArgumentException("File name is missing.");
        }

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        boolean isValidExtension = false;
        for (String allowed : ALLOWED_EXTENSIONS) {
            if (allowed.equals(extension)) {
                isValidExtension = true;
                break;
            }
        }

        if (!isValidExtension) {
            throw new IllegalArgumentException("Invalid file extension. Only .mp3 and .wav are allowed.");
        }

        return true;
    }
}
