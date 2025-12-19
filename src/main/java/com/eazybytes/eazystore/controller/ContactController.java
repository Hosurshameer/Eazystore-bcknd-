package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ContactDto;
import com.eazybytes.eazystore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService iContactService;

    @PostMapping

    public ResponseEntity<String> saveContact(@RequestBody ContactDto contactDto){
        boolean isSaved = iContactService.saveContact(contactDto);

        if(isSaved){
            return ResponseEntity.status(HttpStatus.CREATED).body("Request processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("An error occurred while processing the request. Please try again or contact dev team");
        }
    }
}
