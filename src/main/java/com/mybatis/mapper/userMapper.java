package com.mybatis.mapper;

import com.mybatis.mybatis.User;


import java.util.List;

/**
 * Created by Administrator on 2016/2/6.
 */
public interface userMapper {

//    @Insert(" insert into USERS  values (#{id},#{name},#{age},#{address})")
    public int insertUser(User user);

//    @Select("select * from USERS where id=#{id}")
    public User getUser(int id);

//    @Delete(" DELETE  from USERS where id = #{id}")
    public int deleteUser(int id);

//    @Select("SELECT * from USERS ")
    public List<User> getAll();

//    @Update("UPDATE USERS SET name=#{name},age=#{age},address=#{address} where id=#{id}")
    public int updateUser();


}




