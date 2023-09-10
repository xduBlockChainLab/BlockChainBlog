package com.bc208.blog.controller;

import com.bc208.blog.pojo.TeamTask;

import com.bc208.blog.service.TeamTaskService;
import com.bc208.blog.utils.ParamsException;
import com.bc208.blog.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/bc208/api")
public class TeamTaskController {
    @Autowired
    private TeamTaskService teamTaskService;


    @RequestMapping(value = "/teamtasks")

    @ResponseBody
    public ResultInfo showAllTasks(){
        ResultInfo resultInfo =new ResultInfo();
        List<TeamTask> teamTasks = teamTaskService.teamTodolist(0);

        try{
            resultInfo.setResult(teamTasks);
        }catch(ParamsException p){
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        }catch (Exception e){
            resultInfo.setCode(500);
            resultInfo.setMsg("查找失败");
        }
        System.out.println(resultInfo);
        System.out.println(teamTasks);

        return resultInfo;


    }

    @ResponseBody
    @RequestMapping("change_status")
    public  ResultInfo changeStatus(@RequestParam("task_id")int task_id,@RequestParam("task_type") int task_type){
        ResultInfo resultInfo = new ResultInfo();
        teamTaskService.changeStatus(task_id,task_type);

        return resultInfo;
    }


    @ResponseBody
    @RequestMapping("/show_finished")
    public ResultInfo showFinished(){
        ResultInfo resultInfo = new ResultInfo();

        List<TeamTask> finished = teamTaskService.findFinished();
        resultInfo.setResult(finished);
        return resultInfo;
    }

    @RequestMapping("/insert_teamtask")
    @ResponseBody
    public ResultInfo insertTask(@RequestParam("task_content")String task_content){

        ResultInfo resultInfo = new ResultInfo();


        try{
            List<TeamTask> teamTask= teamTaskService.insertTeamTask(task_content);
            resultInfo.setResult(teamTask);
        }catch (ParamsException p){
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("任务添加失败");
            resultInfo.setCode(500);
            resultInfo.setMsg("任务添加失败");
        }



        return resultInfo;
    }

    @RequestMapping("/delete_teamtask")
    @ResponseBody
    public ResultInfo deleteTask(@RequestParam("task_id")int task_id,@RequestParam("task_type" ) int task_type){
        System.out.println("删除");
        ResultInfo resultInfo =new ResultInfo();
       try {
           List<TeamTask> teamTasks = teamTaskService.deleteTeamTask(task_id, task_type);
            resultInfo.setResult(teamTasks);
       }catch(ParamsException paramsException){
           resultInfo.setCode(paramsException.getCode());
           resultInfo.setMsg(paramsException.getMsg());
           paramsException.printStackTrace();

       }catch (Exception e){
           e.printStackTrace();
       }


       return resultInfo;
    }
    @RequestMapping("/update_teamtask")
    @ResponseBody
    public ResultInfo updateTask(@RequestParam("task_id")int task_id,@RequestParam("task_content") String task_content ){
        ResultInfo resultInfo = new ResultInfo();

        try {
            TeamTask teamTask = teamTaskService.updateTeamTask(task_id, task_content);
            resultInfo.setResult(teamTask);
        }catch(ParamsException paramsException){
            resultInfo.setCode(paramsException.getCode());
            resultInfo.setMsg(paramsException.getMsg());
            paramsException.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultInfo;


    }
}
