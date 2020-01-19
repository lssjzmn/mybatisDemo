package com.example.mydemo.core.dao.api;

import com.example.mydemo.core.entity.ParkingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface ParkingRecordMapper {

    List<ParkingRecord> findParkingRecordsByCarNo(@Param("carNo") String carNo,
                                                  @Param("ioType") Character ioType,
                                                  @Param("payed") Character payed);

}
