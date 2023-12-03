import request from '../../utils/request'
import formatDateTime from '../../utils/date2LocalDate'
import createLocalDateTime from '../../utils/DateTime2LocalDate'

Page({
  data: {
    list: [],
    finishedList: [],
    // splicList: [],
    showTodoExch: false,
    showTodoCreate: false,
    currentTask: {},
    // taskIdCounter: 0 // 生成唯一taskId
    importancesArrayIndex: 0,
    importancesPickerArray: [1, 2, 3, 4], // 任务重要程度选择列表
    // ------------------------新任务属性
    newTask: {
      newTaskName: "",
      newTaskImportance: 1,
      newTaskBeginDate: "",
      newTaskBeginTime: "",
      newTaskEndDate: "",
      newTaskEndTime: "",
      newTaskRemind: false,
      newTaskRemindDate: "",
      newTaskRemindTime: "",
      newTaskDesc: ""
    },
    taskDTO: {
      taskName: "",
      beginTime: "",
      endTime: "",
      importance: 1,
      remind: 0,
      remindTime: "",
      taskDesc: ""
    }
  },
  onLoad: function () {
    this.getTaskNoDone()
    this.getTaskDone()
  },
  getTaskNoDone: function () {
    request('getTasks', 'GET', null, wx.getStorageSync("token"))
      .then(response => {
        console.log(response)
        this.data.list = response.data
        this.setData({
          list: this.data.list
        })
      })
  },
  getTaskDone: function () {
    request('getTasksDone', 'GET', null, wx.getStorageSync("token"))
      .then(response => {
        console.log(response)
        this.data.finishedList = response.data
        this.setData({
          finishedList: this.data.finishedList
        })
      })
  },
  exchTodoNoDone: function (event) {
    console.log(this.data.list[event.currentTarget.dataset.index])
    console.log("test1")
    let [begin_date, begin_time] = this.data.list[event.currentTarget.dataset.index].beginTime.split("T")
    let end_date = ''
    let end_Time = ''
    if(this.data.list[event.currentTarget.dataset.index].endTime != null){
      [end_date, end_Time] = this.data.list[event.currentTarget.dataset.index].endTime.split("T")
    }
    let remind_date = '' 
    let remind_time = ''
    if(this.data.list[event.currentTarget.dataset.index].remindTime != null){
      [remind_date, remind_time] = this.data.list[event.currentTarget.dataset.index].remindTime.split("T")
    }
    //test
    this.setData({
      // currentTask: event.detail,获取展示任务详细信息
      showTodoExch: true,
      currentTask: this.data.list[event.currentTarget.dataset.index],
      'currentTask.begin_Date': begin_date,
      'currentTask.begin_Time': begin_time,
      'currentTask.end_Date': end_date,
      'currentTask.end_Time': end_Time,
      'currentTask.remind_Date': remind_date,
      'currentTask.remind_Time': remind_time,
    });
  },  
  exchTodoDone: function (event) {
    console.log(this.data.finishedList[event.currentTarget.dataset.index])
    console.log("test2")
    let [begin_date, begin_time] = this.data.finishedList[event.currentTarget.dataset.index].beginTime.split("T")
    let end_date = ''
    let end_Time = ''
    if(this.data.finishedList[event.currentTarget.dataset.index].endTime != null){
      [end_date, end_Time] = this.data.finishedList[event.currentTarget.dataset.index].endTime.split("T")
    }
    let remind_date = '' 
    let remind_time = ''
    if(this.data.finishedList[event.currentTarget.dataset.index].remindTime != null){
      [remind_date, remind_time] = this.data.finishedList[event.currentTarget.dataset.index].remindTime.split("T")
    }
    //test
    this.setData({
      // currentTask: event.detail,获取展示任务详细信息
      showTodoExch: true,
      currentTask: this.data.finishedList[event.currentTarget.dataset.index],
      'currentTask.begin_Date': begin_date,
      'currentTask.begin_Time': begin_time,
      'currentTask.end_Date': end_date,
      'currentTask.end_Time': end_Time,
      'currentTask.remind_Date': remind_date,
      'currentTask.remind_Time': remind_time,
    });
  },
  // 关闭弹窗事件
  hideExchTodo: function () {
    this.setData({
      showTodoExch: false,
      currentTask: {}
    });
  },
  hideCreateTodo: function () {
    this.setData({
      showTodoCreate: false,
      newTask: { // 将newTask整体置为空对象
        newTaskName: '',
        newTaskImportance: 1,
        newTaskBeginDate: '',
        newTaskBeginTime: '',
        newTaskEndDate: '',
        newTaskEndTime: '',
        newTaskRemind: false,
        newTaskRemindDate: '',
        newTaskRemindTime: '',
        newTaskDesc: ''
      }
    });
  },
  // 任务修改表单提交事件
  formSubmit: function (event) {
    const updatedTaskInfo = event.detail.value; // 获取表单中修改后的任务信息
    // 更新任务信息到数据中
    this.updateTaskInfo(updatedTaskInfo);
    // 关闭弹窗
    this.hideModal();
  },

  addToDo: function (event) {
    this.setData({
      // currentTask: event.detail, 展示任务创建弹窗
      showTodoCreate: true,
    });
  },

  ToFinished(e) {
    var checkIndex = e.currentTarget.dataset.index
    // this.data.list[checkIndex].taskDone = true;

    // this.data.finishedList.push(this.data.list[checkIndex]);

    // this.data.list.splice(checkIndex, 1)
    // this.setData({
    //   list: this.data.list,
    //   finishedList: this.data.finishedList
    // })
    request('taskDoneChange', 'GET', {
        taskId : this.data.list[checkIndex].taskId
      }, wx.getStorageSync("token"))
      .then(response => {
        if (response.success == true) {
          console.log(response.errorMsg)
          this.getTaskDone()
          this.getTaskNoDone()
        } else {
          console.log(response.errorMsg)
        }
      })
  },
  delNoDone(e) {
    var index = e.currentTarget.dataset.index
    console.log("list所索引:" + index);
    console.log(this.data.list[index].taskId)
    this.sureDel(this.data.list[index].taskId,this.data.list[index].taskName)
  },
  sureDel: function (delTaskId, taskName) {
    var that = this; // 保存 Page 实例的引用
    wx.showModal({
      title: '任务删除',
      content: '是否确认删除<'+taskName+'>?',
      showCancel: true, // 是否显示取消按钮
      cancelText: '取消', // 取消按钮的文字，默认为"取消"
      cancelColor: '#000000', // 取消按钮的文字颜色，默认为"#000000"
      confirmText: '确定', // 确定按钮的文字，默认为"确定"
      confirmColor: '#3CC51F', // 确定按钮的文字颜色，默认为"#3CC51F"
      success: function (res) {
        if (res.confirm) {
          // 用户点击了确定
          console.log('用户点击确定'+delTaskId);
          request('taskDelete', 'GET', {
              taskId : delTaskId
            }, wx.getStorageSync("token"))
            .then(response => {
              if (response.success == true) {
                wx.showToast({
                  title: '任务删除成功',
                })
                that.getTaskDone()
                that.getTaskNoDone()
              } else {
                wx.showToast({
                  title: '任务删除失败',
                })
              }
            })
        } else if (res.cancel) {
          // 用户点击了取消
          console.log('用户点击取消');
        }
      }
    });
  },
  delDone(e) {
    var index = e.currentTarget.dataset.index
    // console.log(index);
    // this.data.finishedList.splice(index, 1);
    // this.setData({
    //   finishedList: this.data.finishedList
    // })
    console.log("list所索引:" + index);
    console.log(this.data.finishedList[index].taskId)
    this.sureDel(this.data.finishedList[index].taskId,this.data.finishedList[index].taskName)
  },
  ToPendding(e) {
    var checkIndex = e.currentTarget.dataset.index
    // // 修改任务状态为未完成
    // this.data.finishedList[checkIndex].taskDone = false;

    // // 将未完成的任务加入到未完成列表
    // this.data.list.push(this.data.finishedList[checkIndex]);

    // // 从已完成列表中移除该任务
    // this.data.finishedList.splice(checkIndex, 1);

    // this.setData({
    //   list: this.data.list,
    //   finishedList: this.data.finishedList
    // });
    request('taskDoneChange', 'GET', {
      taskId : this.data.finishedList[checkIndex].taskId
    }, wx.getStorageSync("token"))
    .then(response => {
      if (response.success == true) {
        console.log(response.errorMsg)
        this.getTaskDone()
        this.getTaskNoDone()
      } else {
        console.log(response.errorMsg)
      }
    })
  },
  exchImportPicker: function (e) {
    this.setData({
      'currentTask.importance': this.data.importancesPickerArray[e.detail.value],
    })
  },
  importPicker: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'newTask.newTaskImportance': this.data.importancesPickerArray[e.detail.value]
    })
    console.log(this.data.newTask.newTaskImportance)
  },
  beginDatePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'newTask.newTaskBeginDate': e.detail.value
    })
    console.log('newTaskBeginDate:' + this.data.newTask.newTaskBeginDate)
  },
  exchBeginDatePicker: function (e) {
    this.setData({
      'currentTask.begin_Date': e.detail.value
    })
  },
  beginTimePicker: function (e) {
    this.setData({
      'newTask.newTaskBeginTime': e.detail.value
    })
    console.log('newTaskBeginTime:' + this.data.newTask.newTaskBeginTime)
  },
  exchBeginTimePicker: function (e) {
    this.setData({
      'currentTask.begin_Time': e.detail.value
    })
  },
  endDatePicker: function (e) {
    this.setData({
      'newTask.newTaskEndDate': e.detail.value
    })
    console.log('newTaskEndDate:' + this.data.newTask.newTaskEndDate)
  },
  exchEndDatePicker: function (e) {
    this.setData({
      'currentTask.end_Date': e.detail.value
    })
  },
  endTimePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'newTask.newTaskEndTime': e.detail.value
    })
    console.log('newTaskEndTime:' + this.data.newTask.newTaskEndTime)
  },
  exchEndTimePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'currentTask.end_Time': e.detail.value
    })
  },
  openRemind: function (e) {
    this.setData({
      'newTask.newTaskRemind': e.detail.value
    })
  },
  exchRemind: function (e) {
    if(e.detail.value == true){
      this.setData({
        'currentTask.remind': 1
      })
    }else{
      this.setData({
        'currentTask.remind': 0
      })
    }
      
  },
  remindDatePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'newTask.newTaskRemindDate': e.detail.value
    })
  },
  exchRemindDatePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'currentTask.remind_Date': e.detail.value
    })
  },
  remindTimePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'newTask.newTaskRemindTime': e.detail.value
    })
  },
  exchRemindTimePicker: function (e) {
    // console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      'currentTask.remind_Time': e.detail.value
    })
  },
  newTaskDesc: function (e) {
    this.setData({
      'newTask.newTaskDesc': e.detail.value
    })
  },
  exchTaskDesc: function (e) {
    this.setData({
      'currentTask.taskDesc': e.detail.value
    })
  },
  CreateTask: function (e) {
    console.log("新任务名字:" + this.data.newTask.newTaskName)
    this.data.taskDTO.taskName = this.data.newTask.newTaskName
    this.data.taskDTO.importance = this.data.newTask.newTaskImportance
    if (this.data.newTask.newTaskRemind == 1) {
      this.data.taskDTO.remind = 1
    }
    this.data.taskDTO.taskDesc = this.data.newTask.newTaskDesc
    const currentDate = new Date();
    if (this.data.newTask.newTaskBeginTime == '') {
      this.data.taskDTO.beginTime = formatDateTime(currentDate);
    } else {
      this.data.taskDTO.beginTime = createLocalDateTime(this.data.newTask.newTaskBeginDate, this.data.newTask.newTaskBeginTime)
    }
    if (this.data.newTask.newTaskEndTime != '') {
      this.data.taskDTO.endTime = createLocalDateTime(this.data.newTask.newTaskEndDate, this.data.newTask.newTaskEndTime)
    }
    if (this.data.newTask.newTaskRemind == true) {
      this.data.taskDTO.remindTime = createLocalDateTime(this.data.newTask.newTaskRemindDate, this.data.newTask.newTaskRemindTime)
    }
    console.log('提交的任务对象为:')
    console.log(this.data.taskDTO)
    request('/taskAdd', "POST", this.data.taskDTO, wx.getStorageSync("token"))
      .then(response => {
        if (response.success == true) {
          wx.showToast({
            title: '任务添加成功',
            duration: 2000 //持续的时间
          })
          this.getTaskDone()
          this.getTaskNoDone()
        } else {
          wx.showToast({
            title: '任务添加失败',
          })
        }
      })
  },
  newTaskNameAdd: function (e) {
    console.log("数据修改:" + e.detail.value)
    this.setData({
      'newTask.newTaskName': e.detail.value
    })
    console.log("新任务名字为:" + this.data.newTask.newTaskName)
  },
  exchTaskNameAdd: function (e) {
    console.log("数据修改:" + e.detail.value)
    this.setData({
      'currentTask.taskName': e.detail.value
    })
  },
  exchTask: function(e){
    console.log(this.data.currentTask);
    const beginTime = createLocalDateTime(this.data.currentTask.begin_Date, this.data.currentTask.begin_Time)
    const endTime = createLocalDateTime(this.data.currentTask.end_Date, this.data.currentTask.end_Time)
    let remindTime = ''
    if(this.data.currentTask.remind == true){
      remindTime = createLocalDateTime(this.data.currentTask.remind_Date, this.data.currentTask.remind_Time)
    }
    request('taskUpdate', 'POST', {
      taskId: this.data.currentTask.taskId,
      taskName: this.data.currentTask.taskName,
      beginTime: beginTime,
      endTime: endTime,
      importance: this.data.currentTask.importance,
      remind: this.data.currentTask.remind,
      remindTime: remindTime,
      taskDesc: this.data.currentTask.taskDesc,
      taskDone: this.data.currentTask.taskDone
    }, wx.getStorageSync("token"))
      .then(response => {
        if (response.success == true) {
          wx.showToast({
            title: '任务修改成功',
          })
        } else {
          wx.showToast({
            title: '任务修改失败',
          })
        }
      })
    this.setData({
      showTodoExch: false,
      currentTask: {}
    })
    this.getTaskDone()
    this.getTaskNoDone()
  }
  // testPush
})