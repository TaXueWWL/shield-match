package com.snowalker.shield.match.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: OrderKey
 * @description: TODO
 * @Since 2022/1/20 10:04 下午
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
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

}
