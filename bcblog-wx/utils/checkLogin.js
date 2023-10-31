// checkLogin.js

// 检查用户是否登录，如果未登录则跳转到登录页
function checkLogin() {
  const token = wx.getStorageSync('token');
  if (!token) {
    console.log("用户未登录");
    wx.reLaunch({
      url: '/pages/login/login',
    });
    return false;
  }
  console.log("用户已登录");
  return true;
}

// 导出 checkLogin 函数，使其可在其他文件中引用
export default checkLogin;
