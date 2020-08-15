package com.PingPongManagement.repositories;

import com.PingPongManagement.models.AppRole;
import com.PingPongManagement.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AppRoleRepository {
    @Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<String> getRoleNames(Integer userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " +
                "where" +
                " ur.appUser.userId = :userId";
        Query query = entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public AppRole findById(Integer roleId) {
        String sql = "Select ar from " + AppRole.class.getName() + " ar " + "where ar.roleId = " +
                ":roleId";

        Query query = entityManager.createQuery(sql, AppRole.class);
        query.setParameter("roleId", roleId);

        return (AppRole) query.getSingleResult();
    }
}
