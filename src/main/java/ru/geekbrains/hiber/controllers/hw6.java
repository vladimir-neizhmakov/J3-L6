package ru.geekbrains.hiber.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hiber.entities.Product;
import ru.geekbrains.hiber.services.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/start")

public class hw6 {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(){return productService.getProducts();}


}
