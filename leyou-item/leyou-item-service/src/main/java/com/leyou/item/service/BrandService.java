package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.dto.BrandAndCateGoryDTO;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author CK
 * @create 2020-02-08-20:03
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example=new Example(Brand.class);
        if(Strings.isNotBlank(key)){
            example.createCriteria().andLike("name","%"+key+"%").orLike("letter",key);
        }

        PageHelper.startPage(page,rows);

        if(Strings.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc?"desc":"asc"));
        }

        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo=new PageInfo<>(brands);
        PageResult<Brand> result = new PageResult<>();
        result.setItems(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPages());
        return result;
    }

    public void saveBrand(BrandAndCateGoryDTO dto) {
        Brand brand=new Brand();
        BeanUtils.copyProperties(dto,brand);
        int i = brandMapper.insertSelective(brand);
        if(Strings.isNotBlank(dto.getCids())&&i>=1){
            String[] split = dto.getCids().split(",");
            for (String cid : split) {
                brandMapper.insertBrandAndCategory(brand.getId(),Long.parseLong(cid));
            }
        }
    }

    public List<Brand> queryBrandsByCid(Integer cid) {
        List<Brand> list = brandMapper.queryBrandsByCid(cid);
        return list;
    }
}
