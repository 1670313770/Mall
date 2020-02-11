package com.leyou.item.pojo;

import lombok.Data;

import javax.annotation.security.DenyAll;
import javax.persistence.*;
import java.util.List;

/**
 * @author CK
 * @create 2020-02-10-15:54
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private String name;

    @Transient
    private List<SpecParam> params;

    // getter和setter省略
}
