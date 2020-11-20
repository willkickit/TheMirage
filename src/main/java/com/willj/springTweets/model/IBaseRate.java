package com.willj.springTweets.model;

public interface IBaseRate {

        default double getBaseRate(){
            return 60.50;
        }
}
