package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.mapper.UserMapper;
import top.luoyuanxiang.thrivex.server.service.IUserService;
import top.luoyuanxiang.thrivex.server.vo.EditPassVO;
import top.luoyuanxiang.thrivex.server.vo.UserQueryVO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public List<UserEntity> list(UserQueryVO queryVO) {
        return baseMapper.list(queryVO);
    }

    @Override
    public Page<UserEntity> paging(Page<UserEntity> page, UserQueryVO queryVO) {
        return baseMapper.list(page, queryVO);
    }

    @Override
    @Transactional
    public void editPass(EditPassVO data) {
        Optional<UserEntity> opt = lambdaQuery()
                .eq(UserEntity::getUsername, data.oldUsername()).oneOpt();
        if (opt.isEmpty()) {
            throw new RuntimeException("用户名或旧密码错误");
        }
        UserEntity user = opt.get();
        String password = user.getPassword();
        if (!passwordEncoder.matches(data.oldPassword(), password)) {
            throw new RuntimeException("用户名或旧密码错误");
        }
        if (!Objects.equals(data.newUsername(), data.oldUsername())) {
            boolean exists = lambdaQuery()
                    .eq(UserEntity::getUsername, data.newUsername())
                    .exists();
            if (exists) {
                throw new RuntimeException("新用户名已存在：" + data.newUsername());
            }
        }
        user.setPassword(passwordEncoder.encode(data.newPassword()));
        user.setUsername(data.newUsername());
        user.updateById();
    }
}
