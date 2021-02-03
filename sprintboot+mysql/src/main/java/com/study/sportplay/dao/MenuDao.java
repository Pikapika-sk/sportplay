package com.study.sportplay.dao;


import com.study.sportplay.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
     List<MainMenu> getMenus();
}
