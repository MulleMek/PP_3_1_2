package com.spboot.springboot.dao;

import com.spboot.springboot.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User u) {
        User user = entityManager.find(User.class,id);
        user.setName(u.getName());
        user.setLastName(u.getLastName());
        user.setEmail(u.getEmail());
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }
}