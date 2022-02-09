package com.snowalker.shield.match;

import com.google.common.collect.Lists;
import com.snowalker.shield.match.core.Direction;
import com.snowalker.shield.match.core.MatchOrder;
import com.snowalker.shield.match.core.MatchResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wenliang.wu@okg.com
 * @ClassName: MatcherEngineTest
 * @description: TODO
 * @Since 2022/1/21 12:13 上午
 */
public class MatcherEngineTest {

    static long sequenceId = 0;

    public static void main(String[] args) {

        List<String> inputs = Lists.newArrayList(
                "buy  2082.34 1",
                "sell 2087.6  2",
                "buy  2087.8  1",
                "buy  2085.01 5",
                "sell 2088.02 3",
                "sell 2087.60 6",
                "buy  2081.11 7",
                "buy  2086.0  3",
                "buy  2088.33 1",
                "sell 2086.54 2",
                "sell 2086.55 5",
                "buy  2086.55 3"
        );

        MatcherEngine engine = MatcherEngine.builder().build();

        for (String input : inputs) {
            MatchOrder order = createOrder(input);
            System.out.println("\n================================================================================\n");
            System.out.println("process order: " + order);
            MatchResult result = engine.matchProcessor(order);
            System.out.println(engine);
            System.out.println(result);
        }
    }

    static MatchOrder createOrder(String input) {
        sequenceId++;
        String[] ss = input.split("\\s+");
        Direction direction = Direction.valueOf(ss[0].toUpperCase());
        BigDecimal price = new BigDecimal(ss[1]);
        BigDecimal amount = new BigDecimal(ss[2]);
        return new MatchOrder(sequenceId, direction, price, amount);
    }
}
