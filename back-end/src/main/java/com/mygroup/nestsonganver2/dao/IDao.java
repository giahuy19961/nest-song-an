
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.mapper.RowMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public interface IDao<T> {

    <T> List<T> query(String sql, RowMapper<T> mapper, Object... parameters);
    
//    default <T> List<T> findAll(String sql, RowMapper<T> mapper, Object... parameters){
//        List<T> list = new ArrayList<T>();
//        return list; 
//    }
    
    int update(String sql, Object... parameters);

    Integer insert(String sql, Object... parameters);
    
    int queryCount(String sql, Object... parameters);
}
