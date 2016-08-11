package com.mvc.service;

import com.mvc.dao.MUserdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/2/11.
 */
@Service
public class MUserService {


    @Autowired
    private MUserdao userdao;

  public void add(String uname)
  {
      userdao.add(uname);
  }


}
