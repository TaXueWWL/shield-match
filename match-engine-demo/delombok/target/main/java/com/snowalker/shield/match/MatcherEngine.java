package com.snowalker.shield.match;

import com.snowalker.shield.match.core.Direction;
import com.snowalker.shield.match.core.MatchOrder;
import com.snowalker.shield.match.core.MatchResult;
import com.snowalker.shield.match.core.OrderBook;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: MatcherEngine
 * @description: 撮合引擎
 *
 * 对于撮合交易来说，如果新订单是一个买单，则首先尝试在卖盘中匹配价格合适的卖单，如果匹配成功则成交。
 * 一个大的买单可能会匹配多个较小的卖单。当买单被完全匹配后，说明此买单已完全成交，处理结束，否则，如果存在未成交的买单，则将其放入买盘。
 * 处理卖单的逻辑是类似的。
 *
 * @Since 2022/1/20 11:13 下午
 */
public class MatcherEngine {
    /**
     * 买盘
     */
    private final OrderBook buyBook = OrderBook.newInstance(Direction.BUY);
    /**
     * 卖盘
     */
    private final OrderBook sellBook = OrderBook.newInstance(Direction.SELL);
    /**
     * 最新成交价
     */
    private BigDecimal marketPrice = BigDecimal.ZERO;

    public MatchResult matchProcessor(MatchOrder order) {
        switch (order.getDirection()) {
        case SELL: 
            // 卖单与buyBook匹配，最后放入sellBook
            return process(order, this.buyBook, this.sellBook);
        case BUY: 
            // 买单与sellBook匹配，最后放入buyBook
            return process(order, this.sellBook, this.buyBook);
        default: 
            throw new IllegalArgumentException("Wrong Direction!");
        }
    }

    /**
     * 把已经挂在买卖盘的订单称为挂单（Maker），当前正在处理的订单称为吃单（Taker），一个Taker订单如果未完全成交则转为Maker挂在买卖盘
     * 根据价格匹配，直到成交双方有一方完全成交或成交条件不满足时结束处理
     * @param takerOrder  输入订单
     * @param makerBook   尝试匹配成交的OrderBook
     * @param anotherBook 未能完全成交后挂单的OrderBook
     * @return 成交结果
     */
    MatchResult process(MatchOrder takerOrder, OrderBook makerBook, OrderBook anotherBook) {
        MatchResult matchResult = MatchResult.builder().build();
        while (true) {
            MatchOrder makerOrder = makerBook.getFirst();
            if (Objects.isNull(makerOrder)) {
                // 对手盘不存在订单
                break;
            }
            if (takerOrder.getDirection() == Direction.BUY && takerOrder.getPrice().compareTo(makerOrder.getPrice()) < 0) {
                // 当前为买单 且当前买单价格小于对手方卖单价格，无法成交（当前买方吃单比对手卖盘卖一价低）
                break;
            }
            if (takerOrder.getDirection() == Direction.SELL && takerOrder.getPrice().compareTo(makerOrder.getPrice()) > 0) {
                // 当前为卖单 且当前卖单价格大于对手方买单价格，无法成交（当前卖方吃单比对手买盘买一价高）
                break;
            }
            // 以maker价格成交 最新成交价为当前挂单价格
            this.marketPrice = makerOrder.getPrice();
            // 成交数量为双方的较小值
            BigDecimal matchedAmount = takerOrder.getAmount().min(makerOrder.getAmount());
            // 成交记录
            matchResult.add(makerOrder.getPrice(), matchedAmount, makerOrder);
            // 更新成交之后的订单数量 :taker挂单数量-已成交量  maker挂单数量-已成交量
            takerOrder.setAmount(takerOrder.getAmount().subtract(matchedAmount));
            makerOrder.setAmount(makerOrder.getAmount().subtract(matchedAmount));
            // 对手盘完全成交之后 从对应的买卖深度删除
            if (makerOrder.getAmount().signum() == 0) {
                makerBook.remove(makerOrder);
            }
            // Taker订单完全成交之后 退出循环(相当于)
            if (takerOrder.getAmount().signum() == 0) {
                break;
            }
        }
        // Taker订单未完全成交时 放入深度
        if (takerOrder.getAmount().signum() > 0) {
            anotherBook.add(takerOrder);
        }
        return matchResult;
    }

    /**
     * 撤单
     * @param order
     * @return
     */
    public boolean cancel(MatchOrder order) {
        OrderBook orderBook = order.getDirection() == Direction.BUY ? this.buyBook : this.sellBook;
        return orderBook.remove(order);
    }

    @Override
    public String toString() {
        String line = "\n-------------------------\n";
        return "\n-----------卖盘------------\n价格     数量  方向  序列\n" + this.sellBook + line + "最新成交价:" + this.marketPrice + "\n-----------买盘------------\n价格     数量  方向  序列\n" + this.buyBook + line;
    }

    @SuppressWarnings("all")
    MatcherEngine(final BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }


    @SuppressWarnings("all")
    public static class MatcherEngineBuilder {
        @SuppressWarnings("all")
        private BigDecimal marketPrice;

        @SuppressWarnings("all")
        MatcherEngineBuilder() {
        }

        /**
         * 最新成交价
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public MatcherEngine.MatcherEngineBuilder marketPrice(final BigDecimal marketPrice) {
            this.marketPrice = marketPrice;
            return this;
        }

        @SuppressWarnings("all")
        public MatcherEngine build() {
            return new MatcherEngine(this.marketPrice);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "MatcherEngine.MatcherEngineBuilder(marketPrice=" + this.marketPrice + ")";
        }
    }

    @SuppressWarnings("all")
    public static MatcherEngine.MatcherEngineBuilder builder() {
        return new MatcherEngine.MatcherEngineBuilder();
    }
}
