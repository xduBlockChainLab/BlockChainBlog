package com.bc208.blog.repository.base.mapper;

import com.bc208.blog.pojo.TeamTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamTasksMapper {

    List<TeamTask> queryTeamTodoList(@Param("task_type") Integer task_type );

    List<TeamTask> queryFinishedList();

    void insertTeamTask(@Param("task_content") String task_content);

    void deleteTeamTask(@Param("task_id") int task_id,@Param("task_type") int task_type);

    TeamTask findById(@Param("task_id")int task_id);

    void updateTeamTask(@Param("task_id") int task_id,@Param("task_content") String task_content);

    void changeStatus(@Param("task_id") int task_id,@Param("task_type") int task_type);

}
