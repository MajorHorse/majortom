package com.majortom.exercise.mytest.java8test.chapter8.observer;

/**
 * <p>
 *
 * </P>
 *
 * @author Major Tom
 * @since 2022/2/28 17:00
 */
public class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if (null != tweet && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
