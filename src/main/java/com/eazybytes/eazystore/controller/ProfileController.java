package com.eazybytes.eazystore.controller;


import com.eazybytes.eazystore.dto.ProfileResponseDto;
import com.eazybytes.eazystore.service.IProfileService;
import com.eazybytes.eazystore.service.impl.ProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
private final IProfileService iProfileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(){

    return  ResponseEntity.ok(iProfileService.getProfile());



    }
}
