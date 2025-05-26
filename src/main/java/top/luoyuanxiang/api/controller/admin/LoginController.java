package top.luoyuanxiang.api.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.api.service.IUserService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.req.LoginReqVO;
import top.luoyuanxiang.api.vo.resp.LoginRespVO;

/**
 * 登录
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private IUserService userService;

    /**
     * 登录
     *
     * @param vo VO
     * @return {@link Result }<{@link ? }>
     */
    @PostMapping("/admin/user/login")
    public Result<LoginRespVO> login(@RequestBody LoginReqVO vo) {
        LoginRespVO loginRespVO = userService.login(vo);
        return Result.success(loginRespVO);
    }
}
