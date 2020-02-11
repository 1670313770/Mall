package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author CK
 * @create 2020-02-09-15:39
 */
@Repository
public interface BrandMapper extends Mapper<Brand> {

    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    public void insertBrandAndCategory(@Param("bid") Long bid,@Param("cid") Long cid);

    @Select("SELECT * FROM tb_brand WHERE id IN(SELECT brand_id FROM tb_category_brand WHERE category_id = #{cid})")
    public List<Brand> queryBrandsByCid(@Param("cid") Integer cid);
}
