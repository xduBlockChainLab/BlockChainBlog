// request.js
function request(path, method, data) {
  const baseUrl = 'http://localhost:8088/bc208/'; // 基础 URL
  const url = baseUrl + path; // 拼接 URL

  return new Promise((resolve, reject) => {
    wx.request({
      url,
      method,
      data,
      header: {
        'Content-Type': 'application/json',
        // 'Authorization': 'Bearer your_access_token', // 设置统一的请求头部信息
        // 添加其他请求头信息
      },
      success: res => {
        resolve(res.data);
      },
      fail: error => {
        reject(error);
      }
    });
  });
}

export default request;
