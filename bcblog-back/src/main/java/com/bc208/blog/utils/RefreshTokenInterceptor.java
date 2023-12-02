package com.bc208.blog.utils;

import cn.hutool.core.util.StrUtil;
import com.bc208.blog.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bc208.blog.utils.RedisConstants.LOGIN_USER_KEY;
import static com.bc208.blog.utils.RedisConstants.LOGIN_USER_TTL;

/**
 * @author QingheLi
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate redisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 处理跨域请求处理
        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        log.warn("第一层拦截: 用于刷新token");

        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)){
            log.warn("用户没有携带token");
            return true;
        }

        Map<Object, Object> userMap = redisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
        if (userMap.isEmpty()) {
            log.warn("token已过期");
            return true;
        }

        UserDTO userDTO = new UserDTO(Long.valueOf(userMap.get("id").toString()), userMap.get("name").toString(), userMap.get("email").toString(), userMap.get("role").toString());

        UserHolder.saveUser(userDTO);
        log.warn("第一层拦截: userDTO存入UserHolder, 刷新token");

        redisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        // 移除用户
        UserHolder.removeUser();
    }
}
