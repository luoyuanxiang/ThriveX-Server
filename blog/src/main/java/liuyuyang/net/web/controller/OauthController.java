package liuyuyang.net.web.controller;

import io.swagger.annotations.Api;
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

    @GetMapping("/github/{code}")
    public Result githubLogin(@PathVariable String code) {
        Map<String, Object> result = oauthService.githubLogin(code);
        return Result.success(result);
    }
}
