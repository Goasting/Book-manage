package cn.web.test;

import cn.web.dao.UserDao;
import cn.web.domain.User;
import org.junit.Test;

public class UserDaoTest {



    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("zhang");
        loginuser.setPassword("123");


        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }
}
