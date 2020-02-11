package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.dto.SpuDTO;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CK
 * @create 2020-02-10-19:30
 */
@Service
public class GoodsService {
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;


    public PageResult<SpuDTO> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }
        if(saleable != null){
            criteria.andEqualTo("saleable",saleable);
            criteria.andEqualTo("saleable", saleable);
        }

        PageHelper.startPage(page,rows);

        List<Spu> spus = spuMapper.selectByExample(example);
        PageInfo pageInfo=new PageInfo(spus);

        List<SpuDTO> collect = spus.stream().map(spu -> {
            SpuDTO spuDTO=new SpuDTO();
            BeanUtils.copyProperties(spu,spuDTO);

            Brand brand = brandMapper.selectByPrimaryKey(spuDTO.getBrandId());
            spuDTO.setBname(brand.getName());

            List<Category> categories = categoryMapper.selectByIdList(Arrays.asList(spuDTO.getCid1(), spuDTO.getCid2(), spuDTO.getCid3()));
            List<String> collectNames = categories.stream().map(category -> category.getName()).collect(Collectors.toList());
            String join = StringUtils.join(collectNames, "/");

            spuDTO.setCname(join);

            return spuDTO;
        }).collect(Collectors.toList());

        PageResult pageResult = new PageResult();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setItems(collect);
        return pageResult;

    }
}
