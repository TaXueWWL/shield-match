package com.snowalker.shield.match.core;

import lombok.*;
import java.math.BigDecimal;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: MatchOrder
 * @description: 撮合订单
 * @Since 2022/1/20 9:49 下午
 */
public class MatchOrder {
    /**
     * 序号
     */
    private final long sequenceId;
    /**
     * 订单方向
     */
    private final Direction direction;
    /**
     * 价格
     */
    private final BigDecimal price;
    /**
     * 数量
     */
    private BigDecimal amount;

    @Override
    public String toString() {
        return String.format("%.2f %.2f %s seq=%s", this.price, this.amount, this.direction, this.sequenceId);
    }

    @SuppressWarnings("all")
    public MatchOrder(final long sequenceId, final Direction direction, final BigDecimal price, final BigDecimal amount) {
        this.sequenceId = sequenceId;
        this.direction = direction;
        this.price = price;
        this.amount = amount;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MatchOrder)) return false;
        final MatchOrder other = (MatchOrder) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getSequenceId() != other.getSequenceId()) return false;
        final Object this$direction = this.getDirection();
        final Object other$direction = other.getDirection();
        if (this$direction == null ? other$direction != null : !this$direction.equals(other$direction)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof MatchOrder;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $sequenceId = this.getSequenceId();
        result = result * PRIME + (int) ($sequenceId >>> 32 ^ $sequenceId);
        final Object $direction = this.getDirection();
        result = result * PRIME + ($direction == null ? 43 : $direction.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        return result;
    }

    /**
     * 序号
     */
    @SuppressWarnings("all")
    public long getSequenceId() {
        return this.sequenceId;
    }

    /**
     * 订单方向
     */
    @SuppressWarnings("all")
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * 价格
     */
    @SuppressWarnings("all")
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 数量
     */
    @SuppressWarnings("all")
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 数量
     */
    @SuppressWarnings("all")
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
