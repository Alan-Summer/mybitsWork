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
        query.like("name", "雨").lt("age", 40);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨 并且年龄大于等于20且小于40 并且email不为空
     */

    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name","雨")
                .between("age",20,40)
                .isNotNull("email");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }


    /**
    * 名字为王性 或者年龄大于等于25，按照年龄降序排列，
     * 年龄相同的按照ID升序排列
    * **/

    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("name","王")
                .or().ge("age",25)
                .orderByDesc("age")
                .orderByAsc("id");

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日 并且直属上级为王姓
     * */

    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> query = new QueryWrapper<>();
      query.apply("date_format(create_time,'%Y-%m-%d')={0}","2019-02-14")
              .inSql("manager_id","select id from user where name like '王%'");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }
}
