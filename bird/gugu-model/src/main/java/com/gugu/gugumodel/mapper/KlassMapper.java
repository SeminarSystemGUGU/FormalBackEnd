package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.KlassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface KlassMapper {
    /**
     * 获取课程下的所有班级信息
     * @param courseId
     * @return
     */
    ArrayList<KlassEntity> getKlassByCourseId(Long courseId);

    /**
     * 新建班级
     * @param klassEntity
     */
    void newKlass(KlassEntity klassEntity);

    /**
     * 根据班级获取课程id
     * @param klassId
     * @return
     */
    Long getCourseIdByKlass(Long klassId);

    /**
     * 根据id删除班级的记录
     * @param klassId
     */
    void deleteKlassById(Long klassId);

    /**
     * 根据id获取班级信息
     * @param klassId
     */
    KlassEntity getKlassById(Long klassId);

    /**
     * 根据课程获取班级id列表
     * @param courseId
     * @return
     */
     ArrayList<Long> getKlassIdByCourseId(Long courseId);
    /**@author ljy
     *获取课程下已有的serial
     * @param courseId
     */
    public ArrayList<Byte> getSerial(Long courseId);
}
