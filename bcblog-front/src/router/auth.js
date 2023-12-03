export function isAuthenticated() {
    // 从localStorage中获取令牌
    const token = localStorage.getItem("token");

    // 如果令牌存在且未过期，则返回true
    if (token) {
        return true;
    } else {
        return false;
    }
}
