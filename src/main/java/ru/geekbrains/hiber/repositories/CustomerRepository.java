package ru.geekbrains.hiber.repositories;

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
public class CustomerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public void create(Customer newCustomer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(newCustomer);
            session.getTransaction().commit();
        }
    }

    public List<Customer> read() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(customers);
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

    public List getProductCustomers(long product_id){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            //Не получилось победить вернуть результат запроса как List<Product>
            List customers = session.createQuery("select p.customers from Product p where p.id = :product_id")
                    .setParameter("product_id", product_id)
                    .getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(customers);
        }
    }
}
