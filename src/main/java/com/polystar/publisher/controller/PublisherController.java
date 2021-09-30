package com.polystar.publisher.controller;


import com.polystar.publisher.exception.CustomException;
import com.polystar.publisher.exception.CustomExceptionDto;
import com.polystar.publisher.model.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    //used by the server to read files at the client locally
    @GetMapping("read-files")
    public ResponseEntity readFiles(@RequestParam List<String> filesPaths) throws CustomException {
        try {
            publisherService.getOneTimeTopWordInStringConcat(filesPaths);
            return new ResponseEntity("Done", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(new CustomExceptionDto(e), HttpStatus.BAD_REQUEST);
        }
    }


    //get top word concatenated by server for long time analysis
    @PostMapping("top-Repeated")
    public ResponseEntity mostRepeatedWordsConcatCurrent(@RequestParam String filePath) throws CustomException {
        try {
            return new ResponseEntity(publisherService.topRepeatedFiveWords(filePath), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(new CustomExceptionDto(e), HttpStatus.BAD_REQUEST);
        }
    }

}
