package com.example.mydemo.core.dao.repository;

import com.example.mydemo.core.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PatientRepo extends CrudRepository<Patient,Serializable> {
}
