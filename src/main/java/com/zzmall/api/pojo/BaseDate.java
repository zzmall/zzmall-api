package com.zzmall.api.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zzmall.api.common.serializer.JsonDateSerializer;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Connor
 * @Date 2019/05/18 16:29
 * <p>
 * 时间抽象类，不能被实例化
 *
 * @MappedSuperclass 代码复用，不会被映射到数据库表
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class BaseDate {


    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    @CreatedDate
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    @LastModifiedDate
    private Date updateTime;

}
