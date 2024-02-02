package com.bc208.blog.repository.base.mapper;

import com.bc208.blog.pojo.db.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface DBlearnMapper {

    int driverAddd(Driver driver);

    int busAdd(Bus bus);

    int peccancyAdd(Peccancy peccancy);

    List<Driver> fleetDrivers(Integer fleet_id);

    List<PeccancyVO> getDriverPeccancy(Integer driver_id, LocalDateTime startTime, LocalDateTime endTime);

    List<FleetPeccancyVO> fleetPeccancys(Integer fleet_id, LocalDateTime startTime, LocalDateTime endTime);
}
