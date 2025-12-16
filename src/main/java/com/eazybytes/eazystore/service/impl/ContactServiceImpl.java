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
        try {
            Contact contact = transformToEntity(contactDto);
            contact.setCreatedAt(Instant.now());
            contact.setCreatedBy(contactDto.getName());
            contactRepository.save(contact);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    private Contact transformToEntity(ContactDto contactdto){
        Contact contact=new Contact();
        BeanUtils.copyProperties(contactdto,contact);
        return contact;
    }
}
