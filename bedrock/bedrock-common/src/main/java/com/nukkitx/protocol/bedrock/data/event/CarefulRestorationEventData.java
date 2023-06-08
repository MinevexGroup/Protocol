package com.nukkitx.protocol.bedrock.data.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Kaooot
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarefulRestorationEventData implements EventData {

    public static final CarefulRestorationEventData INSTANCE = new CarefulRestorationEventData();

    @Override
    public EventDataType getType() {
        return EventDataType.CAREFUL_RESTORATION;
    }
}