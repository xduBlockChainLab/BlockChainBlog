// package com.bc208.blog.controller;
//
// import com.bc208.blog.common.dto.DemandDto;
// import com.bc208.blog.service.impl.DemandServiceImpl;
// import com.bc208.blog.utils.ResultInfo;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
//
// /**
//  * @author QingheLi
//  */
// @Controller
// @RequestMapping("/bc208/api")
// public class DemandController {
//
//     @Autowired
//     private DemandServiceImpl demandService;
//
//     @ResponseBody
//     @RequestMapping("/demand/build")
//     public ResultInfo buildDemand(@RequestBody DemandDto demandDto){
//         demandDto.setDemandStatus(0);
//         System.out.println(demandDto);
//         ResultInfo resultInfo = new ResultInfo();
//         int key = demandService.buildDemand(demandDto);
//         if (key == 1){
//             resultInfo.setMsg("success");
//         }else{
//             resultInfo.setMsg("fail");
//         }
//         return resultInfo;
//     }
//
//     @ResponseBody
//     @PostMapping("/demand/change")
//     public ResultInfo changeDemand(@RequestBody DemandDto demandDto){
//         ResultInfo resultInfo = new ResultInfo();
//         int key = demandService.changeDemand(demandDto);
//         if (key == 1){
//             resultInfo.setMsg("success");
//         }else{
//             resultInfo.setMsg("fail, 需求不存在");
//         }
//         return resultInfo;
//     }
//
// }
