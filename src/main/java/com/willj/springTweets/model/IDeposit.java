package com.willj.springTweets.model;

public interface IDeposit {
    default double getBaseDeposit(){
        return 50.00;
    }

    default double twoRooms(){
        return 1.5;
    }

    default double threeRooms(){
        return 2.0;
    }
}
