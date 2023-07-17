package org.dinesh.productservicetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.dinesh.ConditionClass.ProductFilterClass;
import org.dinesh.controller.ProductController;
import org.dinesh.dto.ProductResponseDto;
import org.dinesh.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@RequiredArgsConstructor
public class ProductControllerTest {


    private MockMvc mockMvc;
    private ObjectMapper objectMapper =new ObjectMapper();

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this );
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    ProductResponseDto productResponseDto1  = new ProductResponseDto().builder().productName("Samsung s23").price(BigDecimal.valueOf(1000))
            .brand("Samsung").description("Good Phone").specification("{color : red }").discount(5).skuCode("1234").Category("Mobile").build();
    ProductResponseDto productResponseDto2  = new ProductResponseDto().builder().productName("Iphone 14").price(BigDecimal.valueOf(1000))
            .brand("Apple").description("Good Phone").specification("{color : red }").discount(5).skuCode("1235").Category("Mobile").build();
    ProductResponseDto productResponseDto3  = new ProductResponseDto().builder().productName("Fridge").price(BigDecimal.valueOf(1000))
            .brand("LG").description("Good Fridge").specification("{color : red }").discount(5).skuCode("1236").Category("HomeAppliance").build();
    @Test
    public void getRandomProductTest() throws Exception {
        var id = 0;

        var offset =  String.valueOf(id);
        List<ProductResponseDto> tempList=  new ArrayList<ProductResponseDto>(Arrays.asList(productResponseDto1,productResponseDto2,productResponseDto3));
        System.out.println(tempList.size());
       Mockito.when(productService.getRandomProduct(id)).thenReturn(tempList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/random").param("offset", offset)
//                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
   //             .andExpect( jsonPath("$",hasSize(3))).andExpect(jsonPath("$[2].name", Matchers.is("Grokking Algorithm")));
        var result =   mockMvc.perform(MockMvcRequestBuilders.get("/api/product/random").param("offset", offset).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
                result.andExpect(jsonPath("$",hasSize(3))).andExpect(jsonPath("$[2].productName", Matchers.is("Fridge")));


    }
    @Test
    public void getProductByCategoryTest() throws Exception { // for class as json content always override the hashcode and equals method then it wlll identify the object here and the deserialized object in the controller
        ProductFilterClass filter = new ProductFilterClass();
        filter.setLimit(10);

        List<ProductResponseDto> result=  new ArrayList<ProductResponseDto>(Arrays.asList(productResponseDto1,productResponseDto2,productResponseDto3));
        Mockito.when(productService.getProductByFilter(filter)).thenReturn(result);
        var payload = objectMapper.writeValueAsString(filter);
        System.out.println(filter.hashCode());

        List<ProductResponseDto>  ProductByCategory= new ArrayList<ProductResponseDto>(Arrays.asList(productResponseDto1,productResponseDto2,productResponseDto3));
         mockMvc.perform(MockMvcRequestBuilders.get("/api/product/withCondtion").contentType(MediaType.APPLICATION_JSON).content(payload)).andExpect(status().isOk())
                 .andExpect(jsonPath("$",hasSize(3))).andExpect(jsonPath("$[2].productName",Matchers.is("Fridge")));
    }

}
