package com.plazoleta.infraestructure.input.rest.client.adapter;

import com.plazoleta.application.dto.request.MessageRequestDto;
import com.plazoleta.domain.model.MessageModel;
import com.plazoleta.domain.spi.ITwilioFeignClientPort;
import com.plazoleta.infraestructure.input.rest.client.TwilioFeignClient;
import com.plazoleta.infraestructure.input.rest.client.mapper.ITwilioMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TwilioFeignAdapter implements ITwilioFeignClientPort {

    private final TwilioFeignClient twilioFeignClient;
    private final ITwilioMapper twilioMapper;

    @Override
    public void sendMessage(MessageModel messageModel) {
        MessageRequestDto messageRequestDto = twilioMapper.toSmsRequest(messageModel);
        twilioFeignClient.sendMessage(messageRequestDto);
    }
}
