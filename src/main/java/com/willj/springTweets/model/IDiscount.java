package com.willj.springTweets.model;

public interface IDiscount {
    default double discountRate(){
        return 0.15;
    }
}
