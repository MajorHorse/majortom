package com.majortom.exercise.mytest.java8test.chapter1.predicates.impl;

import com.majortom.exercise.mytest.java8test.chapter1.entity.Apple;
import com.majortom.exercise.mytest.java8test.chapter1.predicates.ApplePredicate;

/**
 * <p>
 *
 * </P>
 *
 * @author Major Tom
 * @since 2022/1/14 10:32
 */
public class AppleGreenPredicate implements ApplePredicate {

    @Override
    public boolean predicate(Apple apple) {
        return apple.getColor().equals("green");
    }
}
