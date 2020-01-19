package com.example.mydemo.core.dao.api;

import com.example.mydemo.core.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Service
public interface DepartmentDaoMapper {

    List<Department> findDepartmentsByClas(@Param("params") Map params);

}
