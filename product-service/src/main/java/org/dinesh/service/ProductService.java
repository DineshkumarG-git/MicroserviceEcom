package org.dinesh.service;

import java.util.*;
import lombok.RequiredArgsConstructor;
import org.dinesh.ConditionClass.ProductFilterClass;
import org.dinesh.dto.ProductResponseDto;
import org.dinesh.model.Product;
import org.dinesh.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProductService {

    @Autowired
    private  ProductRepository productRepository;
    public List<ProductResponseDto> getRandomProduct(int offset)
    {
          var result =  productRepository.getRandom(10,offset).stream().map(product -> mapToProductResponseDto(product)).toList();
          return result;
    }
    public List<ProductResponseDto> getProductByCategory(String category ,int limit ,int offset)
    {
        return productRepository.getByCategory(category,limit,offset).stream().map((product)->mapToProductResponseDto(product)).toList();
    }

    public List<ProductResponseDto> getProdutByNameLike(String name ,int limit , int offset)
    {
         return productRepository.getByLikeName(name,limit, offset).stream().map(product -> mapToProductResponseDto(product)).toList();
    }

    public List<ProductResponseDto> getProductByFilter(ProductFilterClass filter)
    {
        try {

            if (filter == null) {
                return null;
            } else if (filter.getCatergory() != "" && filter.getPartialName() != null) {
                  productRepository.getProductbyLikeNameWithCategory(filter.getPartialName(),filter.getCatergory(), filter.getLimit(), filter.getLimit());
            } else if (filter.getExactName() != "" && filter.getPartialName() != "") {
                  productRepository.getProductbyNameWithCategory(filter.getExactName(),filter.getCatergory(), filter.getLimit(), filter.getLimit());
            } else if (filter.getExactName() != "") {
                   productRepository.getProductByName(filter.getExactName(),filter.getLimit(),filter.getOffset());
            } else if (filter.getCatergory() != "") {
                   productRepository.getByCategory(filter.getCatergory(),filter.getLimit(),filter.getOffset());
            } else if (filter.getPartialName() != null) {
                  productRepository.getByCategory(filter.getPartialName(),filter.getLimit(),filter.getOffset());
            }

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return new ArrayList<ProductResponseDto>();
    }






    public ProductResponseDto mapToProductResponseDto(Product product)
    {
        return ProductResponseDto.builder().skuCode(product.getSkudCode()).price(product.getPrice()).description(product.getDescription()).productName(product.getProductName())
                .brand(product.getBrand()).Category(product.getCategory()).discount(product.getDiscount()).specification(product.getSpecification()).build();
    }


}
