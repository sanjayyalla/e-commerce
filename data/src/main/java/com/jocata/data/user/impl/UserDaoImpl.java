package com.jocata.data.user.impl;

import com.jocata.data.user.UserDao;
import com.jocata.datamodel.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public String deleteById(Integer id) {
        User u = findById(id);
        if (u != null) entityManager.remove(u);
        return "Deleted";
    }
}
