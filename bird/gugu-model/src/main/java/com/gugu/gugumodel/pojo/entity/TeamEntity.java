package com.gugu.gugumodel.pojo.entity;

import com.gugu.gugumodel.pojo.vo.TeamMessageVO;

/**
 * 储存队伍信息的entity，与表对应
 * @author ren
 */
public class TeamEntity {
    private Long id;
    private Long klassId;
    private Long courseId;
    private Long leaderId;
    private String teamName;
    private Integer teamSerial;
    private Integer status;

    public TeamEntity(){

    }

    public TeamEntity(TeamMessageVO teamMessageVO){
        this.klassId=teamMessageVO.getKlass_id();
        this.courseId=teamMessageVO.getCourse_id();
        this.teamName=teamMessageVO.getTeam_name();
        this.leaderId=teamMessageVO.getLeader().getId();
        this.status=teamMessageVO.getStatus();
        this.id=teamMessageVO.getTeamId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
