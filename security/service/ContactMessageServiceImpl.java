package com.angel.security.service;

import com.angel.security.dto.RequestMessageDto;
import com.angel.security.exception.BusinessException;
import com.angel.security.exception.ResourceNotFoundException;
import com.angel.security.model.ContactMessageModel;
import com.angel.security.repository.IContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.angel.security.util.Util.ERRDTO;
import static com.angel.security.util.Util.NOTFOUND;

@Service
@RequiredArgsConstructor
public class ContactMessageServiceImpl implements IContactMessageService {

    private final IContactMessageRepository repository;
    private final MailService mailService;

    @Override
    public void create(RequestMessageDto dto) {

        ContactMessageModel message = new ContactMessageModel();
        message.setName(dto.getData().getName());
        message.setEmail(dto.getData().getEmail());
        message.setDescription(dto.getData().getDescription());

        repository.save(message);

        mailService.sendContactConfirmation(
                dto.getData().getEmail(),
                dto.getData().getName()
        );
    }

    @Override
    public void markAsRead(RequestMessageDto dto) {

        int rows = repository.updateStatus(
                dto.getData().getMessageId(),
                "CSM-001",
                dto.getData().getUserResponse()
        );

        if(rows == 0){
            throw new ResourceNotFoundException(NOTFOUND);
        }

    }

    @Override
    public void markAsAnswered(RequestMessageDto dto){

        if(dto.getData() == null){
            throw new BusinessException(ERRDTO);
        }

        int rows = repository.updateStatus(
                dto.getData().getMessageId(),
                "CSM-002",
                dto.getData().getUserResponse()
        );

        if(rows == 0){
            throw new ResourceNotFoundException(NOTFOUND);
        }

        ContactMessageModel message =
                repository.findById(dto.getData().getMessageId());

        mailService.sendContactReplyEmail(
                message.getEmail(),
                message.getName(),
                dto.getData().getUserResponse()
        );

    }

    @Override
    public void deleteLogical(String messageId) {

        int rows = repository.logicalDelete(messageId);

        if(rows == 0){
            throw new ResourceNotFoundException(NOTFOUND);
        }

    }

    @Override
    public List<ContactMessageModel> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ContactMessageModel> getRead() {
        return repository.findByStatus("CSM-001");
    }

    @Override
    public List<ContactMessageModel> getAnswered() {
        return repository.findByStatus("CSM-002");
    }
}
