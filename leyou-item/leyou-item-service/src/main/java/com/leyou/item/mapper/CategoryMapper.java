package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author CK
 * @create 2020-02-08-20:00
 */
@Repository
public interface CategoryMapper extends Mapper<Category> , SelectByIdListMapper<Category,Long> {
}
