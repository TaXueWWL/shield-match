package com.snowalker.shield.match.core;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: OrderKey
 * @description: TODO
 * @Since 2022/1/20 10:04 下午
 */
public class OrderKey {
    private final long sequenceId;
    private final BigDecimal price;

    public long getSequenceId() {
        return sequenceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderKey orderKey = (OrderKey) o;
        return sequenceId == orderKey.sequenceId && Objects.equals(price, orderKey.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceId, price);
    }

    @SuppressWarnings("all")
    public OrderKey(final long sequenceId, final BigDecimal price) {
        this.sequenceId = sequenceId;
        this.price = price;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "OrderKey(sequenceId=" + this.getSequenceId() + ", price=" + this.getPrice() + ")";
    }
}
