package top.luoyuanxiang.thrivex.server.vo;

import java.util.List;

/**
 * 自定义分页
 *
 * @param page   页码
 * @param size   每页数量
 * @param pages  总页数
 * @param total  总数
 * @param prev   上一页
 * @param next   下一页
 * @param result 数据
 * @param <T>
 * @author luoyuanxiang
 */
public record Paging<T>(Long page, Long size,
                        Long pages, Long total,
                        boolean prev, boolean next,
                        List<T> result) {
}
