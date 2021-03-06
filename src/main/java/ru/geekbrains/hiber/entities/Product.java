package ru.geekbrains.hiber.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "title")
        private String title;

        @Column(name = "cost")
        private BigDecimal cost;

        @ManyToMany
        @JoinTable(
                name = "customers_products",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "customer_id")
        )
        private List<Customer> customers;

        public Product(String title, BigDecimal cost){
                this.title = title;
                this.cost = cost;
        }

        @Override
        public String toString() {
                return String.format("Product [id = %d, title = %s, price = %s]", id, title, cost);}
    }
