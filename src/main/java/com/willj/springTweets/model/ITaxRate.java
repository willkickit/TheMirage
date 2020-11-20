package com.willj.springTweets.model;

public interface ITaxRate {
    default double getTaxRate(){
        return 0.04;
    }
}
