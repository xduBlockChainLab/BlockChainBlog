Component({
  data: {
    selected: 0,
    color: "#7A7E83",
    selectedColor: "#7dd1d2",
    list: [{
      pagePath: "/pages/index/index",
      iconPath: "/static/img/HOME.png",
      selectedIconPath: "/static/img/HOME.png",
      text: "首页"
    }, {
      pagePath: "/pages/logs/logs",
      iconPath: "/static/img/TODO.png",
      selectedIconPath: "/static/img/TODO.png",
      text: "TODO_log"
    }, {
      pagePath: "/pages/todo/todo",
      iconPath: "/static/img/TODO.png",
      selectedIconPath: "/static/img/TODO.png",
      text: "TODO"
    }, {
      pagePath: "/pages/test/test",
      iconPath: "/static/img/TODO.png",
      selectedIconPath: "/static/img/TODO.png",
      text: "TODO"
    }
  ]
  },
  attached() {
  },
  methods: {
    switchTab(e) {
      const data = e.currentTarget.dataset
      const url = data.path
      wx.switchTab({url}) // 这里有个坑, 路径url, pages前不加/会"自动增加当前路径"
      getApp().globalData.selectedIndex = data.index
      // this.setData({
      //   selected: data.index
      //   // 这里的index是list数组的索引?
      // })
    }
  }, 
  ready:function() {
    this.setData({
      selected: getApp().globalData.selectedIndex
    })
  },
})