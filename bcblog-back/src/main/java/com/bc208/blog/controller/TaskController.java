// package com.bc208.blog.controller;
// import com.bc208.blog.service.impl.TaskServiceImpl;
// import com.bc208.blog.pojo.UserTask;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import com.bc208.blog.utils.ParamsException;
// import com.bc208.blog.utils.ResultInfo;
//
// import java.util.List;
//
// /**
//  * @author QingheLi
//  */
// @RestController
// @RequestMapping("/bc208/api")
//
// public class TaskController {
//     @Autowired
//     private TaskServiceImpl taskServiceImpl;
//
//     @PostMapping("/tasks")
//     @ResponseBody
//     public ResultInfo userTask(@RequestParam("token") String token){
//         System.out.println(token);
//         ResultInfo resultInfo = new ResultInfo();
//         String[] split = token.split("=");
//         String tokenId=split[1];
//         Integer id = Integer.parseInt(tokenId);
//         try{
//             List<UserTask> userTask = taskServiceImpl.userTodolist(id,0);
//             resultInfo.setResult(userTask);
//         }catch(ParamsException p){
//             resultInfo.setCode(p.getCode());
//             resultInfo.setMsg(p.getMsg());
//             p.printStackTrace();
//         }catch (Exception e){
//             resultInfo.setCode(500);
//             resultInfo.setMsg("查找失败");
//         }
//         return resultInfo;
//     }
//
//     @PostMapping("/finished")
//     @ResponseBody
//     public ResultInfo userFinished(@RequestParam("token") String token){
//         ResultInfo resultInfo = new ResultInfo();
//         String[] split = token.split("=");
//         String tokenId=split[1];
//         Integer id = Integer.parseInt(tokenId);
//         try{
//             List<UserTask> userTask = taskServiceImpl.userTodolist(id,1);
//             resultInfo.setResult(userTask);
//         }catch(ParamsException p){
//             resultInfo.setCode(p.getCode());
//             resultInfo.setMsg(p.getMsg());
//             p.printStackTrace();
//         }catch (Exception e){
//             resultInfo.setCode(500);
//             resultInfo.setMsg("查找失败");
//         }
//         return resultInfo;
//     }
//
//     @PostMapping("/change")
//     @ResponseBody
//     public ResultInfo updateTaskTye(
//              @RequestParam("token") String token
//             ,@RequestParam("task_id") Integer task_id
//             ,@RequestParam("task_type") Integer task_type)
//     {
//         System.out.println(task_type);
//         ResultInfo resultInfo = new ResultInfo();
//         String[] split = token.split("=");
//         String tokenId=split[1];
//         Integer id = Integer.parseInt(tokenId);
//         try{
//             UserTask userTask= taskServiceImpl.updateTaskType(id,task_id,task_type);
//             resultInfo.setResult(userTask);
//         }catch (ParamsException p){
//             resultInfo.setCode(p.getCode());
//             resultInfo.setMsg(p.getMsg());
//             p.printStackTrace();
//         }catch (Exception e){
//             resultInfo.setCode(500);
//             resultInfo.setMsg("信息更新失败");
//         }
//         return  resultInfo;
//     }
//
//     @PostMapping("/insert")
//     @ResponseBody
//     public ResultInfo insertTask(@RequestParam("token") String token,
//                                  @RequestParam("task_content") String task_content){
//         ResultInfo resultInfo = new ResultInfo();
//         String[] split = token.split("=");
//         String tokenId=split[1];
//         Integer id = Integer.parseInt(tokenId);
//         try{
//             List<UserTask> userTask= taskServiceImpl.insertTask(id,task_content);
//             resultInfo.setResult(userTask);
//         }catch (ParamsException p){
//             resultInfo.setCode(p.getCode());
//             resultInfo.setMsg(p.getMsg());
//             p.printStackTrace();
//         }catch (Exception e){
//             resultInfo.setCode(500);
//             resultInfo.setMsg("任务添加失败");
//         }
//         return  resultInfo;
//     }
//
//     @PostMapping("/update")
//     @ResponseBody
//     public  ResultInfo updateTask(@RequestParam("token") String token,
//                                   @RequestParam("task_id") Integer task_id,@RequestParam("task_content") String task_content){
//
//         ResultInfo resultInfo = new ResultInfo();
//         String[] split = token.split("=");
//         String tokenId=split[1];
//         Integer id = Integer.parseInt(tokenId);
//         try{
//             UserTask userTask= taskServiceImpl.updateTask(id,task_id,task_content);
//             resultInfo.setResult(userTask);
//         }catch (ParamsException p){
//             resultInfo.setCode(p.getCode());
//             resultInfo.setMsg(p.getMsg());
//             p.printStackTrace();
//         }catch (Exception e){
//             e.printStackTrace();
//             resultInfo.setCode(500);
//             resultInfo.setMsg("任务更新失败");
//         }
//         return  resultInfo;
//     }
//
//     @PostMapping("/delete")
//     @ResponseBody
//     public ResultInfo deleteTask(@RequestParam("token") String token,
//                                  @RequestParam("task_id") Integer task_id){
//         ResultInfo resultInfo=new ResultInfo();
//         String[] split = token.split("=");
//         String tokenId=split[1];
//         Integer id = Integer.parseInt(tokenId);
//         try{
//             String result= taskServiceImpl.deleteTask(id,task_id);
//             resultInfo.setResult(result);
//         }catch (ParamsException p){
//             resultInfo.setCode(p.getCode());
//             resultInfo.setMsg(p.getMsg());
//             p.printStackTrace();
//         }catch (Exception e){
//             resultInfo.setCode(500);
//             resultInfo.setMsg("任务删除失败");
//             e.printStackTrace();
//         }
//         return  resultInfo;
//     }
//
// }
