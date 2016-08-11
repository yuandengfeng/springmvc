package com.mvc.dao;

import com.mvc.pro.MUser;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/2/11.
 */
@Component
public class MUserdao {

    public void add(String uname)
    {
        System.out.println("MUserDao.add(uname)="+uname);
    }
}
