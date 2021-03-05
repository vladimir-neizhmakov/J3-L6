package ru.geekbrains.hiber.repositories;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hiber.entities.Customer;
import ru.geekbrains.hiber.entities.Product;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void create(String newProduct, Double cost) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new Product(newProduct, BigDecimal.valueOf(cost)));
            session.getTransaction().commit();
        }
    }

    public List<Product> read() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            System.out.println("products.toString() from repository1");
            List<Product> products = session.createQuery("from Product ").getResultList();
            System.out.println(products);
            session.getTransaction().commit();
            return Collections.unmodifiableList(products);
        }
    }

    public void update(Long id, String newtitle, Double cost) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setTitle(newtitle);
            product.setCost(BigDecimal.valueOf(cost));
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
