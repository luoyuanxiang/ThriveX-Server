package top.luoyuanxiang.thrivex.server.vo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 查询公共参数
 */
@Getter
@Setter
public class QueryCommonVO implements Serializable {

    /**
     * 关键字
     */
    private String key;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 构建查询包装器
     *
     * @param column 列
     * @return {@link QueryWrapper }<{@link T }>
     */
    public <T> QueryWrapper<T> buildQueryWrapper(String column) {
        return new QueryWrapper<T>()
                .like(key != null, column, key)
                .ge(StrUtil.isNotBlank(startDate), "create_time", startDate)
                .le(StrUtil.isNotBlank(endDate), "create_time", endDate)
                .between(StrUtil.isNotBlank(startDate) && StrUtil.isNotBlank(endDate), "create_time", startDate, endDate);
    }
}
