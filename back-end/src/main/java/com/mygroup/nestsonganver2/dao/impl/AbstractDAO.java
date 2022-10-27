/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.dao.impl;

import com.mygroup.nestsonganver2.dao.IDao;
import com.mygroup.nestsonganver2.mapper.RowMapper;
import com.mygroup.nestsonganver2.utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huy
 * @param <T>
 */
public class AbstractDAO<T> implements IDao<T> {

    @Override
    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Utils.makeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            connection.commit();
            connection.setAutoCommit(true);
            while (resultSet.next()) {
                results.add(mapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }
    }
    
    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Date) {
                    statement.setDate(index, (Date) parameter);    
                } else if (parameter instanceof Float) {
                    statement.setFloat(index, (Float) parameter);    

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = Utils.makeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            result = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int id = 0;
            connection = Utils.makeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }

            } catch (SQLException e) {
                System.out.println(e);
                return 0;
            }
        }
    }

    @Override
    public int queryCount(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Utils.makeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            connection.commit();
            connection.setAutoCommit(true);
            while(resultSet.next()){
                return resultSet.getInt("total");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0;
            }
        }
    }

    

}

