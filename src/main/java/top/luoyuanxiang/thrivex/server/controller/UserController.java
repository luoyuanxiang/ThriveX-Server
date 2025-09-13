//package top.luoyuanxiang.thrivex.server.controller;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//import top.luoyuanxiang.thrivex.server.vo.Paging;
//import top.luoyuanxiang.thrivex.server.vo.Result;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 用户管理
// *
// * @author luoyuanxiang
// * @since 2025-09-12
// */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Resource
//    private UserService userService;
//
//    @PremName("user:add")
//    @PostMapping
//    @ApiOperation("新增用户")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
//    public Result<String> add(@RequestBody UserDTO user) {
//        userService.add(user);
//        return Result.success();
//    }
//
//    @PremName("user:del")
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除用户")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
//    public Result<String> del(@PathVariable Integer id) {
//        userService.del(id);
//        return Result.success();
//    }
//
//    @PremName("user:del")
//    @DeleteMapping("/batch")
//    @ApiOperation("批量删除用户")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
//    public Result<String> batchDel(@RequestBody List<Integer> ids) {
//        userService.delBatch(ids);
//        return Result.success();
//    }
//
//    @PremName("user:edit")
//    @PatchMapping
//    @ApiOperation("编辑用户")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
//    public Result<String> edit(@RequestBody UserInfoDTO user) {
//        userService.edit(user);
//        return Result.success();
//    }
//
//    @PremName("user:info")
//    @GetMapping("/{id}")
//    @ApiOperation("获取用户")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 5)
//    public Result<User> get(@PathVariable Integer id) {
//        User data = userService.get(id);
//        return Result.success(data);
//    }
//
//    @PremName("user:list")
//    @PostMapping("/list")
//    @ApiOperation("获取用户列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 6)
//    public Result<List<User>> list(@RequestBody UserFilterVo filterVo) {
//        List<User> list = userService.list(filterVo);
//        return Result.success(list);
//    }
//
//    @PremName("user:list")
//    @PostMapping("/paging")
//    @ApiOperation("分页查询用户列表")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 7)
//    public Result paging(UserFilterVo filterVo, PageVo pageVo) {
//        Page<User> data = userService.paging(filterVo, pageVo);
//        Map<String, Object> result = Paging.filter(data);
//        return Result.success(result);
//    }
//
//    @PostMapping("/login")
//    @ApiOperation("用户登录")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 8)
//    public Result<Map> login(@RequestBody UserLoginDTO user) {
//        Map<String, Object> result = userService.login(user);
//        return Result.success("登录成功", result);
//    }
//
//    @PremName("user:pass")
//    @PatchMapping("/pass")
//    @ApiOperation("修改用户密码")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 9)
//    public Result<String> editPass(@RequestBody EditPassDTO data) {
//        userService.editPass(data);
//        return Result.success("密码修改成功");
//    }
//
//    @GetMapping("/check")
//    @ApiOperation("校验当前用户Token是否有效")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 10)
//    public Result checkPrem(String token) {
//        userService.check(token);
//        return Result.success();
//    }
//
//    @GetMapping("/author")
//    @ApiOperation("获取作者信息")
//    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 11)
//    public Result<User> getAuthor() {
//        User data = userService.get(1);
//        return Result.success(data);
//    }
//
//}
