package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class CourseMemberLimitStrategyEntity implements Strategy {
    Long id;
    Byte minMember;
    Byte maxMember;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getMinMember() {
        return minMember;
    }

    public void setMinMember(Byte minMember) {
        this.minMember = minMember;
    }

    public Byte getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Byte maxMember) {
        this.maxMember = maxMember;
    }


    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        if(teamAllEntity.getNumOfMember()>minMember&&teamAllEntity.getNumOfMember()<maxMember){
            return true;
        }else{
            return true;
        }
    }
}