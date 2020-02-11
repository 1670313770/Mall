package com.leyou.item.dto;

import com.leyou.item.pojo.Brand;
import lombok.Data;

import java.util.List;

/**
 * @author CK
 * @create 2020-02-09-20:45
 */
@Data
public class BrandAndCateGoryDTO {

    private Long id;
    private String name;
    private String image;
    private Character letter;
    private String cids;

}
