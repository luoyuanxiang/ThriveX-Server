package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoyuanxiang.thrivex.server.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 按用户名查找
     *
     * @param username 用户名
     * @return {@link User }
     */
    User findByUsername(String username);
}
