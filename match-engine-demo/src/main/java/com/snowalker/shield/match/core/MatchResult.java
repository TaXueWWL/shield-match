package com.snowalker.shield.match.core;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: MatchResult
 * @description: 撮合结果
 * @Since 2022/1/20 11:29 下午
 */
@Builder
@Getter
public class MatchResult {

    /**
     * taker订单
     */
    private MatchOrder takerOrder;

    private final List<MatchRecord> matchRecords = Lists.newArrayList();

    /**
     * 添加成交记录
     * 含义：吃单方 takerOrder 以价格 price 吃掉 挂单方 makerOrder 多少amount的单子 （成交量为matchedAmount）
     * @param price
     * @param matchedAmount
     * @param makerOrder
     */
    public void add(BigDecimal price, BigDecimal matchedAmount, MatchOrder makerOrder) {
        this.matchRecords.add(MatchRecord.builder().price(price).makerOrder(makerOrder).takerOrder(this.takerOrder).amount(matchedAmount).build());
    }

    @Override
    public String toString() {
        if (matchRecords.isEmpty()) {
            return "no matched.";
        }
        return matchRecords.size() + " matched: " + String.join(", ", this.matchRecords.stream().map(MatchRecord::toString).toArray(String[]::new));
    }
}
