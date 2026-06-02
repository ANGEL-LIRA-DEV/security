package com.angel.security.dto;

import com.angel.security.model.ContactMessageModel;

import lombok.Data;

@Data
public class RequestMessageDto {

    private String action; // ADD, READ, ANSWER, DELETE
    private ContactMessageModel data;

}
