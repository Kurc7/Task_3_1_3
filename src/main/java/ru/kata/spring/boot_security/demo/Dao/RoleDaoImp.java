package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImp implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(String role) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r WHERE r.name = : role")
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r").getResultList();
    }
}
