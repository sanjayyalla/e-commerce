package com.jocata.data.user.impl;

import com.jocata.data.user.RoleDao;
import com.jocata.datamodel.user.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Role findById(Integer id) {
        return em.find(Role.class, id);
    }

}
