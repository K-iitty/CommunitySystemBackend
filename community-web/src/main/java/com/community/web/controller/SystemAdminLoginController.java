package com.community.web.controller;

import com.community.common.Result;
import com.community.common.util.JwtUtil;
import com.community.domain.entity.SystemAdmin;
import com.community.service.SystemAdminLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/systemAdmin")
@Tag(name = "系统管理员登录管理", description = "系统管理员登录相关接口")
@ApiSupport(order = 25, author = "社区管理系统开发团队")
public class SystemAdminLoginController {

    @Autowired
    private SystemAdminLoginService systemAdminLoginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "系统管理员登录", description = "系统管理员登录认证")
    @ApiOperationSupport(order = 1, author = "开发团队")
    public Result login(@Parameter(description = "用户名") @RequestParam String username,
                        @Parameter(description = "密码") @RequestParam String password) {
        try {
            SystemAdmin admin = systemAdminLoginService.login(username, password, passwordEncoder);
            if (admin != null) {
                // 生成JWT Token（不再使用Session）
                String token = jwtUtil.generateToken(admin.getUsername());

                // 创建不包含敏感信息的响应对象
                SystemAdmin safeAdmin = new SystemAdmin();
                safeAdmin.setId(admin.getId());
                safeAdmin.setUsername(admin.getUsername());
                safeAdmin.setEmail(admin.getEmail());
                safeAdmin.setRealName(admin.getRealName());
                safeAdmin.setRoleType(admin.getRoleType());
                safeAdmin.setStatus(admin.getStatus());

                return Result.ok("登录成功")
                        .put("data", safeAdmin)
                        .put("token", token)
                        .put("tokenType", "Bearer");
            } else {
                return Result.error("用户名或密码错误");
            }
        } catch (Exception e) {
            return Result.error("登录失败: " + e.getMessage());
        }
    }

    // 移除Session相关的info接口，改为JWT方式
    @GetMapping("/info")
    @Operation(summary = "获取当前登录管理员信息", description = "通过JWT Token获取管理员信息")
    @ApiOperationSupport(order = 4, author = "开发团队")
    public Result getInfo() {
        try {
            // 从SecurityContext中获取当前用户
            org.springframework.security.core.Authentication auth =
                    org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
                String username = auth.getName();
                SystemAdmin admin = systemAdminLoginService.getAdminByUsername(username);
                if (admin != null) {
                    // 创建安全的管理员信息（不包含敏感信息）
                    SystemAdmin safeAdmin = new SystemAdmin();
                    safeAdmin.setId(admin.getId());
                    safeAdmin.setUsername(admin.getUsername());
                    safeAdmin.setEmail(admin.getEmail());
                    safeAdmin.setRealName(admin.getRealName());
                    safeAdmin.setRoleType(admin.getRoleType());
                    safeAdmin.setStatus(admin.getStatus());

                    return Result.ok().put("data", safeAdmin);
                }
            }
            return Result.error("未登录或Token无效");
        } catch (Exception e) {
            return Result.error("获取用户信息失败: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "系统管理员退出登录", description = "系统管理员退出登录")
    @ApiOperationSupport(order = 2, author = "开发团队")
    public Result logout() {
        // JWT是无状态的，客户端直接丢弃token即可
        return Result.ok("退出成功");
    }
    
    /**
     * 系统管理员注册
     */
    @PostMapping("/register")
    @Operation(summary = "系统管理员注册", description = "系统管理员注册新账户")
    @ApiOperationSupport(order = 3, author = "开发团队")
    public Result register(@Parameter(description = "管理员信息") @RequestBody SystemAdmin admin) {
        try {
            // 简单验证必填字段
            if (admin.getUsername() == null || admin.getUsername().isEmpty()) {
                return Result.error("用户名不能为空");
            }
            if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
                return Result.error("密码不能为空");
            }
            
            boolean result = systemAdminLoginService.register(admin, passwordEncoder);
            if (result) {
                return Result.ok("注册成功");
            } else {
                return Result.error("注册失败，用户名可能已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("注册失败: " + e.getMessage());
        }
    }

}