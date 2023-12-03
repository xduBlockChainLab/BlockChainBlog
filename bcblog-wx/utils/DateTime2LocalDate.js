function createLocalDateTime(date, time) {
  // 构建符合 LocalDateTime 格式的字符串
  const localDateTimeString = `${date}T${time}`;
  return localDateTimeString;
}

export default createLocalDateTime;