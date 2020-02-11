package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.dto.BrandAndCateGoryDTO;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author CK
 * @create 2020-02-08-20:05
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(name = "key",required = false)String key,
            @RequestParam(name = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(name = "rows",defaultValue = "5",required = false)Integer rows,
            @RequestParam(name = "sortBy",required = false)String sortby,
            @RequestParam(name = "desc",required = false)Boolean desc
    ){
        PageResult<Brand> pageResult=brandService.queryBrandsByPage(key,page,rows,sortby,desc);
        if(CollectionUtils.isEmpty(pageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    @PostMapping
    public ResponseEntity<Void> saveBrand(@RequestBody BrandAndCateGoryDTO dto){
        brandService.saveBrand(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable(name = "cid")Integer cid){
        List<Brand> brands = brandService.queryBrandsByCid(cid);
        if(CollectionUtils.isEmpty(brands)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }

}
