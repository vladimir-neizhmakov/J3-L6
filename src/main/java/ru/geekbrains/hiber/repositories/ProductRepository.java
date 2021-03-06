package ru.geekbrains.hiber.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hiber.entities.Product;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.sql.Wrapper;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final SessionFactory sessionFactory;
    List<Product> product;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void create(Product newProduct) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(newProduct);
            session.getTransaction().commit();
        }
    }

    public List<Product> read() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product ").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(products);
        }
    }

    public void update(Long id, String newtitle, BigDecimal cost) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setTitle(newtitle);
            product.setCost(cost);
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

    public List getCustomerProducts(long customer_id){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            //Не получилось победить вернуть результат запроса как List<Product>
            List products = session.createQuery("select distinct c.products from Customer c where c.id = :customer_id")
                    .setParameter("customer_id", customer_id)
                    .getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(products);
        }
    }
}
