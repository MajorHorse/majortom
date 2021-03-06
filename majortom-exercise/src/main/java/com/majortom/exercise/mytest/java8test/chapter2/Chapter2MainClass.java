package com.majortom.exercise.mytest.java8test.chapter2;

import com.majortom.exercise.mytest.java8test.chapter2.entity.Trader;
import com.majortom.exercise.mytest.java8test.chapter2.entity.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 *
 * </P>
 *
 * @author Major Tom
 * @since 2022/2/8 18:08
 */
public class Chapter2MainClass {
    public static void main(String[] args) {
        List<Integer> numberArr = Arrays.asList(1, 2, 4, 5, 6);
        List<Integer> result = numberArr.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(result);

        List<Integer> numberArr1 = Arrays.asList(1, 2, 4);
        List<Integer> numberArr2 = Arrays.asList(5, 6);
        List<int[]> collect = numberArr1.stream()
                .flatMap(i -> numberArr2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        collect.forEach(x -> System.out.println(Arrays.toString(x)));

        Integer sumResult = numberArr.stream().reduce(0, Integer::sum);
        System.out.println("sumResult is: " + sumResult);
        Optional<Integer> maxResult = numberArr.stream().reduce(Integer::max);
        System.out.println("maxResult is: " + maxResult);
        System.out.println("=====================================================================================");
        //测试数据
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //Q1：找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> q1Result = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("Q1结果是： " + q1Result);
        //Q2：交易员都在哪些不同的城市工作过。
        List<String> q2Result = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Q2结果是： " + q2Result);
        //Q3：查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> q3Result = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("Q3结果是： " + q3Result);
        //Q4：返回所有交易员的姓名字符串，按字母顺序排序。
        String q4Result = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + b);
        System.out.println("Q4结果是： " + q4Result);
        //Q5：有没有交易员是在米兰工作的？
        boolean q5Result = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println("Q5结果是： " + q5Result);
        //Q6：打印生活在剑桥的交易员的所有交易额。
        int q6Result = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println("Q6结果是： " + q6Result);
        //Q7：所有交易中，最高的交易额是多少？
        Integer q7Result = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println("Q7结果是： " + q7Result);
        //Q8：找到交易额最小的交易
        //方法1
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(
                        x -> transactions.stream()
                                .filter(transaction -> x == transaction.getValue())
                                .collect(Collectors.toList())
                                .forEach(System.out::println)
                );
        //方法2:
        Optional<Transaction> reduce = transactions.stream()
                .reduce((a, b) -> a.getValue() > b.getValue() ? b : a);
        //方法3
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }
}
