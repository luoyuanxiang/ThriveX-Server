package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoyuanxiang.api.entity.Permission;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
