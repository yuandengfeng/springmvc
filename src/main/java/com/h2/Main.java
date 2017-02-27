package com.h2;

import com.h2.database.DBConn;
import com.h2.entities.Page;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import net.sf.json.JSONArray;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuandengfeng on 2017/2/21.
 */
public class Main {


    public static void main(String[] args){
                try {
            ConnectionSource connectionSource = DBConn.getConnection();

            TableUtils.createTableIfNotExists(connectionSource, Page.class);

            Dao<Page,String> pageDao = DaoManager.createDao(connectionSource,Page.class);

            Page page = new Page();
            page.setTitle("dfsdaf");
            page.setId(UUID.randomUUID().toString());
            page.setContent("dfsadfadsf");
            page.setDate(new Date().toString());
            page.setUrl("http://www.baidu.com");

            pageDao.createIfNotExists(page);
//                    System.out.println(pageDao.create(page));


            QueryBuilder<Page,String> queryBuilder = pageDao.queryBuilder();
            queryBuilder.where().eq("title","dfsdaf");

            PreparedQuery<Page> preparedQuery = queryBuilder.prepare();

            List<Page> pages = pageDao.query(preparedQuery);

            System.out.println(JSONArray.fromObject(pages).toString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
