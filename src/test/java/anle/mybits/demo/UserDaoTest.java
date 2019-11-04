package anle.mybits.demo;

import anle.mybits.demo.dao.UserMapper;
import anle.mybits.demo.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("小雨");
        user.setAge(24);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setReMark("一个无需要的星系");
        int rows = userMapper.insert(user);
        System.out.println("影响的记录数：  " + rows);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name","雨").lt("age",40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }


}