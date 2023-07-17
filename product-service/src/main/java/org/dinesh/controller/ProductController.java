package org.dinesh.controller;

import lombok.RequiredArgsConstructor;
import org.dinesh.ConditionClass.ProductFilterClass;
import org.dinesh.dto.ProductResponseDto;
import org.dinesh.service.ProductService;
import org.dinesh.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {


    private   ProductService productService; // mocktio could not inject private static final  varibles
    @GetMapping()
    public String Hello()
    {
        LogUtil.info("Hello World!");
        return "Hello World!";
    }
    @GetMapping("/random")
    public List<ProductResponseDto> getRandomProduct(@RequestParam int offset)
    {
        System.out.println(offset);
        var results = productService.getRandomProduct(offset);
        System.out.println(results.size());
        return results;
    }


//    @GetMapping("/categories")
//    public List<ProductResponseDto> getProductByCategory(@RequestParam("category") String category, @RequestParam("limit") int limit ,@RequestParam("offset") int offset )
//    {
//        return productService.getProductByCategory(category,limit,offset);
//    }

    @GetMapping("/withCondtion")
    public List<ProductResponseDto> getProductByFilter(@RequestBody ProductFilterClass filter)
    {
        LogUtil.info(String.valueOf(filter.getLimit()));
        System.out.println( filter.hashCode());
        System.out.println(filter.getLimit());
       var  result =   productService.getProductByFilter(filter);
        System.out.println(result.size());
       LogUtil.info(String.valueOf(result.size()));

       return  result ;
    }




}
