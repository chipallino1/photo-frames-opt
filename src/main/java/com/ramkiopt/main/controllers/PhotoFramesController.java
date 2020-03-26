package com.ramkiopt.main.controllers;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.utils.FileStorageService;
import com.ramkiopt.main.services.utils.FilesUtils;
import com.ramkiopt.main.services.utils.ReflectionUtilsService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/photo-frames")
public class PhotoFramesController {
    private final PhotoFramesStructureService photoFramesStructureService;
    private final BaseResponseService responseService;
    private final FileStorageService fileStorageService;

    @Autowired
    public PhotoFramesController(PhotoFramesStructureService photoFramesStructureService,
                                 FileStorageService fileStorageService, BaseResponseService responseService) {
        this.photoFramesStructureService = photoFramesStructureService;
        this.fileStorageService = fileStorageService;
        this.responseService = responseService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createPhotoFrame(@RequestBody @Valid PhotoFramesDto dto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.createErrorResponse(bindingResult.getFieldErrors());
        }
        dto = photoFramesStructureService.createPhotoFrame(dto);
        return responseService.createResponseEntity(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/addPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createPhoto4PhotoFrame(@PathParam("id") Long id, @RequestParam MultipartFile file) {
        String imageSrc = fileStorageService.storeFile(file);
        return responseService.createResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(FilesUtils.getContentType(request.getServletContext(), resource)))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updatePhotoFrame(@RequestBody @Valid PhotoFramesDto photoFramesDto,
                                                   BindingResult bindingResult) {
        List<String> excludedProperties = ReflectionUtilsService.getNullProperties(photoFramesDto);
        if (bindingResult.hasErrors()) {
            return responseService.createErrorResponse(bindingResult.getFieldErrors(),
                    excludedProperties.toArray(new String[0]));
        }
        return responseService.createResponseEntity(photoFramesStructureService.updatePhotoFrame(photoFramesDto.getId(),
                photoFramesDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readPhotoFrame(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(photoFramesStructureService.readPhotoFrame(id), HttpStatus.OK);
    }

    @GetMapping("/allByName")
    public ResponseEntity<Object> readAllPhotoFrames(@PathParam("name") String name,
                                                     @PathParam("pageNumber") Integer pageNumber,
                                                     @PathParam("offset") Integer offset) {
        Pageable pageable = PageRequest.of(pageNumber, offset);
        return responseService.createResponseEntity(photoFramesStructureService.readAllByName("%" + name + "%",
                pageable), HttpStatus.OK);
    }

    @GetMapping("/allOrderByCost")
    public ResponseEntity<Object> readAllOrderByCost(@PathParam("name") String name,
                                                     @PathParam("pageNumber") Integer pageNumber,
                                                     @PathParam("offset") Integer offset) {
        Pageable pageable = PageRequest.of(pageNumber, offset);
        return responseService.createResponseEntity(photoFramesStructureService.readAllOrderByCost("%" + name + "%",
                pageable), HttpStatus.OK);
    }

    @GetMapping("/allOrderByCostDesc")
    public ResponseEntity<Object> readAllOrderByCostDesc(@PathParam("name") String name,
                                                         @PathParam("pageNumber") Integer pageNumber,
                                                         @PathParam("offset") Integer offset) {
        Pageable pageable = PageRequest.of(pageNumber, offset);
        return responseService.createResponseEntity(photoFramesStructureService.readAllOrderByCostDesc("%" + name + "%",
                pageable), HttpStatus.OK);
    }

    @GetMapping("/allByNameOrderPopular")
    public ResponseEntity<Object> readAllPhotoFramesOrderByPopularity(@PathParam("name") String name,
                                                                      @PathParam("pageNumber") Integer pageNumber,
                                                                      @PathParam("offset") Integer offset) {
        Pageable pageable = PageRequest.of(pageNumber, offset);
        return responseService
                .createResponseEntity(photoFramesStructureService.readAllByNameOrderByPopularityDesc("%" + name + "%",
                        pageable), HttpStatus.OK);
    }

    @GetMapping("/allWithDiscounts")
    public ResponseEntity<Object> readAllWithDiscounts(@PathParam("pageNumber") Integer pageNumber,
                                                       @PathParam("offset") Integer offset) {
        return responseService
                .createResponseEntity(photoFramesStructureService.readAllWithDiscounts(pageNumber, offset),
                        HttpStatus.OK);
    }


    @GetMapping("/allByColors")
    public ResponseEntity<Object> readAllByColor(@PathParam("colors") String colors,
                                                 @PathParam("pageNumber") Integer pageNumber,
                                                 @PathParam("offset") Integer offset) {
        List<String> colorNames = colors.contains(",") ? Arrays.asList(colors.split(","))
                : Collections.singletonList(colors);
        return responseService.createResponseEntity(photoFramesStructureService.
                readAllByColors(colorNames, pageNumber, offset), HttpStatus.OK);
    }

    @GetMapping("/allBySizes")
    public ResponseEntity<Object> readAllBySize(@PathParam("sizes") String sizes,
                                                @PathParam("pageNumber") Integer pageNumber,
                                                @PathParam("offset") Integer offset) {
        List<String> sizeNames = sizes.contains(",") ? Arrays.asList(sizes.split(","))
                : Collections.singletonList(sizes);
        return responseService.createResponseEntity(photoFramesStructureService.
                readAllBySizes(sizeNames, pageNumber, offset), HttpStatus.OK);
    }

    @GetMapping("/allByAllParams")
    public ResponseEntity<Object> readAllBySize(@PathParam("colors") String colors,
                                                @PathParam("sizes") String sizes,
                                                @PathParam("insideMaterials") String insideMaterials,
                                                @PathParam("borderMaterials") String borderMaterials,
                                                @PathParam("pageNumber") Integer pageNumber,
                                                @PathParam("offset") Integer offset) {
        List<String> colorNames = colors.contains(",") ? Arrays.asList(colors.split(","))
                : Collections.singletonList(colors);
        List<String> sizeNames = sizes.contains(",") ? Arrays.asList(sizes.split(","))
                : Collections.singletonList(sizes);
        List<String> insideMaterialNames = insideMaterials.contains(",") ?
                Arrays.asList(insideMaterials.split(",")) : Collections.singletonList(insideMaterials);
        List<String> borderMaterialNames = borderMaterials.contains(",") ?
                Arrays.asList(borderMaterials.split(",")) : Collections.singletonList(borderMaterials);
        return responseService.createResponseEntity(
                photoFramesStructureService.readAllByAllParams(colorNames, sizeNames, insideMaterialNames,
                        borderMaterialNames, pageNumber, offset), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePhotoFrame(@PathVariable("id") Long id) {
        return responseService.createResponseEntity(photoFramesStructureService.deletePhotoFrame(id), HttpStatus.OK);
    }

    @GetMapping("/insideMaterials")
    public ResponseEntity<Object> getInsideMaterials() {
        return responseService.createResponseEntity(photoFramesStructureService.readAllInsideMaterials(), HttpStatus.OK);
    }

    @GetMapping("/borderMaterials")
    public ResponseEntity<Object> getBorderMaterials() {
        return responseService.createResponseEntity(photoFramesStructureService.readAllBorderMaterials(), HttpStatus.OK);
    }
}
