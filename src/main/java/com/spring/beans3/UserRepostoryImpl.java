package com.spring.beans3;

import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/2/15.
 */
@Repository("userRepostory")
public class UserRepostoryImpl implements UserRepostory {


    @Override
    public void save() {
        System.out.println("UserRepostory  save ...");
    }
}
