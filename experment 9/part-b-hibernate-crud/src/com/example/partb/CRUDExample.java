package com.example.partb;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CRUDExample {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // CREATE
        Student s1 = new Student("Alice","Java");
        session.save(s1);
        // READ
        List<Student> students = session.createQuery("from Student", Student.class).list();
        for (Student s : students) {
            System.out.println(s.getId() + ": " + s.getName() + " (" + s.getCourse() + ")");
        }
        // UPDATE
        s1.setCourse("Spring Boot");
        session.update(s1);
        // DELETE (commented)
        // session.delete(s1);
        tx.commit();
        session.close();
        HibernateUtil.shutdown();
    }
}
