package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.service.IUserService;
import top.luoyuanxiang.thrivex.server.vo.EditPassVO;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;
import top.luoyuanxiang.thrivex.server.vo.UserQueryVO;

import java.util.List;

/**
 * 用户管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 新增用户
     *
     * @param user 用户
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("user:add")
    @PostMapping
    public Result<String> add(@RequestBody UserEntity user) {
        boolean exists = userService.lambdaQuery()
                .eq(UserEntity::getUsername, user.getUsername())
                .exists();
        if (exists) {
            return Result.error("用户已存在: " + user.getUsername());
        }
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.insert();
        return Result.success();
    }

    /**
     * 删除用户
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("user:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除用户
     *
     * @param ids 身份证
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("user:del")
    @DeleteMapping("/batch")
    public Result<String> batchDel(@RequestBody List<Integer> ids) {
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 编辑用户
     *
     * @param user 用户
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("user:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody UserEntity user) {
        user.updateById();
        return Result.success();
    }

    /**
     * 获取用户
     *
     * @param id id
     * @return {@link Result }<{@link UserEntity }>
     */
    @HasPermission("user:info")
    @GetMapping("/{id}")
    public Result<UserEntity> get(@PathVariable Integer id) {
        UserQueryVO userQueryVO = new UserQueryVO();
        userQueryVO.setId(id);
        List<UserEntity> list = userService.list(userQueryVO);
        if (list.isEmpty()) return Result.success(null);
        UserEntity data = list.get(0);
        return Result.success(data);
    }

    /**
     * 获取用户列表
     *
     * @param queryVO 过滤 VO
     * @return {@link Result }<{@link List }<{@link UserEntity }>>
     */
    @HasPermission("user:list")
    @PostMapping("/list")
    public Result<List<UserEntity>> list(@RequestBody UserQueryVO queryVO) {
        List<UserEntity> list = userService.list(queryVO);
        return Result.success(list);
    }

    /**
     * 分页查询用户列表
     *
     * @param filterVo 过滤 VO
     * @param page     页
     * @param size     大小
     * @return {@link Result }<{@link Paging }<{@link UserEntity }>>
     */
    @HasPermission("user:list")
    @PostMapping("/paging")
    public Result<Paging<UserEntity>> paging(UserQueryVO filterVo,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size) {
        Page<UserEntity> data = userService.paging(new Page<>(page, size), filterVo);
        return Result.page(data);
    }

    /**
     * 修改用户密码
     *
     * @param data 数据
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("user:pass")
    @PatchMapping("/pass")
    public Result<String> editPass(@RequestBody EditPassVO data) {
        userService.editPass(data);
        return Result.success("密码修改成功");
    }

    /**
     * 获取作者信息
     *
     * @return {@link Result }<{@link UserEntity }>
     */
    @NoAuth
    @GetMapping("/author")
    public Result<UserEntity> getAuthor() {
        UserQueryVO userQueryVO = new UserQueryVO();
        userQueryVO.setId(1);
        List<UserEntity> list = userService.list(userQueryVO);
        if (list.isEmpty()) return Result.success(null);
        UserEntity data = list.get(0);
        return Result.success(data);
    }

}
