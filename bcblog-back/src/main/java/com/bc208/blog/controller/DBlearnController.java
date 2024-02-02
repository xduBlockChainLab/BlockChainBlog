package com.bc208.blog.controller;

import com.bc208.blog.common.dto.Result;
import com.bc208.blog.pojo.db.*;
import com.bc208.blog.repository.base.mapper.DBlearnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author QingheLi
 */
@Slf4j
@RestController
@RequestMapping("/database/")
public class DBlearnController {

    @Autowired
    private DBlearnMapper dbLearnMapper;

    @PostMapping("/driverAdd")
    @ResponseBody
    public Result driverAdd(@RequestBody Driver driver){
        if (dbLearnMapper.driverAddd(driver) == 1) {
            return Result.success("司机信息录入成功");
        }else{
            return Result.fail("司机信息录入失败");
        }
    }

    @PostMapping("/BusAdd")
    @ResponseBody
    public Result busAdd(@RequestBody Bus bus){
        if (dbLearnMapper.busAdd(bus) == 1) {
            return Result.success("汽车信息录入成功");
        }else{
            return Result.fail("汽车信息录入失败");
        }
    }

    @PostMapping("/peccancyAdd")
    @ResponseBody
    public Result peccancyAdd(@RequestBody Peccancy peccancy){
        if (dbLearnMapper.peccancyAdd(peccancy) == 1) {
            return Result.success("违章信息录入成功");
        }else{
            return Result.fail("违章信息录入失败");
        }
    }

    @GetMapping("/fleetDrivers")
    @ResponseBody
    public Result fleetDrivers(Integer fleet_id){
        List<Driver> drivers = dbLearnMapper.fleetDrivers(fleet_id);
        if (drivers == null){
            return Result.fail("查询车队成员失败");
        }
        return Result.success(drivers);
    }

    @PostMapping("/driverPeccancy")
    @ResponseBody
    public Result driverPeccancy(@RequestBody DriverPeccancyDTO driverPeccancyDTO){
        List<PeccancyVO> peccancies = dbLearnMapper.getDriverPeccancy(driverPeccancyDTO.getDriver_id(), driverPeccancyDTO.getStartTime(), driverPeccancyDTO.getEndTime());
        if (peccancies == null){
            return Result.fail("查询司机违规信息失败");
        }
        return Result.success(peccancies);
    }

    @PostMapping("/fleetPeccancy")
    @ResponseBody
    public Result fleetPeccancy(@RequestBody FleetPeccancyDTO fleetPeccancyDTO){
        List<FleetPeccancyVO> peccancys = dbLearnMapper.fleetPeccancys(fleetPeccancyDTO.getFleet_id(), fleetPeccancyDTO.getStartTime(), fleetPeccancyDTO.getEndTime());
        if (peccancys == null){
            return Result.fail("查询车队成员失败");
        }
        return Result.success(peccancys);
    }
}
