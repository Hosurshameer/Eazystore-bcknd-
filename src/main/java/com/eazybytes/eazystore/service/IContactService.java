package com.eazybytes.eazystore.service;


import com.eazybytes.eazystore.dto.ContactDto;
import com.eazybytes.eazystore.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    boolean saveContact(ContactDto contactDto);
    List<ContactResponseDto> getAllOpenMessages();

    void updateMessageStatus(Long contactId,String status);
}
