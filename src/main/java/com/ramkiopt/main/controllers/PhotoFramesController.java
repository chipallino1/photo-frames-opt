package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.PhotosDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.app.photos.PhotosService;
import com.ramkiopt.main.services.utils.FileStorageService;
import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/photo-frames")
public class PhotoFramesController {
    private final PhotoFramesStructureService photoFramesStructureService;
    private final BaseResponseService responseService;
    private final FileStorageService fileStorageService;
    private final PhotosService<PhotosDto> photosService;

    @Autowired
    public PhotoFramesController(PhotoFramesStructureService photoFramesStructureService,
                                 FileStorageService fileStorageService, BaseResponseService responseService, PhotosService<PhotosDto> photosService) {
        this.photoFramesStructureService = photoFramesStructureService;
        this.fileStorageService = fileStorageService;
        this.responseService = responseService;
        this.photosService = photosService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity createPhotoFrame(@RequestBody @Valid PhotoFramesDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.createErrorInfo(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        dto = photoFramesStructureService.createPhotoFrame(dto);
        return responseService.createResponseEntity(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/addPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createPhoto4PhotoFrame(@PathParam("id") Long id, @RequestParam MultipartFile file) throws IOException {
        photosService.delete(id);
        String imageSrc = fileStorageService.storeFile(file);
        PhotosDto dto = new PhotosDto();
        dto.setPhotoFrameId(id);
        dto.setPhotoSrc(imageSrc);
        photosService.create(dto);
        return responseService.createResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PutMapping("/update")
    public ResponseEntity updatePhotoFrame(@RequestBody PhotoFramesDto photoFramesDto) {
        return responseService.createResponseEntity(photoFramesStructureService.updatePhotoFrame(photoFramesDto.getId(),
                photoFramesDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity readPhotoFrame(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(photoFramesStructureService.readPhotoFrame(id), HttpStatus.OK);
    }

    @GetMapping("/allByName")
    public ResponseEntity readAllPhotoFrames(@PathParam("name") String name,
                                             @PathParam("pageNumber") Integer pageNumber,
                                             @PathParam("offset") Integer offset) {
        Pageable pageable = PageRequest.of(pageNumber, offset);
        return responseService.createResponseEntity(photoFramesStructureService.readAllByName("%" + name + "%",
                pageable), HttpStatus.OK);
    }

    @GetMapping("/allByColor")
    public ResponseEntity readAllByColor(@PathParam("color") String color,
                                         @PathParam("pageNumber") Integer pageNumber,
                                         @PathParam("offset") Integer offset) {
        return responseService.createResponseEntity(photoFramesStructureService.
                readAllByColor(color, pageNumber, offset), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePhotoFrame(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(photoFramesStructureService.deletePhotoFrame(id), HttpStatus.OK);
    }
}
