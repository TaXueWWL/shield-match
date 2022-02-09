package com.snowalker.shield.match.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 撮合记录
 * @author oker
 */
@Data
@Builder
@AllArgsConstructor
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

}
