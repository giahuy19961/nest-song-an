/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dao;

import com.mygroup.nestsonganver2.entity.UserEntity;
import java.util.List;

/**
 *
 * @author huy
 */
public interface IUserDAO extends IDao<UserEntity> {

    // Create User
    
    public int createNewUser(UserEntity user);

    // ----------------------------------------------------------------------
    
    // Find User
    
    public UserEntity findUser(String username, String password);
    
    public UserEntity findUser(String username);

    public UserEntity findUser(int id);
    
    public List<UserEntity> findAll();

    // ----------------------------------------------------------------------
    
    // Update User
       
    public int updateUser(UserEntity user);

    public int updateUserStatus(int id, int status);

    public int updateUserPassword(int id, String password); 
    
    // ----------------------------------------------------------------------
 

   
}
