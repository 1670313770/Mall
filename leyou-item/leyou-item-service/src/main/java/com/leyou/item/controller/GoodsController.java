package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.dto.SpuDTO;
import com.leyou.item.service.GoodsService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CK
 * @create 2020-02-10-19:31
 */
@Controller
@RequestMapping("/spu")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/page")
    public ResponseEntity<PageResult<SpuDTO>> querySpuBoByPage(
            @RequestParam(name = "key",required = false) String key,
            @RequestParam(name = "saleable",required = false) Boolean saleable,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "rows",defaultValue = "5") Integer rows
    ){
        PageResult<SpuDTO> pageResult = goodsService.querySpuByPage(key,saleable,page,rows);
        if(pageResult == null || CollectionUtils.isEmpty(pageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

}
