
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.converter;

import com.mygroup.nestsonganver2.dao.impl.RoleDAO;
import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.entity.RoleEntity;
import com.mygroup.nestsonganver2.entity.UserEntity;
import com.mygroup.nestsonganver2.utils.Utils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 */
public class UserConverter {

    private RoleDAO roleDao = RoleDAO.getRoleDAO();
    
    private static UserConverter userConverter;
    
    public static UserConverter getInstance(){
        if(userConverter == null){
            userConverter = new UserConverter(); 
        }
        return userConverter;
    }

    public UserDTO convertEntitytoDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setFullname(entity.getFullname());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setPassword(entity.getPassword());
        dto.setRole(roleDao.getRoleById(entity.getRoleId()));
        return dto;
    }

    public List<UserDTO> convertEntitytoDTO(List<UserEntity> entityList) {
        List<UserDTO> dtoList = new ArrayList<>();      
        entityList.forEach(entity -> {         
            dtoList.add(convertEntitytoDTO(entity));
        });
        return dtoList;
    }

    public UserEntity convertDTOtoEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setFullname(dto.getFullname());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setPassword(dto.getPassword());
        entity.setRoleId((dto.getRole()==null)?2:dto.getRole().getId());
        return entity;
    }

    public UserDTO convertTokentoDTO(String token) {
        UserDTO dto = new UserDTO();
        String[] propString;
        if (token != null && !token.isEmpty()) {

            String[] tokenArray = token.split("[|]");
            for (String prop : tokenArray) {
                propString = prop.split("=");
                switch (propString[0]) {
                    case "id":
                        dto.setId(Integer.parseInt(propString[1]));
                        break;
                    case "fullname":
                        dto.setFullname(propString[1]);
                        break;
                    case "role":
                        dto.setRole(new RoleEntity(0, propString[1].trim()));
                        break;
                    case "expired":
                        LocalDateTime expr = LocalDateTime.parse(propString[1], Utils.dtf);
                        LocalDateTime now = LocalDateTime.now();
                        if (now.isAfter(expr)) {
                            return null;
                        }
                        break;
                }
            }
        }
        return dto;
    }
    
    public String ConvertDTOtoToken(UserDTO dto) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        builder.append("expired=").append(Utils.dtf.format(now)).append("|");
        builder.append("id=").append(dto.getId()).append("|");
        builder.append("fullname=").append(dto.getFullname()).append("|");
        builder.append("role=").append(dto.getRole().getName());
        return builder.toString();
    }
    
    public String ConvertEntitytoToken(UserEntity entity) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        builder.append("expired=").append(Utils.dtf.format(now)).append("|");
        builder.append("id=").append(entity.getId()).append("|");
        builder.append("fullname=").append(entity.getFullname()).append("|");
        builder.append("role=").append((roleDao.getRoleById(entity.getRoleId())).getName());
        return builder.toString();
    }
    
    
    
}
