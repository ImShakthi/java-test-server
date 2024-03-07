package com.imshakthi.javatestserver.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageBody {
    private String message;
}
