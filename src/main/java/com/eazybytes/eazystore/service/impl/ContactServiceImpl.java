package com.eazybytes.eazystore.service.impl;
import com.eazybytes.eazystore.dto.ContactDto;
import com.eazybytes.eazystore.entity.Contact;
import com.eazybytes.eazystore.repository.ContactRepository;
import  com.eazybytes.eazystore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;
    @Override
    public boolean saveContact(ContactDto contactDto) {

            Contact contact = transformToEntity(contactDto);

            contactRepository.save(contact);
            return true;

    }

    private Contact transformToEntity(ContactDto contactdto){
        Contact contact=new Contact();
        BeanUtils.copyProperties(contactdto,contact);
        return contact;
    }
}
