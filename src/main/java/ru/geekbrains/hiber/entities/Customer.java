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
@Table(name = "customers")
public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "fullname")
        private String fullname;

        @ManyToMany
        @JoinTable(
                name = "customers_products",
                joinColumns = @JoinColumn(name = "customer_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id")
        )
        private List<Product> products;

        public Customer(String fullname){
                this.fullname = fullname;
        }

        @Override
        public String toString() {
                return String.format("Customer [id = %d, fullname = %s]", id, fullname);}
    }
