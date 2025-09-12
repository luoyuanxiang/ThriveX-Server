package top.luoyuanxiang.thrivex.server.service.impl;

import top.luoyuanxiang.thrivex.server.entity.Permission;
import top.luoyuanxiang.thrivex.server.mapper.PermissionMapper;
import top.luoyuanxiang.thrivex.server.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
