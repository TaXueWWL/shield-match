package com.snowalker.shield.match.disruptor;

import com.lmax.disruptor.ExceptionHandler;
import java.util.function.BiConsumer;

/**
 * @author snowalker
 * @version 1.0
 * @date 2022/2/2 23:04
 * @className
 * @desc
 */
public class GlobalExceptionHandler<T> implements ExceptionHandler<T> {
    public final String name;
    public final BiConsumer<Throwable, Long> onException;

    @Override
    public void handleEventException(Throwable throwable, long seq, T event) {
        System.out.println("disruptor " + name + " seq:" + seq + " exist exception");
        onException.accept(throwable, seq);
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        System.out.println("disruptor " + name + " Start exception");
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        System.out.println("disruptor " + name + " Shutdown exception");
    }

    @SuppressWarnings("all")
    public GlobalExceptionHandler(final String name, final BiConsumer<Throwable, Long> onException) {
        this.name = name;
        this.onException = onException;
    }
}
