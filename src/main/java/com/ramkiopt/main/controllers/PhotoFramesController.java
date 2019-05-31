package com.ramkiopt.main.controllers;

import com.ramkiopt.main.services.utils.ResponseCustomizationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo-frames")
public class PhotoFramesController implements ResponseCustomizationService {
    @PostMapping("/create")
    public Boolean createPhotoFrame() {
        return false;
    }
}
