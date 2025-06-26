package liuyuyang.net.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liuyuyang.net.common.utils.Result;
import liuyuyang.net.web.service.OauthService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "第三方登录")
@RestController
@RequestMapping("/oauth")
@Transactional
public class OauthController {
    @Resource
    private OauthService oauthService;

    @ApiOperation("授权登录")
    @PostMapping("/github/login")
    public Result authGithubLogin(@RequestParam String code) {
        Map<String, Object> result = oauthService.authGithubLogin(code);
        return Result.success(result);
    }

    @ApiOperation("绑定第三方登录")
    @PostMapping("/github/bind")
    public Result bindGithubLogin(@RequestParam String code, @RequestParam String userId) {
        oauthService.bindGithubLogin(code, userId);
        return Result.success("绑定成功");
    }
}
