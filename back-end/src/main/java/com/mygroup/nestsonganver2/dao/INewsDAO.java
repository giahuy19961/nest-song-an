    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.NewsEntity;
import java.util.List;

/**
 *
 * @author dd220
 */
public interface INewsDAO extends IDao<NewsEntity>{
    
    //Get all news
    public List<NewsEntity> getAllNews(); 
    
    //Get news by id
    public NewsEntity getNewsById (int id);
    
    //add news
    public int addNews(NewsEntity news);
    
    //update news 
    public int updateNewsById(NewsEntity news);
    
    //Delete news by id
    public int deleteNews(int id);
}
