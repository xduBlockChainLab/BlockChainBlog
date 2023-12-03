// utils.js

// 将日期对象格式化为 "YYYY-MM-DD HH:mm:ss" 格式
function formatDateTime(date) {
  const year = date.getFullYear();
  const month = addZero(date.getMonth() + 1);
  const day = addZero(date.getDate());
  const hours = addZero(date.getHours());
  const minutes = addZero(date.getMinutes());
  const seconds = addZero(date.getSeconds());

  return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
}

// 在个位数前补零
function addZero(num) {
  return num < 10 ? '0' + num : num;
}

export default formatDateTime;
