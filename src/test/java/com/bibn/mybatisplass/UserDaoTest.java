package com.bibn.mybatisplass;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bibn.mybatisplass.dao.UserDao;
import com.bibn.mybatisplass.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDaoTest
 * @Description TODO
 * @Author admin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao dao;

    public void sys(List list){
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectAll(){
        List<User> users = dao.selectList(null);
        sys(users);
    }

    @Test
    public void testIntert(){
        User user = new User();
        user.setAge(20);
        user.setUserName("123");
        user.setEmail("test@asd.com");
        user.setName("张三");
        user.setPassword("123");

        int insert = dao.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());

    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1L);//主键
        user.setAge(21);//更新字段
        //根据id更新，更新不为null的字段
        this.dao.updateById(user);

    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setAge(22);//更新字段

        //更新条件
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",6);

//        执行更新操作
        int result = this.dao.update(user,userQueryWrapper);
        System.out.println("result"+result);

    }

    @Test
    public void testUpdate2(){
//        更新的条件以及字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",6).set("user_name","mike");
//        执行更新操作
        int result = this.dao.update(null,wrapper);
        System.out.println("result"+result);

    }

    @Test
    public void testDeleteById(){
//        执行进行操作
        int result = dao.deleteById(6L);
        System.out.println("result"+result);
    }

    @Test
    public void testDeleteByMap(){
        Map map = new HashMap<String,Object>();
        map.put("age",20);
        map.put("name","张三");
//        将map中的元素设置成删除的条件，多个之间的and关系
        int i = dao.deleteByMap(map);
        System.out.println("result"+i);
    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setAge(20);
        user.setName("张三");
//        将实体类对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int delete = dao.delete(wrapper);
        System.out.println("result"+ delete);
    }

    @Test
    public void testDelete3(){
//        根据id集合批量删除
        int i = dao.deleteBatchIds(Arrays.asList(1L, 2L));
        System.out.println("result"+i);
    }

    @Test
    public void testSelectById(){
//        根据id查询数据
        User user = dao.selectById(2L);
        System.out.println("resulyt"+user);
    }

    @Test
    public void testSelectBatchIds(){
//        根据id集合批量查询
        List<User> users = dao.selectBatchIds(Arrays.asList(2L, 3L, 4L));
        sys(users);
    }

    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi1");
//        根据条件查询一条数据，如果结果超过一条就会报错
        User user = dao.selectOne(wrapper);

        System.out.println(user);
    }

    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",19);
        Integer integer = dao.selectCount(wrapper);
        System.out.println("count="+integer);
    }

    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",2);//年龄大于23岁
//        根据条件查询数据
        List<User> users = dao.selectList(wrapper);
        sys(users);
    }

    @Test
    public void testSelectPage(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",20);//年龄大于20岁

        Page<User> userPage = new Page<>(3,2);

//        根据条件查询数据
        IPage<User> userIPage = dao.selectPage(userPage, wrapper);
        System.out.println("数据总条数"+userIPage.getTotal());
        System.out.println("总页数"+userIPage.getPages());

        List<User> records = userIPage.getRecords();
        sys(records);
    }

    @Test
    public void testList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","李");

        List<User> users = dao.selectList(wrapper);
        sys(users);
    }
    
    @Test
    public void testSelect(){
        HashMap<String, Object> map = new HashMap<>();

        List<User> users = dao.selectByMap(map);
        sys(users);
    }

    public static void main(String[] args) {
        System.out.println(123);
    }
}
