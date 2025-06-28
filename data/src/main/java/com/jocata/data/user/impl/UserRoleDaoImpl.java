package com.jocata.data.user.impl;

import com.jocata.data.user.UserRoleDao;
import com.jocata.datamodel.user.entity.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserRole save(UserRole userRole) {
        return em.merge(userRole);
    }

}
