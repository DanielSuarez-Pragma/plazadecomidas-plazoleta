package com.plazoleta.infraestructure.input.rest.client.mapper;

import com.plazoleta.application.dto.request.MessageRequestDto;
import com.plazoleta.domain.model.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITwilioMapper {
    default MessageRequestDto toSmsRequest(MessageModel smsMessageModel) {
        if ( smsMessageModel == null ) {
            return null;
        }

        MessageRequestDto messageRequestDto = new MessageRequestDto();
        messageRequestDto.setNumber(smsMessageModel.getNumber());
        messageRequestDto.setMessage(smsMessageModel.getMessage());

        return messageRequestDto;
    }
}
