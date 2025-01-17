package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.MessageModel;

public interface ITwilioFeignClientPort {

    void sendMessage(MessageModel messageModel);

}
