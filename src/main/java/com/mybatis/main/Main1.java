package com.mybatis.main;

import com.mybatis.mapper.userMapper;
import com.mybatis.mybatis.Order;
import com.mybatis.mybatis.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/2/5.
 */
public class Main1 {

    public static void main(String[] args) throws IOException {
        //mybatis的配置文件
//        String path =System.getProperty("user.dir");
//        System.out.println(path);
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Main1.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();

        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
      /*查询
        String statement = "com.mybatis.mapper.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user.toString());

        /*插入
        String statementIN ="com.mybatis.mapper.userMapper.insertUser";
        User user2 = new User();
        user2.setAddress("北京");
        user2.setAge(20);
        user2.setName("fengfeng");
        user2.setId(6);
         int count = session.insert(statementIN,user2);
        session.commit();
        System.out.println(count);
      */
//      userMapper  mapper =session.getMapper(userMapper.class);
//      mapper.insertUser(user2);
        /*删除
        String statement = "com.mybatis.mapper.userMapper.deleteUser";
        int count = session.delete(statement,7);
        session.commit();
        System.out.println(count);
         */
        /*更新用户
        String statement = "com.mybatis.userMapper.updateUser";
        User user2 = new User();
        user2.setAddress("bb");
        user2.setAge(20);
        user2.setName("圆圆");
        user2.setId(6);
        User user3 = new User();
        user3.setAddress("cc");
        user3.setAge(20);
        user3.setName("哈哈");
        user3.setId(7);
        int count = session.update(statement,user2);
        int count2 = session.update(statement,user3);
        session.commit();
        System.out.println(count);
        System.out.println(count2);
         */
        /*查询所有
//        String statement = "com.mybatis.mapper.userMapper.getAll";
      String statement = "com.mybatis.mapper.orderMapper.getOrder";
        List<User> list = session.selectList("com.mybatis.mapper.userMapper.getAll");

      for(int i=0;i<list.size();i++)
      {
        System.out.println(list.get(i));
      }
       session.close();
         */

//      String statement = "com.mybatis.mapper.userMapper.getUser";//映射sql的标识字符串
//      //执行查询返回一个唯一user对象的sql
//      User user = session.selectOne(statement, 1);
//      System.out.println(user.toString());

//
      Order order = new Order();
//      order.setOrder_id(22);
      order.setOrder_no("王小五");
      order.setOrder_price(22);
      String statement1 = "com.mybatis.mapper.orderMapper.insertOrder";

      int cc = session.insert(statement1,order);

      String statement2 = "com.mybatis.mapper.orderMapper.getOrder";//映射sql的标识字符串
      //执行查询返回一个唯一order对象的sql
      Order orders = session.selectOne(statement2,1);
      System.out.println(orders.toString());

      List<Order> list = session.selectList("com.mybatis.mapper.orderMapper.getAll");

      for(int i=0;i<list.size();i++)
      {
        System.out.println(list.get(i));
      }


    }


}
