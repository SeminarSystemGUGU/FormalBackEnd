package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Mapper
@Repository
public interface TeacherMapper{
        void deleteTeacherById(long id);

        ArrayList<TeacherEntity> getTeachers();

        void resetTeacherPassword(Long teacherId);

        void changeTeacherInformation(TeacherEntity teacherEntity);

        void newTeacher(TeacherEntity teacherEntity);

        TeacherEntity getTeacherById(Long teacherId);

        void changePassword(String password,Long teacherId);

        void changeEmail(String email,Long teacherId);
}
