package com.study.sportplay.dao;

import com.study.sportplay.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUserByMessage(@Param("username") String username, @Param("password") String password);

    List<User> getAllUser(@Param("username") String username, @Param("pageStart") int PageStart, @Param("pageSize") int pageSize);

    int getUserCounts(@Param("username")String username);

    int updateState(Integer id ,Boolean state);

    int addUser(User user);

    int deleteUser(int id);

    User getUpdateUser(int id);
    int editUser(User user);

}
