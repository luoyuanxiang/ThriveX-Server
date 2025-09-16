package top.luoyuanxiang.thrivex.server.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.luoyuanxiang.thrivex.server.service.IStatisService;
import top.luoyuanxiang.thrivex.server.vo.Result;

/**
 * 数据统计管理
 *
 * @author luoyuanxiang
 */
@Slf4j
@RestController
@RequestMapping("/statis")
public class StatisController {

    @Resource
    private IStatisService statisService;

    /**
     * 获取网站统计数据
     *
     * @param type      统计类型：basic(基础数据), overview(概览趋势), new-visitor(新访客趋势), basic-overview(基础概览趋势)
     * @param startDate 开始日期 (格式: 20240101)，可选，默认为当天
     * @param endDate   结束日期 (格式: 20240131)，可选，默认为当天
     * @return {@link Result }<{@link JsonNode }>
     */
    @GetMapping
    public Result<JsonNode> getStatisData(
            @RequestParam String type,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        try {
            JsonNode data;
            String successMsg;

            switch (type.toLowerCase()) {
                case "basic":
                    data = statisService.getStatisData(startDate, endDate);
                    successMsg = "获取基础统计数据成功";
                    break;
                case "overview":
                    data = statisService.getOverviewTimeTrend(startDate, endDate);
                    successMsg = "获取概览时间趋势报表成功";
                    break;
                case "new-visitor":
                    data = statisService.getNewVisitorTrend(startDate, endDate);
                    successMsg = "获取新访客趋势报表成功";
                    break;
                case "basic-overview":
                    System.out.println("basic-overview");
                    System.out.println("startDate: " + startDate);
                    System.out.println("endDate: " + endDate);
                    data = statisService.getBasicOverviewTrend(startDate, endDate);
                    successMsg = "获取基础概览时间趋势报表成功";
                    break;
                default:
                    return Result.error("不支持的统计类型: " + type + "。支持的类型: basic, overview, new-visitor, basic-overview");
            }

            if (data == null) {
                return Result.error(600, "获取" + type + "类型统计数据失败");
            }

            return Result.success(successMsg, data);

        } catch (Exception e) {
            log.error("获取{}类型统计数据失败", type, e);
            return Result.error(600, "获取" + type + "类型统计数据失败: " + e.getMessage());
        }
    }
}
