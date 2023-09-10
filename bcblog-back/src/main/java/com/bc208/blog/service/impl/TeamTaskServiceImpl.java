package com.bc208.blog.service.impl;

import com.bc208.blog.repository.base.mapper.TeamTasksMapper;
import com.bc208.blog.pojo.TeamTask;
import com.bc208.blog.service.TeamTaskService;
import com.bc208.blog.utils.nullOrNot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamTaskServiceImpl implements TeamTaskService {
    @Autowired
    private TeamTasksMapper tasksMapper;

    @Override
    @Transactional
    public List<TeamTask> teamTodolist(Integer type) {
        List<TeamTask> teamTasks = tasksMapper.queryTeamTodoList(0);

        nullOrNot.isTrue(teamTasks == null, "未找到您的任务");//判断是否存在task
        return teamTasks;
    }




    @Override
    @Transactional
    public List<TeamTask> insertTeamTask(String task_content) {
        System.out.println(task_content);
        nullOrNot.isTrue(task_content == null, "内容不能为空");
        tasksMapper.insertTeamTask(task_content);
        List<TeamTask> teamTasks = tasksMapper.queryTeamTodoList(0);
        System.out.println("123" + teamTasks);
        return teamTasks;

    }


    @Override
    @Transactional
    public List<TeamTask> deleteTeamTask(int task_id, int task_type) {


        nullOrNot.isTrue(findById(task_id) == null, "该任务不存在");
        tasksMapper.deleteTeamTask(task_id, 0);

        List<TeamTask> teamTasks = tasksMapper.queryTeamTodoList(0);

        return teamTasks;

    }

    @Override
    @Transactional
    public TeamTask findById(int task_id) {

        TeamTask teamTask = tasksMapper.findById(task_id);
        return teamTask;
    }

    @Override
    @Transactional
    public TeamTask updateTeamTask(int task_id, String task_content) {
        TeamTask task = findById(task_id);
        nullOrNot.isTrue(task == null, "该任务不存在");
        tasksMapper.updateTeamTask(task_id, task_content);
        task=findById(task_id);

        return task;
    }

    @Override
    @Transactional
    public List<TeamTask> findFinished() {
        List<TeamTask> teamTasks = tasksMapper.queryFinishedList();
        return teamTasks;
    }

    @Override
    public void changeStatus(int task_id,int task_type) {
        tasksMapper.changeStatus(task_id,task_type);
    }

}
