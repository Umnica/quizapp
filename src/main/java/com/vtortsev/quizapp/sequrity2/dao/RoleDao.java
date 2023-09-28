package com.vtortsev.quizapp.sequrity2.dao;

import com.vtortsev.quizapp.sequrity2.entities.ERole;
import com.vtortsev.quizapp.sequrity2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
