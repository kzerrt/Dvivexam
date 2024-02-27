package com.fc.pojo.po;

import com.fc.common.foreign.anno.ForeignField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Florence
 * @since 2023/05/05
 */
public class ExamPlaceCourse {
    /**
     * 主键
     */
    private Integer id;
    
    private Date createTime;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 课程类型：0是课程合集，2是科目2，3是科目3
     */
    private Short type;
    
    private String intro;
    /**
     * 视频
     */
    private String video;
    /**
     * 封面
     */
    private String cover;
    /**
     * 考场
     */
    @ForeignField(ExamPlace.class)
    private Integer placeId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

}


