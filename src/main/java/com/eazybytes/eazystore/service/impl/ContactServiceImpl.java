package com.eazybytes.eazystore.service.impl;
import com.eazybytes.eazystore.constants.ApplicationConstants;
import com.eazybytes.eazystore.dto.ContactDto;
import com.eazybytes.eazystore.dto.ContactResponseDto;
import com.eazybytes.eazystore.entity.Contact;
import com.eazybytes.eazystore.exception.ResourceNotFoundException;
import com.eazybytes.eazystore.repository.ContactRepository;
import  com.eazybytes.eazystore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ContactResponseDto> getAllOpenMessages() {
        List<Contact> contact=contactRepository.findByStatus(ApplicationConstants.OPEN_MESSAGE);
        return contact.stream().map(this::mapTOContactResponseDto).collect(Collectors.toList());

    }

    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact=contactRepository.findById(contactId).orElseThrow(() -> new ResourceNotFoundException("Contact","ContactId",contactId.toString()));
        contact.setStatus(status);
        contactRepository.save(contact);

    }

    private Contact transformToEntity(ContactDto contactdto){
        Contact contact=new Contact();
        BeanUtils.copyProperties(contactdto,contact);
        contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
        return contact;
    }


    private ContactResponseDto mapTOContactResponseDto(Contact contact){
        ContactResponseDto contactResponseDto=new ContactResponseDto(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMobileNumber(),
                contact.getMessage(),
                contact.getStatus()
        );
       return contactResponseDto;

    }
}
