package com.snowalker.shield.match.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author snowalker
 * @version 1.0
 * @date 2022/2/2 22:43
 * @className
 * @desc
 */
public class EventWrapperFactory implements EventFactory<EventWrapper> {

    @Override
    public EventWrapper newInstance() {
        return EventWrapper.builder().build();
    }
}
