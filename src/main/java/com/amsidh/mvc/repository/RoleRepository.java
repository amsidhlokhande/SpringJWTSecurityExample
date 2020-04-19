package com.amsidh.mvc.repository;

import com.amsidh.mvc.auth.model.EnumRole;
import com.amsidh.mvc.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(EnumRole name);
}
