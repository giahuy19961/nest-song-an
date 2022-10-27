/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.constant.NewsSQL;
import com.mygroup.nestsonganver2.dao.INewsDAO;
import com.mygroup.nestsonganver2.entity.NewsEntity;
import com.mygroup.nestsonganver2.mapper.NewsMapper;
import com.mygroup.nestsonganver2.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dd220
 */
public class NewsDAO extends AbstractDAO<NewsEntity> implements INewsDAO{
    
    public static NewsDAO instance;
    
    public static NewsDAO getNewsDAO() {
        if (instance == null)
            instance = new NewsDAO();
        return instance;
    }
    
    private NewsDAO(){}
   
    //GET
    @Override
    public List<NewsEntity> getAllNews() {
        List<NewsEntity> list = new ArrayList<>();
        try { 
            list = query(NewsSQL.findAll, new NewsMapper());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    @Override
    public NewsEntity getNewsById(int id) {
        List<NewsEntity> list;
        try {
            list = query(NewsSQL.findNewsById, new NewsMapper(), id);
            if (!list.isEmpty()) return list.get(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    //Add 
    @Override
    public int addNews(NewsEntity news) {
        int id = insert(NewsSQL.add, news.getDescription(), news.getShortDescription(), news.getTitle(), news.getEmpId());
        return id;
    }
    
    //update news  by id
    @Override
    public int updateNewsById(NewsEntity news) {
        int result = update(NewsSQL.update, news.getDescription(), news.getShortDescription(), news.getTitle(), news.getEmpId(), news.getId());
        return result;
    }

    //Delete
    @Override
    public int deleteNews(int id) {
        int check = 0;
        Connection cnn = null;
        try {
            cnn = Utils.makeConnection();
            if (cnn != null) {
                String sql = "DELETE News WHERE id = ?";
                PreparedStatement ps = cnn.prepareStatement(sql);
                ps.setInt(1, id);
                check = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return check;
    }
    
}
