package liuyuyang.net.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import liuyuyang.net.common.annotation.CheckRole;
import liuyuyang.net.common.utils.Result;
import liuyuyang.net.model.Baidu;
import liuyuyang.net.web.service.impl.BaiduServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "数据统计管理")
@Slf4j
@RestController
@RequestMapping("/baidu")
@CheckRole
public class BaiduController {
    @Resource
    private BaiduServiceImpl baiduService;

    // 手动刷新百度统计access token
    @PostMapping("/refresh-token")
    @ApiOperation("刷新 Token")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 1)
    public Result<Baidu> refreshToken() {
        Baidu baidu = baiduService.refreshAccessToken();
        return Result.success("token刷新成功", baidu);
    }

    // 获取当前有效的access token
    @GetMapping("/access-token")
    @ApiOperation("获取有效的 Token")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 2)
    public Result<String> getAccessToken() {
        String accessToken = baiduService.getValidAccessToken();
        return Result.success(accessToken, "获取token成功");
    }

    // 检查token状态
    @GetMapping("/token-status")
    @ApiOperation("检查 Token 状态")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 3)
    public Result<Boolean> checkTokenStatus() {
        boolean needRefresh = baiduService.isTokenNeedRefresh();
        return Result.success(needRefresh ? "token需要刷新" : "token状态正常");
    }

    /**
     * 获取时间趋势报表数据
     *
     * @param siteId    网站ID
     * @param startDate 开始日期 (格式: 20240101)，可选，默认为当天
     * @param endDate   结束日期 (格式: 20240131)，可选，默认为当天
     */
    @GetMapping("/statis")
    @ApiOperation("获取统计数据")
    @ApiOperationSupport(author = "刘宇阳 | liuyuyang1024@yeah.net", order = 4)
    public Result<JsonNode> getStatisData(
            @ApiParam(value = "网站ID", required = true) @RequestParam String siteId,
            @ApiParam(value = "开始日期，格式: 20240101，可选，默认为当天") @RequestParam(required = false) String startDate,
            @ApiParam(value = "结束日期，格式: 20240131，可选，默认为当天") @RequestParam(required = false) String endDate
    ) {
        JsonNode data = baiduService.getStatisData(siteId, startDate, endDate);
        if (data == null) return Result.error("获取数据失败");
        return Result.success("获取数据成功", data);
    }
}
