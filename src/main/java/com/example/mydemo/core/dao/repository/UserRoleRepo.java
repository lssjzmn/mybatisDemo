package com.example.mydemo.core.dao.repository;

import com.example.mydemo.core.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Serializable> {
}
