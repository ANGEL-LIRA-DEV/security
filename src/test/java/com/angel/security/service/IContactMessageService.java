package com.angel.security.service;

import com.angel.security.dto.RequestMessageDto;
import com.angel.security.model.ContactMessageModel;

import java.util.List;

public interface IContactMessageService {

    void create(RequestMessageDto dto);
    void markAsRead(RequestMessageDto dto);
    void deleteLogical(String messageId);

    List<ContactMessageModel> getAll();
    List<ContactMessageModel> getRead();
    List<ContactMessageModel> getAnswered();

}
