package com.eazybytes.eazystore.service;


import com.eazybytes.eazystore.dto.ContactDto;

public interface IContactService {
    boolean saveContact(ContactDto contactDto);
}
