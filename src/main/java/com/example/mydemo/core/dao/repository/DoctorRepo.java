package com.example.mydemo.core.dao.repository;

import com.example.mydemo.core.entity.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor,Serializable> {

    public List<Doctor> findDoctorsByAgeAfter(int age);

}
