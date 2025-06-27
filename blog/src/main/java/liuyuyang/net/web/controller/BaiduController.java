package liuyuyang.net.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import liuyuyang.net.common.utils.Result;
import liuyuyang.net.model.Baidu;
import liuyuyang.net.web.service.impl.BaiduServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/baidu")
public class BaiduController {
    @Resource
    private BaiduServiceImpl baiduService;

    // 手动刷新百度统计access token
    @PostMapping("/refresh-token")
    public Result<Baidu> refreshToken() {
        try {
            Baidu baidu = baiduService.refreshAccessToken();
            if (baidu != null) {
                return Result.success("token刷新成功", baidu);
            } else {
                return Result.error("token刷新失败");
            }
        } catch (Exception e) {
            log.error("手动刷新token失败", e);
            return Result.error("token刷新失败: " + e.getMessage());
        }
    }

    // 获取当前有效的access token
    @GetMapping("/access-token")
    public Result<String> getAccessToken() {
        try {
            String accessToken = baiduService.getValidAccessToken();
            if (accessToken != null) {
                return Result.success(accessToken, "获取token成功");
            } else {
                return Result.error("未找到有效的access token");
            }
        } catch (Exception e) {
            log.error("获取access token失败", e);
            return Result.error("获取token失败: " + e.getMessage());
        }
    }

    // 检查token状态
    @GetMapping("/token-status")
    public Result<Boolean> checkTokenStatus() {
        try {
            boolean needRefresh = baiduService.isTokenNeedRefresh();
            return Result.success(needRefresh ? "token需要刷新" : "token状态正常");
        } catch (Exception e) {
            log.error("检查token状态失败", e);
            return Result.error("检查token状态失败: " + e.getMessage());
        }
    }

    /**
     * 获取时间趋势报表数据
     *
     * @param siteId    网站ID
     * @param startDate 开始日期 (格式: 20240101)，可选，默认为当天
     * @param endDate   结束日期 (格式: 20240131)，可选，默认为当天
     */
    @GetMapping("/statis")
    public Result<JsonNode> getStatisData(
            @RequestParam String siteId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        JsonNode data = baiduService.getStatisData(siteId, startDate, endDate);
        if (data == null) return Result.error("获取数据失败");
        return Result.success("获取数据成功", data);
    }
}
