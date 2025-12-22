package com.eazybytes.eazystore.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logging")
@Slf4j
public class LoggingController {
//    private static final Logger log= LoggerFactory.getLogger(LoggingController.class);
    @GetMapping
    public ResponseEntity<String> testLogging(){
        log.trace("Trace logging");
        log.debug("Debug logging");
        log.info("Info logging");
        log.warn("Warn logging");
        log.error("Error logging");
     return ResponseEntity.ok("Logging test");
    }

}
