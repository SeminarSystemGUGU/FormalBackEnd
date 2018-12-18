package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.vo.ActiveUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ren
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;
    @Test
    public void getMembers(){
        System.out.println(studentMapper.getMembers(1L).size());
    }

    @Test
    public void getLeader(){
        System.out.println(studentMapper.getLeader(1L).getStudentName());
    }

    @Test
    public void getStudentWithoutTeamTest(){
        System.out.println(studentMapper.getStudentWithoutTeam(1L).size());
    }

    @Test
    public void activeStudent(){
        ActiveUserVO activeUserVO =new ActiveUserVO();
        activeUserVO.setEmail("123");
        activeUserVO.setPassword("123");
        activeUserVO.setUserId(2L);
        studentMapper.activeStudent(activeUserVO);
//        studentMapper.changeEmail("123",2L);
    }

    @Test
    public void newStudentTest(){
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setAccount("113831843");
        studentEntity.setStudentName("任剑鹏");
        studentMapper.newStudent(studentEntity);
        System.out.println(studentEntity.getId());
    }
}
