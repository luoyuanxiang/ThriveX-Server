package top.luoyuanxiang.api.service.impl;

import top.luoyuanxiang.api.entity.Permission;
import top.luoyuanxiang.api.mapper.PermissionMapper;
import top.luoyuanxiang.api.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
