package com.snowalker.shield.match.core;

import java.math.BigDecimal;

/**
 * 撮合记录
 * @author oker
 */
public class MatchRecord {
    /**
     * 成交价格
     */
    public BigDecimal price;
    /**
     * 成交数量
     */
    public BigDecimal amount;
    /**
     * 吃单
     */
    public MatchOrder takerOrder;
    /**
     * 挂单
     */
    public MatchOrder makerOrder;

    @Override
    public String toString() {
        return String.format("价格:[%.2f, 数量:%.2f]", this.price, this.amount);
    }


    @SuppressWarnings("all")
    public static class MatchRecordBuilder {
        @SuppressWarnings("all")
        private BigDecimal price;
        @SuppressWarnings("all")
        private BigDecimal amount;
        @SuppressWarnings("all")
        private MatchOrder takerOrder;
        @SuppressWarnings("all")
        private MatchOrder makerOrder;

        @SuppressWarnings("all")
        MatchRecordBuilder() {
        }

        /**
         * 成交价格
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public MatchRecord.MatchRecordBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        /**
         * 成交数量
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public MatchRecord.MatchRecordBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * 吃单
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public MatchRecord.MatchRecordBuilder takerOrder(final MatchOrder takerOrder) {
            this.takerOrder = takerOrder;
            return this;
        }

        /**
         * 挂单
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public MatchRecord.MatchRecordBuilder makerOrder(final MatchOrder makerOrder) {
            this.makerOrder = makerOrder;
            return this;
        }

        @SuppressWarnings("all")
        public MatchRecord build() {
            return new MatchRecord(this.price, this.amount, this.takerOrder, this.makerOrder);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "MatchRecord.MatchRecordBuilder(price=" + this.price + ", amount=" + this.amount + ", takerOrder=" + this.takerOrder + ", makerOrder=" + this.makerOrder + ")";
        }
    }

    @SuppressWarnings("all")
    public static MatchRecord.MatchRecordBuilder builder() {
        return new MatchRecord.MatchRecordBuilder();
    }

    /**
     * 成交价格
     */
    @SuppressWarnings("all")
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 成交数量
     */
    @SuppressWarnings("all")
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 吃单
     */
    @SuppressWarnings("all")
    public MatchOrder getTakerOrder() {
        return this.takerOrder;
    }

    /**
     * 挂单
     */
    @SuppressWarnings("all")
    public MatchOrder getMakerOrder() {
        return this.makerOrder;
    }

    /**
     * 成交价格
     */
    @SuppressWarnings("all")
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    /**
     * 成交数量
     */
    @SuppressWarnings("all")
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 吃单
     */
    @SuppressWarnings("all")
    public void setTakerOrder(final MatchOrder takerOrder) {
        this.takerOrder = takerOrder;
    }

    /**
     * 挂单
     */
    @SuppressWarnings("all")
    public void setMakerOrder(final MatchOrder makerOrder) {
        this.makerOrder = makerOrder;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MatchRecord)) return false;
        final MatchRecord other = (MatchRecord) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$takerOrder = this.getTakerOrder();
        final Object other$takerOrder = other.getTakerOrder();
        if (this$takerOrder == null ? other$takerOrder != null : !this$takerOrder.equals(other$takerOrder)) return false;
        final Object this$makerOrder = this.getMakerOrder();
        final Object other$makerOrder = other.getMakerOrder();
        if (this$makerOrder == null ? other$makerOrder != null : !this$makerOrder.equals(other$makerOrder)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof MatchRecord;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $takerOrder = this.getTakerOrder();
        result = result * PRIME + ($takerOrder == null ? 43 : $takerOrder.hashCode());
        final Object $makerOrder = this.getMakerOrder();
        result = result * PRIME + ($makerOrder == null ? 43 : $makerOrder.hashCode());
        return result;
    }

    @SuppressWarnings("all")
    public MatchRecord(final BigDecimal price, final BigDecimal amount, final MatchOrder takerOrder, final MatchOrder makerOrder) {
        this.price = price;
        this.amount = amount;
        this.takerOrder = takerOrder;
        this.makerOrder = makerOrder;
    }
}
