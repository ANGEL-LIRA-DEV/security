package com.angel.security.repository;

import java.util.List;
import com.angel.security.model.ContactMessageModel;

public interface IContactMessageRepository {

    int save(ContactMessageModel message) ;

    int updateStatus(String messageId, String status, String userResponse);

    int logicalDelete(String messageId);

    List<ContactMessageModel> findAll();
    List<ContactMessageModel> findByStatus(String status);
    ContactMessageModel findById(String messageId);

}
