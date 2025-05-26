package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Role;
import top.luoyuanxiang.api.entity.User;
import top.luoyuanxiang.api.service.IRoleService;
import top.luoyuanxiang.api.service.IUserService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.user.EditPassDTO;
import top.luoyuanxiang.api.vo.user.UserDTO;

import java.util.List;

/**
 * 用户管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;

    /**
     * 新增用户
     *
     * @param user 用户
     * @return {@link Result }<{@link ? }>
     */
    @PremName("user:add")
    @PostMapping
    public Result<?> add(@RequestBody UserDTO user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 删除用户
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("user:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        userService.del(id);
        return Result.success();
    }

    /**
     * 批量删除用户
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("user:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 编辑用户
     *
     * @param user 用户
     * @return {@link Result }<{@link ? }>
     */
    @PremName("user:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 获取用户
     *
     * @param id 身份证
     * @return {@link Result }<{@link User }>
     */
    @PremName("user:info")
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Integer id) {
        User data = userService.get(id);
        return Result.success(data);
    }

    /**
     * 获取用户列表
     *
     * @return {@link Result }<{@link List }<{@link User }>>
     */
    @PremName("user:list")
    @PostMapping("/list")
    public Result<List<User>> list() {
        List<User> list = userService.list();
        list.parallelStream().forEach(s -> {
            s.setPassword("只有聪明的人才能看到密码");
            Role role = roleService.getById(s.getRoleId());
            s.setRole(role);
        });
        return Result.success(list);
    }

    /**
     * 分页查询用户列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link User }>>
     */
    @PremName("user:list")
    @PostMapping("/paging")
    public Result<Page<User>> paging(Page<User> page) {
        Page<User> data = userService.page(page);
        data.getRecords().parallelStream().forEach(s -> {
            s.setPassword("只有聪明的人才能看到密码");
            Role role = roleService.getById(s.getRoleId());
            s.setRole(role);
        });
        return Result.success(data);
    }

    /**
     * 修改用户密码
     *
     * @param data 数据
     * @return {@link Result }<{@link String }>
     */
    @PremName("user:pass")
    @PatchMapping("/pass")
    public Result<String> editPass(@RequestBody EditPassDTO data) {
        userService.editPass(data);
        return Result.success("密码修改成功");
    }

    /**
     * 校验当前用户Token是否有效
     *
     * @param token 令 牌
     * @return {@link Result }<{@link ? }>
     */
    @GetMapping("/check")
    public Result<?> checkPrem(String token) {
        return Result.success();
    }

    /**
     * 获取作者信息
     *
     * @return {@link Result }<{@link User }>
     */
    @GetMapping("/author")
    public Result<User> getAuthor() {
        User data = userService.get(1);
        return Result.success(data);
    }

}
