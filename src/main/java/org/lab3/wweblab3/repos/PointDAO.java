package org.lab3.wweblab3.repos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.lab3.wweblab3.entities.Point;

import java.util.List;

public class PointDAO {
    private static PointDAO _instance;
    private static SessionFactory sessionFactory;
    public static PointDAO getInstance()
    {
        if(_instance == null)
        {
            _instance = new PointDAO();
        }
        return _instance;
    }
    public PointDAO()
    {

    }
    public static SessionFactory  getSessionFactory()
    {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Point.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Some problems: " + e);
            }
        }
        return sessionFactory;
    }
    public Point getById(int id)
    {
        Session session = getSessionFactory().openSession();
        var res = session.get(Point.class, id);
        session.close();
        return res;
    }
    public List<Point> getAll()
    {   Session session = getSessionFactory().openSession();
        var res = session.createQuery("from Point").list();
        System.out.println("Got list of points: " + res.size());
        session.close();
        return res;
    }
    public void save(Point point) {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(point);
        tx1.commit();
        session.close();
    }
    public void clear() {
        Session session = getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("delete from Point").executeUpdate();
        tx1.commit();
        session.close();
    }
}
