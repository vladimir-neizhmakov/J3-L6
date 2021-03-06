package ru.geekbrains.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hiber.entities.Product;
import ru.geekbrains.hiber.services.HW6Service;

import java.math.BigDecimal;


@RestController
@RequestMapping("/crud")

public class hw6 {
    @Autowired
    private HW6Service Service;

    @GetMapping
    public void crud(){
        System.out.println("Реализация read: "+Service.getProducts());
    //    System.out.println("Реализация create: newProduct ");
     //   Service.addProduct(new Product("newProduct", BigDecimal.valueOf(33.33)));
     //   System.out.println(Service.getProducts());
    //    System.out.println("Реализация update: newProduct ");
     //   Service.updateProduct(4L, "newProductUpdated",BigDecimal.valueOf(44.44));
     //   System.out.println(Service.getProducts());
     //   System.out.println("Реализация delete: newProductUpdated ");
    //    Service.deleteProduct(4L);
    //    System.out.println(Service.getProducts());
        System.out.println("Реализация поиска продуктов по id покупателя");
        System.out.println(Service.getCustomerProducts(1L));
        System.out.println("Реализация поиска покупателей по id продукта");
        System.out.println(Service.getProductCustomers(1L));
    }


}
