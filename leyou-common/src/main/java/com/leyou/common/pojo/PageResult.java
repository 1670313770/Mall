package com.leyou.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author CK
 * @create 2020-02-09-15:18
 */
@Data
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;

}
