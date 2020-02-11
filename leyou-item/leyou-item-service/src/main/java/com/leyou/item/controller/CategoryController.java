package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author CK
 * @create 2020-02-08-20:05
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询子节点
     * @param pid
     * @return
     */
//    @CrossOrigin(origins = {"http://manage.leyou.com"})
    @GetMapping("list")
    public ResponseEntity findCategoryByPid(@RequestParam(name = "pid",defaultValue = "0")Long pid){
        try {
            if(pid == null || pid<0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            List<Category> categoryByPid = categoryService.findCategoryByPid(pid);
            if(categoryByPid == null || categoryByPid.size()==0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.ok(categoryByPid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
