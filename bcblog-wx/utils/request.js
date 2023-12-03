// request.js
function request(path, method, data, token) {
  const baseUrl = 'http://localhost:8088/bc208/'; // 基础 URL
  // const baseUrl = 'http://39.101.74.9:8088/bc208/'; // 基础 URL
  const url = baseUrl + path; // 拼接 URL

  return new Promise((resolve, reject) => {
    wx.request({
      url,
      method,
      data,
      header: {
        'Content-Type': 'application/json',
        'token': token
      },
      success: res => {
        resolve(res.data);
      },
      fail: error => {
        wx.showToast({
          icon: 'none',
          title: '系统错误, 请稍后重试.',
          duration: 5000//持续的时间
        })
        reject(error);
      }
    });
  });
}

export default request;
