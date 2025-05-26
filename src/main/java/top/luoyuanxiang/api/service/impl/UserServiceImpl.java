package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.luoyuanxiang.api.entity.Role;
import top.luoyuanxiang.api.entity.User;
import top.luoyuanxiang.api.entity.UserToken;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.RoleMapper;
import top.luoyuanxiang.api.mapper.UserMapper;
import top.luoyuanxiang.api.properties.JwtProperties;
import top.luoyuanxiang.api.service.IRoleService;
import top.luoyuanxiang.api.service.IUserService;
import top.luoyuanxiang.api.service.IUserTokenService;
import top.luoyuanxiang.api.utils.JwtUtils;
import top.luoyuanxiang.api.vo.req.LoginReqVO;
import top.luoyuanxiang.api.vo.resp.LoginRespVO;
import top.luoyuanxiang.api.vo.user.EditPassDTO;
import top.luoyuanxiang.api.vo.user.UserDTO;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IRoleService roleService;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private IUserTokenService userTokenService;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        User user = lambdaQuery()
                .eq(User::getUsername, vo.username())
                .eq(User::getPassword, DigestUtils.md5DigestAsHex(vo.password().getBytes()))
                .one();
        if (user == null) throw new CustomException(400, "用户名或密码错误");
        user.setPassword("只有聪明的人才能看到密码");
        Role role = roleService.getById(user.getRoleId());
        String token = JwtUtils.JwtBuilder.builder()
                .secretKey(jwtProperties.getSecretKey())
                .ttlMillis(jwtProperties.getTtl())
                .subject(user.getUsername())
                .addClaims("id", user.getId())
                .addClaims("role", role.getMark())
                .build();
        // 先删除用户的token
        LambdaQueryWrapper<UserToken> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(UserToken::getUid,user.getId());
        userTokenService.lambdaUpdate()
                .eq(UserToken::getUid, user.getId())
                .remove();
        // 再存储用户的token
        UserToken userToken = new UserToken();
        userToken.setUid(user.getId());
        userToken.setToken(token);
        userToken.insert();
        return new LoginRespVO(token, user, role);
    }

    @Override
    public void add(UserDTO user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());

        User data = baseMapper.selectOne(queryWrapper);

        // 判断用户是否存在
        if (data != null) throw new CustomException(400, "该用户已存在：" + user.getUsername());

        // 密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        User temp = new User();
        BeanUtils.copyProperties(user, temp);

        baseMapper.insert(temp);
    }

    @Override
    public void del(Integer id) {
        User data = baseMapper.selectById(id);
        if (data == null) throw new CustomException(400, "该用户不存在");
        baseMapper.deleteById(id);
    }

    @Override
    public User get(Integer id) {
        User data = baseMapper.selectById(id);
        data.setPassword("只有聪明的人才能看到密码");

        Role role = roleMapper.selectById(data.getRoleId());
        data.setRole(role);

        return data;
    }

    @Override
    public void editPass(EditPassDTO data) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", data.getUsername());
        queryWrapper.eq("password", DigestUtils.md5DigestAsHex(data.getOldPassword().getBytes()));

        User user = baseMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new CustomException(400, "用户名或旧密码错误");
        }

        user.setPassword(DigestUtils.md5DigestAsHex(data.getNewPassword().getBytes()));
        baseMapper.updateById(user);
    }
}
