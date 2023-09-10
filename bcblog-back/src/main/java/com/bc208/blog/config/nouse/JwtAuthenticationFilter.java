//package com.bc208.blog.config.auth;
//
//import com.bc208.blog.common.vo.SecurityUser;
//import com.bc208.blog.pojo.User;
//import com.bc208.blog.service.impl.UsersServiceImpl;
//import com.bc208.blog.utils.JwtUtil;
//import com.bc208.blog.utils.ResultInfo;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Slf4j
//public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
//
//    @Resource
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private JwtUtil jwtUtils;
//
//    @Autowired
//    private UsersServiceImpl userDetailsService;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String jwt = request.getHeader(jwtUtils.getHeader());
//        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
//        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
//        if (jwt == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String authorization = request.getHeader("Authorization");
//        //如果Authorization为空，那么不允许用户访问，直接返回
//        if (!StringUtils.hasText(authorization)) {
//            printFront(response, "没有登录！");
//            return;
//        }
//
//        //Authorization 去掉头部的Bearer 信息，获取token值
//        String jwtToken = authorization.replace("Bearer ", "");
//        //验签
//        boolean verifyTokenResult = jwtUtils.verifyToken(jwtToken);
//        //验签不成功
//        if (!verifyTokenResult) {
//            printFront(response, "jwtToken 已过期");
//            return;
//        }
//
//        String username = jwtUtils.getUserNameFromToken(jwtToken);
//        //从payload中获取授权列表
//        User user = userDetailsService.getByUserEmail(username);
//        // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, new SecurityUser(user).getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(token);
//
//        chain.doFilter(request, response);
//
//    }
//
//    private void printFront(HttpServletResponse response, String message) throws IOException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        ResultInfo httpResult = new ResultInfo();
//        httpResult.setCode(-1);
//        httpResult.setMsg(message);
//        writer.print(objectMapper.writeValueAsString(httpResult));
//        writer.flush();
//    }
//
//}
