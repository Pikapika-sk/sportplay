package com.study.sportplay.controller;

import com.study.sportplay.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.study.sportplay.bean.User;
import com.alibaba.fastjson.*;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        String flag = "error";
        User us = userDao.getUserByMessage(user.getUsername(),user.getPassword());
        System.out.println("User:" +us);
        HashMap<String,Object> res =  new HashMap<>();
        if(us!=null){
            flag = "ok";
        }
        res.put("flag",flag);
        res.put("user",user);
        String resJSON = JSON.toJSONString(res);
        return resJSON;
    }
}
