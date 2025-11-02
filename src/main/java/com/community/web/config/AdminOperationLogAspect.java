package com.community.web.config;

import com.community.domain.entity.AdminOperationLog;
import com.community.service.AdminOperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 管理员操作日志切面类
 * 拦截所有管理端接口操作并记录到数据库
 */
@Aspect
@Component
@Slf4j
public class AdminOperationLogAspect {

    @Autowired
    private AdminOperationLogService adminOperationLogService;

    /**
     * 切入点：拦截所有Controller中的方法
     */
    @Pointcut("execution(public * com.community.web.controller.*Controller.*(..))")
    public void adminOperationPointcut() {
    }

    /**
     * 方法执行成功后记录日志
     */
    @AfterReturning(pointcut = "adminOperationPointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        recordOperationLog(joinPoint, "成功", 200);
    }

    /**
     * 方法执行异常后记录日志
     */
    @AfterThrowing(pointcut = "adminOperationPointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        recordOperationLog(joinPoint, "失败", 500);
    }

    /**
     * 记录操作日志
     */
    private void recordOperationLog(JoinPoint joinPoint, String operationStatus, int responseCode) {
        try {
            // 获取请求信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return;
            }
            HttpServletRequest request = attributes.getRequest();

            // 获取当前登录的管理员ID
            Long adminId = getCurrentAdminId();
            if (adminId == null) {
                return;
            }

            // 获取操作信息
            String operationModule = getOperationModule(joinPoint);
            String operationType = getOperationType(joinPoint);
            String operationDescription = getOperationDescription(joinPoint);

            // 获取请求信息
            String requestUrl = request.getRequestURI();
            String requestMethod = request.getMethod();
            String requestIp = getClientIp(request);
            String requestParams = getRequestParams(joinPoint);

            // 创建操作日志对象
            AdminOperationLog operationLog = new AdminOperationLog();
            operationLog.setAdminId(adminId);
            operationLog.setOperationTime(LocalDateTime.now());
            operationLog.setOperationModule(operationModule);
            operationLog.setOperationType(operationType);
            operationLog.setOperationDescription(operationDescription);
            operationLog.setRequestUrl(requestUrl);
            operationLog.setRequestMethod(requestMethod);
            operationLog.setRequestIp(requestIp);
            operationLog.setRequestParams(requestParams);
            operationLog.setResponseCode(responseCode);
            operationLog.setOperationStatus(operationStatus);

            // 保存到数据库
            adminOperationLogService.save(operationLog);

            log.info("管理员操作日志已记录: 操作人={}, 模块={}, 操作={}, 状态={}", 
                    adminId, operationModule, operationType, operationStatus);
        } catch (Exception e) {
            log.error("记录管理员操作日志失败", e);
        }
    }

    /**
     * 获取当前登录的管理员ID
     */
    private Long getCurrentAdminId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                // 从SecurityContext获取管理员ID（假设使用用户名作为ID或从token中获取）
                Object principal = authentication.getPrincipal();
                if (principal instanceof String) {
                    // 如果需要真实ID，需要根据用户名查询数据库，这里简化处理
                    return null;
                }
            }
        } catch (Exception e) {
            log.debug("获取当前管理员ID失败", e);
        }
        return null;
    }

    /**
     * 获取操作模块
     */
    private String getOperationModule(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 移除Controller后缀
        return className.replace("Controller", "");
    }

    /**
     * 获取操作类型（CRUD）
     */
    private String getOperationType(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if (methodName.contains("add") || methodName.contains("save")) {
            return "新增";
        } else if (methodName.contains("update")) {
            return "修改";
        } else if (methodName.contains("delete") || methodName.contains("remove")) {
            return "删除";
        } else if (methodName.contains("query") || methodName.contains("select") || methodName.contains("get") || methodName.contains("page")) {
            return "查询";
        }
        return "其他";
    }

    /**
     * 获取操作描述
     */
    private String getOperationDescription(JoinPoint joinPoint) {
        String methodName = null;
        try {
            String className = joinPoint.getTarget().getClass().getName();
            methodName = joinPoint.getSignature().getName();

            // 反射获取方法上的@Operation注解
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Operation operation = method.getAnnotation(Operation.class);
                    if (operation != null) {
                        return operation.summary();
                    }
                }
            }
        } catch (Exception e) {
            log.debug("获取操作描述失败", e);
        }
        return methodName;
    }

    /**
     * 获取请求参数
     */
    private String getRequestParams(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                return Arrays.toString(args);
            }
        } catch (Exception e) {
            log.debug("获取请求参数失败", e);
        }
        return "";
    }

    /**
     * 获取客户端IP
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多个IP，取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
