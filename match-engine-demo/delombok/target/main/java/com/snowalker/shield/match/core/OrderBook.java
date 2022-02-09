package com.snowalker.shield.match.core;

import lombok.*;
import java.util.*;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: OrderBook
 * @description:
 * 订单簿 OrderBook 的表示。
 * ⼀个直观的想法是使⽤ List<Order> ，并对订单进⾏ 排序。
 * 但是，在证券交易中，使⽤ List 会导致两个致命问题：
 *      1. 插⼊新的订单时，必须从头扫描 List<Order> ，以便在合适的地⽅插⼊ Order ，平均耗时 O(N)；
 *      2. 取消订单时，也必须从头扫描 List<Order> ，平均耗时O(N)。
 *      更好的⽅法是使⽤红⿊树，它是⼀种⾃平衡的⼆叉排序树，插⼊和删除的效率都是O(logN)，对应 的Java类是 TreeMap 。
 *      所以我们定义 OrderBook 的结构就是⼀个 TreeMap<OrderKey, Order> ，它的排序根据 OrderKey 决定。
 * @Since 2022/1/20 10:07 下午
 */
public class OrderBook {
    /**
     * 买卖方向
     */
    private final Direction direction;
    /**
     * 买卖方向深度排序树
     */
    private final TreeMap<OrderKey, MatchOrder> book;

    public static OrderBook newInstance(Direction direction) {
        OrderBook orderBook = OrderBook.builder().direction(direction).book(new TreeMap<>(direction == Direction.BUY ? SORT_BUY : SORT_SELL)).build();
        return orderBook;
    }

    // 价格低优先
    // 时间早在前
    // 数量多在前
    /**
     * 买单排序
     * 深度：价格高在前
     *      同价时间早在前  根据时间定序即可，即便是数量相同，也有先后的seq
     */
    private static final Comparator<OrderKey> SORT_SELL = Comparator.comparing(OrderKey::getPrice).thenComparingLong(OrderKey::getSequenceId);
    private static final Comparator<OrderKey> SORT_BUY = (o1, o2) -> {
        // 价格高优先
        int cmp = o2.getPrice().compareTo(o1.getPrice());
        // 时间早优先
        if (cmp == 0) {
            cmp = Long.compare(o1.getSequenceId(), o2.getSequenceId());
        }
        return cmp;
    };

    /**
     * 查找首元素
     * @return
     */
    public MatchOrder getFirst() {
        return this.book.isEmpty() ? null : this.book.firstEntry().getValue();
    }

    /**
     * 移除元素
     * @param order
     * @return
     */
    public boolean remove(MatchOrder order) {
        return this.book.remove(new OrderKey(order.getSequenceId(), order.getPrice())) != null;
    }

    public boolean add(MatchOrder order) {
        return this.book.put(new OrderKey(order.getSequenceId(), order.getPrice()), order) == null;
    }

    @Override
    public String toString() {
        if (this.book.isEmpty()) {
            return "(empty)";
        }
        List<String> orders = new ArrayList<>(10);
        for (Map.Entry<OrderKey, MatchOrder> entry : this.book.entrySet()) {
            orders.add(entry.getValue().toString());
        }
        if (direction == Direction.SELL) {
            Collections.reverse(orders);
        }
        return String.join("\n", orders);
    }


    @SuppressWarnings("all")
    public static class OrderBookBuilder {
        @SuppressWarnings("all")
        private Direction direction;
        @SuppressWarnings("all")
        private TreeMap<OrderKey, MatchOrder> book;

        @SuppressWarnings("all")
        OrderBookBuilder() {
        }

        /**
         * 买卖方向
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public OrderBook.OrderBookBuilder direction(final Direction direction) {
            this.direction = direction;
            return this;
        }

        /**
         * 买卖方向深度排序树
         * @return {@code this}.
         */
        @SuppressWarnings("all")
        public OrderBook.OrderBookBuilder book(final TreeMap<OrderKey, MatchOrder> book) {
            this.book = book;
            return this;
        }

        @SuppressWarnings("all")
        public OrderBook build() {
            return new OrderBook(this.direction, this.book);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "OrderBook.OrderBookBuilder(direction=" + this.direction + ", book=" + this.book + ")";
        }
    }

    @SuppressWarnings("all")
    public static OrderBook.OrderBookBuilder builder() {
        return new OrderBook.OrderBookBuilder();
    }

    @SuppressWarnings("all")
    private OrderBook(final Direction direction, final TreeMap<OrderKey, MatchOrder> book) {
        this.direction = direction;
        this.book = book;
    }

    /**
     * 买卖方向
     */
    @SuppressWarnings("all")
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * 买卖方向深度排序树
     */
    @SuppressWarnings("all")
    public TreeMap<OrderKey, MatchOrder> getBook() {
        return this.book;
    }
}
