package com.snowalker.shield.match.disruptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author snowalker
 * @version 1.0
 * @date 2022/2/2 22:44
 * @className
 * @desc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventWrapper<T> {

    T t;

    long id;

    boolean isAHandled;

    boolean isBHandled;
}
