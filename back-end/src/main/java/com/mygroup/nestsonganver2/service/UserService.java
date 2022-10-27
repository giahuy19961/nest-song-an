/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.service;

import com.mygroup.nestsonganver2.dao.impl.UserDAO;
import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.entity.UserEntity;
import com.mygroup.nestsonganver2.converter.UserConverter;
import com.mygroup.nestsonganver2.dao.impl.RoleDAO;
import com.mygroup.nestsonganver2.entity.RoleEntity;
import com.mygroup.nestsonganver2.utils.Utils;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class UserService {
    
    private  RoleDAO roleDAO = RoleDAO.getRoleDAO();
    
    private static UserDAO userDAO = UserDAO.getInstance();
    
    private static UserConverter converter = UserConverter.getInstance();

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
    // Create User 

    public String insertUser(UserDTO user) throws NoSuchAlgorithmException {
        if (checkUserName(user.getUsername()) == 1 ) {
            return null;
        }
        user.setPassword(Utils.hashPassWordMd5(user.getPassword()));
        int id = userDAO.createNewUser(converter.convertDTOtoEntity(user));
        return converter.ConvertEntitytoToken(userDAO.findUser(id));
    }

    // ----------------------------------------------------------------------
    // Find User
    public String checkLogin(UserDTO user) throws NoSuchAlgorithmException {
        UserEntity userEntity = userDAO.findUser(user.getUsername(), Utils.hashPassWordMd5(user.getPassword()));
        if (userEntity != null && userEntity.getId() != 0) {
            return converter.ConvertEntitytoToken(userEntity);
        }
        return null;
    }
    
    public int checkUserName(String username){
        UserEntity entity = userDAO.findUser(username);
        if(entity != null && entity.getId() != 0) return 1;
        return 0;
    }
    
    public UserDTO getUserById(int userId, int tokenId, String tokenRole) {
        if (tokenRole.equalsIgnoreCase("admin")
            || tokenRole.equalsIgnoreCase("employee")
            || tokenId == userId)
            return getUserById(userId);
        return null;
    }
    

    private UserDTO getUserById(int userId) {
        UserEntity userEntity = userDAO.findUser(userId);
        if (userEntity != null && userEntity.getId() != 0) {
            return converter.convertEntitytoDTO(userEntity);
        }
        return new UserDTO();
    }
    
    public List<UserDTO> findAllUsers(String tokenRole){
        if(tokenRole.equalsIgnoreCase("admin")) return findAllUsers();
        return null;
    }

    private List<UserDTO> findAllUsers() {
        List<UserDTO> list;
        List<UserEntity> entityList = userDAO.findAll();
        list = converter.convertEntitytoDTO(entityList);
        return list;
    }

    // ----------------------------------------------------------------------
    // Update User
    
    private int updateUser(UserEntity user, UserEntity oldUser){
        if(!user.getFullname().isEmpty()) user.setFullname(oldUser.getFullname());
        if(!user.getAddress().isEmpty()) user.setAddress(oldUser.getAddress());
        if(!user.getDateOfBirth().toString().isEmpty()) user.setDateOfBirth(oldUser.getDateOfBirth());
        if(!user.getPhoneNumber().isEmpty()) user.setPhoneNumber(oldUser.getPhoneNumber());
        return userDAO.updateUser(user);
    }
    
    public int updateUser(UserDTO user) {
        UserEntity oldUser = userDAO.findUser(user.getId());
        if(oldUser == null || oldUser.getId() == 0) return 0;
        else return updateUser(converter.convertDTOtoEntity(user), oldUser);
    }
    
    public int updateUserStatus(int id, int status) {
        return userDAO.updateUserStatus(id, status);
    }
    
    public int updateUserPassword(int id, String password) throws NoSuchAlgorithmException {
        return userDAO.updateUserPassword(id, Utils.hashPassWordMd5(password));
    }

    // ----------------------------------------------------------------------
}
