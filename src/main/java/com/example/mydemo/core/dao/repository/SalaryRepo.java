package com.example.mydemo.core.dao.repository;

import com.example.mydemo.core.entity.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SalaryRepo extends CrudRepository<Salary,Serializable> {
}
