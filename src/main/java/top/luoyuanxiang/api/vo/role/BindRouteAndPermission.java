package top.luoyuanxiang.api.vo.role;

import lombok.Data;

import java.util.List;

@Data
public class BindRouteAndPermission {
    /**
     * 路由 ID
     */
    List<Integer> route_ids;
    /**
     * 权限 ID
     */
    List<Integer> permission_ids;
}
