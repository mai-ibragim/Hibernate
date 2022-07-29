package org.example;

import org.example.config.Configur;
import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class App {
    public static void main( String[] args ) {
//      Configur.getFactory();
//        saveUser(new User("Gulmairam", "Ibragimova", 27));
//        saveUser(new User("Gulshaiyr", "Momunova", 26));
//        saveUser(new User("Aizirek", "Saipidin kyzy", 22));
//        System.out.println(getId(1L));
//        System.out.println(getAll());
//        System.out.println(updateUser(1L, "Aiza", "Kasieva", 21));
//        deleteById(1L);
//        deleteAll();
        dropTable();
//        --------------CRUD-------------------
//        CREATE,    READ,    UPDATE,      DELETE

    }
//    ------------------CREATE-------------------------------
    public static void saveUser(User user) {
        try {
            Session session = Configur.getFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
//    ---------------------READ(getId,   getAll)------------------------------
    public static User getId(Long id) {
        try{
            Session session = Configur.getFactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
//    -----------------------getAll--------------------------
    public static List<User> getAll(){
        try {
            Session session = Configur.getFactory().openSession();
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            session.close();
            return users;

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
//    -------------------UPDATE-----------------------------
    public static User updateUser(Long id, String name, String surname, int age) {
       try {
           Session session = Configur.getFactory().openSession();
           session.beginTransaction();
           User user = session.get(User.class, id);
           user.setId(id);
           user.setName(name);
           user.setSurname(surname);
           user.setAge(age);
           session.getTransaction().commit();
           session.close();
           return user;
       } catch (HibernateException e) {
           System.out.println(e.getMessage());
           return null;
       }
    }
//    -----------------DELETE (deleteById,    deleteAll,    dropTable-------------
    public static void deleteById(Long id) {
        try {
            Session session = Configur.getFactory().openSession();
            session.beginTransaction();
           User user = session.get(User.class, id);
           session.delete(user);
           session.getTransaction().commit();
           session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
//    ------------------deleteAll-------------------
    public static void deleteAll() {
        try {
            Session session = Configur.getFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());

        }
    }
    public static void dropTable() {
        try {
            Session session = Configur.getFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
