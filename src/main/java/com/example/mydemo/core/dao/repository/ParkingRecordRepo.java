package com.example.mydemo.core.dao.repository;

import com.example.mydemo.core.entity.ParkingRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ParkingRecordRepo extends CrudRepository<ParkingRecord, Serializable> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM parking_io_record P " +
                    "WHERE P.car_no = :carNo AND P.io_type = :ioType AND P.payed = :payed " +
                    "AND TIMESTAMPDIFF(HOUR ,P.io_time,NOW()) >= " + 24 +
                    " ORDER BY P.io_time DESC ")
    public List<ParkingRecord> findParkingRecordsByNativeSql(@Param("carNo") String carNo,
                                                             @Param("ioType") Character ioType,
                                                             @Param("payed") Character payed);

}
