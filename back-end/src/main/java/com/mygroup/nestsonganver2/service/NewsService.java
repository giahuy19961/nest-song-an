/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.converter.NewsConverter;
import com.mygroup.nestsonganver2.dao.impl.NewsDAO;
import com.mygroup.nestsonganver2.dto.NewsDTO;
import com.mygroup.nestsonganver2.entity.NewsEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dd220
 */
public class NewsService {
    
    public static NewsService instance;
    public static NewsService getNewsService() {
        if (instance == null)
            instance = new NewsService();
        return instance;
    }
    
    private NewsService(){}
    
    private NewsDAO newsDAO = NewsDAO.getNewsDAO();
    
    
    //Get all News service
    public  List<NewsDTO> getAllNews() {
        List<NewsEntity> entityList = newsDAO.getAllNews();
        if (entityList.isEmpty()) return new ArrayList<NewsDTO>(); 
        NewsDTO dto = new NewsDTO();
        List<NewsDTO> dtoList = new ArrayList<>();
        for (NewsEntity entity : entityList) {
            dto = NewsConverter.convertEntityToDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    //Get news by ID 
    public NewsDTO getNewsById(int id) {
        NewsEntity entity = newsDAO.getNewsById(id);
        if (entity == null) return new NewsDTO();
        return NewsConverter.convertEntityToDTO(entity);
    }
    
    //Add news 
    public int addNews(NewsDTO dto) {
        if (dto.getDescription() == null)
            dto.setDescription("");
        if (dto.getShortDescription() == null)
            dto.setShortDescription("");
        if (dto.getTitle() == null)
            dto.setTitle("");
        if (dto.getEmpId() == 0)
            dto.setEmpId(1);
        int id = newsDAO.addNews(NewsConverter.convertDTOToEnity(dto));
        return id;
    }
    
    //update news by id
    public NewsDTO updateNews(int id, NewsDTO dto) {
        NewsEntity old = newsDAO.getNewsById(id);
        
        if (old == null) return null;
        
        if (dto == null) return NewsConverter.convertEntityToDTO(old);
        
        if (dto.getDescription() != null)
            old.setDescription(dto.getDescription());
        
        if(dto.getShortDescription() != null)
            old.setShortDescription(dto.getShortDescription());
        
        if (dto.getEmpId() != old.getEmpId() && dto.getEmpId() != 0)
            old.setEmpId(dto.getEmpId());
        
        newsDAO.updateNewsById(old);
        return NewsConverter.convertEntityToDTO(old);
    }
    
    //Delete news by Id
    // check = 0: is fail
    // check > 0: is deleted
    public int deleteNews(int id) {
        int check = 0;
        NewsEntity deleteNews = newsDAO.getNewsById(id);
        if (deleteNews == null) return check;
        
        check = newsDAO.deleteNews(id);
        return check;
    }
}
