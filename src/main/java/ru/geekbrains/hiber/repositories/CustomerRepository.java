package ru.geekbrains.hiber.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.hiber.entities.Customer;
import ru.geekbrains.hiber.entities.Product;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CustomerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void create(String newCustomer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new Customer(newCustomer));
            session.getTransaction().commit();
        }
    }

    public void read() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer").getResultList();
            System.out.println(customers);
            session.getTransaction().commit();
        }
    }

    public void update(Long id, String newFullName) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            customer.setFullname(newFullName);
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
