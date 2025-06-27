package liuyuyang.net.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import liuyuyang.net.model.Baidu;

public interface BaiduService {
    /**
     * 刷新百度统计access token
     * @return 刷新后的token信息
     */
    Baidu refreshAccessToken();
    
    /**
     * 获取当前有效的access token
     * @return access token字符串
     */
    String getValidAccessToken();
    
    /**
     * 检查token是否即将过期（提前1天刷新）
     * @return true表示需要刷新
     */
    boolean isTokenNeedRefresh();
    
    /**
     * 获取百度统计数据
     * @param startDate 开始日期 (格式: 20240101)，可选
     * @param endDate 结束日期 (格式: 20240131)，可选
     * @return 统计数据的JsonNode对象
     */
    JsonNode getStatisData(String startDate, String endDate);
} 