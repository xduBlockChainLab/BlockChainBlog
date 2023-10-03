//
// package com.bc208.blog.service.impl;
// import com.bc208.blog.repository.base.mapper.TasksMapper;
// import com.bc208.blog.pojo.UserTask;
// import com.bc208.blog.service.TaskService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.time.LocalDateTime;
// import java.util.List;
//
// /**
//  * @author QingheLi
//  */
// @Service
// public class TaskServiceImpl implements TaskService {
//     @Autowired
//     private TasksMapper tasksMapper;
//
//     /**
//      * 查找该用户的所有任务
//      * @param userID
//      * @param type
//      * @return
//      */
//     @Override
//     public List<UserTask> userTodolist(Integer userID, Integer type) {
//         List<UserTask> userTask = tasksMapper.queryUserTodolistById(userID, type);
//         nullOrNot.isTrue(userTask == null, "未找到您的任务");
//         //判断是否存在task
//         return userTask;
//     }
//
//     /**
//      * 查找该用户的某个任务
//      * @param task_userid
//      * @param task_id
//      * @param task_type
//      * @return
//      */
//     @Override
//     @Transactional
//     public UserTask updateTaskType(Integer task_userid, Integer task_id, Integer task_type) {
//         UserTask userTask = tasksMapper.queryTask(task_userid, task_id);
//         nullOrNot.isTrue(userTask == null, "该任务不存在");
//         if (task_type == 0) {
//             LocalDateTime localDateTime = LocalDateTime.now();
//             tasksMapper.updateTaskType(task_userid, task_id, 1, localDateTime);
//         } else if (task_type == 1) {
//             tasksMapper.updateTaskType(task_userid, task_id, 0, null);
//         }
//         return tasksMapper.queryTask(task_userid, task_id);
//     }
//
//     @Override
//     @Transactional
//     public List<UserTask> insertTask(Integer task_userid, String task_content) {
//         nullOrNot.isTrue(task_content == null, "内容不能为空");
//         tasksMapper.insertTask(task_userid, task_content);
//         return tasksMapper.queryUserTodolistById(task_userid, 0);
//     }
//
//     @Override
//     @Transactional
//     public UserTask updateTask(Integer task_userid, Integer task_id, String task_content) {
//         nullOrNot.isTrue(task_content == null, "内容不能为空");
//         tasksMapper.updateTask(task_userid, task_id, task_content);
//         UserTask userTask = tasksMapper.queryTask(task_userid, task_id);
//         return userTask;
//     }
//
//     @Override
//     @Transactional
//     public String deleteTask(Integer task_userid, Integer task_id) {
//         UserTask userTask = tasksMapper.queryTask(task_userid, task_id);
//         nullOrNot.isTrue(userTask == null, "该任务不存在");
//         tasksMapper.deleteTask(task_userid, task_id);
//         return "删除成功";
//     }
// }
//
//
//
