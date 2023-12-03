// pages/login/login.js
import request from '../../utils/request'

Page({
  /**
   * 页面的初始数据
   */
  data: {
    form: {
      email: "",
      password: "",
      captcha: "",
      auth: "",
    },
    current: 1,
    codeText: '获取验证码',
    counting: false,
  },
  click(e) {
    let index = e.currentTarget.dataset.code;
    this.setData({
      current: index
    })
  },
  //获取验证码 
  getCode() {
    if (this.data.form.email == "") {
      wx.showToast({
        title: '请输入网站邮箱!!!',
        icon: 'error',
      })
    } else if (!this.emailCheck()) {
      wx.showToast({
        title: '请输入正确邮箱!!!',
        icon: 'error',
      })
    } else {
      var that = this;
      if (!that.data.counting) {
        wx.showToast({
          title: '请稍等',
        })
        request('/captcha', "GET", {
            email: this.data.form.email
          })
          .then(response => {
            if (response.success == true) {
              wx.showToast({
                title: '验证码已发送',
              })
              //开始倒计时60秒
              that.countDown(that, 60);
            } else {
              wx.showToast({
                title: '验证码发送失败, 请联系管理员',
              })
            }
          })
      }
    }
  },
  //倒计时60秒
  countDown(that, count) {
    if (count == 0) {
      that.setData({
        codeText: '获取验证码',
        counting: false
      })
      return;
    }
    that.setData({
      counting: true,
      codeText: count + '秒后重新获取',
    })
    setTimeout(function () {
      count--;
      that.countDown(that, count);
    }, 1000);
  },
  //输入响应函数, 将输入内容绑定到form数据库
  inputChange: function (e) {
    var field = e.currentTarget.dataset.field;
    var updatedData = {};
    updatedData['form.' + field] = e.detail.value;
    this.setData(updatedData);
  },
  //关联邮箱和微信
  wxEmailLink: function () {

    var updatedData = {};
    updatedData['form.auth'] = wx.getStorageSync("auth");
    this.setData(updatedData);

    var form = this.data.form
    request('/wxLink', "POST", form)
      .then(response => {
        if (response.success == true) {
          wx.showToast({
            title: '成功绑定邮箱',
          })
          wx.removeStorage({
            key: 'auth',
          })
        } else {
          wx.showToast({
            title: response.errorMsg,
            icon: 'error',
          })
        }
      })
    this.setData({
      current: 1
    })
    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 6000 //持续的时间
    })
    wx.login({
      success: res => {
        request('wxLogin', 'GET', {
            code: res.code
          })
          .then(response => {
            if (response.success == true) {
              wx.setStorageSync("token", response.data)
            } else {
              wx.setStorageSync("auth", response.errorMsg)
            }
          })
          .catch(error => {
            console.error('请求失败', error);
          });
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  //邮箱格式检验
  emailCheck: function () {
    // 正则表达式模式，用于验证邮箱格式
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    // 使用正则表达式测试邮箱地址
    return emailPattern.test(this.data.form.email);
  },
  // 检查用户是否登录，如果未登录则跳转到登录页
  checkLogin: function () {
    const token = wx.getStorageSync('token'); // 假设你的用户信息存储在本地缓存中
    const auth = wx.getStorageSync('auth');
    if (!token || auth) {
      wx.showToast({
        title: '用户未绑定邮箱',
        icon: 'error',
      })
      this.setData({
        current: 0
      })
      return false; // 返回 false 阻止页面跳转
    }
    wx.showToast({
      title: '用户已登录',
    })
    wx.reLaunch({
      url: '/pages/index/index',
    });
    return true; // 用户已登录，继续页面跳转
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 登录
    wx.login({
      success: function (res) {
        console.log(res)
        if (res.code) {
          request('wxLogin', 'GET', {
              code: res.code
            })
            .then(response => {
              if (response.success == true) {
                wx.setStorageSync("token", response.data)
              } else {
                wx.setStorageSync("auth", response.errorMsg)
              }
            })
            .catch(error => {
              console.error('请求失败', error);
              wx.showToast({
                icon: 'none',
                title: '系统错误, 请稍后重试.',
                duration: 5000 //持续的时间
              })
            });
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
        }
      },
      fail: function (error) {
        console.log(error)
      }
    })
    console.log("测试!!-3")
    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 5000 //持续的时间
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    wx.hideHomeButton();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {},
})