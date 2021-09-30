package com.polystar.publisher.controller;


import com.polystar.publisher.exception.CustomException;
import com.polystar.publisher.exception.CustomExceptionDto;
import com.polystar.publisher.model.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    //get top word concatenated by server for long time analysis
    @PostMapping("top-Repeated")
    public ResponseEntity mostRepeatedWordsConcatCurrent(@RequestParam String filePath) throws CustomException {
        try {
            return new ResponseEntity(analyticsService.topRepeatedFiveWords(filePath), HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(new CustomExceptionDto(e), HttpStatus.BAD_REQUEST);
        }
    }

    //used by the server to read files at the client locally
    @GetMapping("read-files")
    public ResponseEntity readFiles(@RequestParam List<String> filesPaths) throws CustomException {
        try {
            analyticsService.getOneTimeTopWordInStringConcat(filesPaths);
            return new ResponseEntity("Done", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity(new CustomExceptionDto(e), HttpStatus.BAD_REQUEST);
        }
    }
}
