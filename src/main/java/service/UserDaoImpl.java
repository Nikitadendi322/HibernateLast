package service;

import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        return user;
    }

    @Override
    public User getById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> getAllUser() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User").list();
        }
    }

    @Override
    public User updateUser(User user) {
        User updated;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            updated = (User) session.merge(user);
            transaction.commit();
        }
        return updated;
    }

    @Override
    public void deleteUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void addRoleToUser(User user, Role role) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User updateUser = session.find(User.class, user.getId());
            Role associatedRole = session.find(Role.class, role.getId());
            updateUser.getRoles().add(associatedRole);
            transaction.commit();
            session.close();
        }
    }
}
