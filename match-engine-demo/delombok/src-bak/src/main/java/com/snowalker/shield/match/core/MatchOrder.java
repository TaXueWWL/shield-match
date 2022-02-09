package com.snowalker.shield.match.core;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: MatchOrder
 * @description: 撮合订单
 * @Since 2022/1/20 9:49 下午
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
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
    @Setter
    private BigDecimal amount;

    @Override
    public String toString() {
        return String.format("%.2f %.2f %s seq=%s", this.price, this.amount, this.direction, this.sequenceId);
    }

}
