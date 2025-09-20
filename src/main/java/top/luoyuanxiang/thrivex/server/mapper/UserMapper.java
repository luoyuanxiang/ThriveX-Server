package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.vo.UserQueryVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 按用户名查找
     *
     * @param username 用户名
     * @return {@link UserEntity }
     */
    UserEntity findByUsername(String username);

    /**
     * 获取用户列表
     *
     * @param queryVO 查询 vo
     * @return {@link List }<{@link UserEntity }>
     */
    List<UserEntity> list(@Param("vo") UserQueryVO queryVO);

    /**
     * 分页查询用户列表
     *
     * @param page    页
     * @param queryVO 查询 vo
     * @return {@link Page }<{@link UserEntity }>
     */
    Page<UserEntity> list(@Param("vo") Page<UserEntity> page, UserQueryVO queryVO);
}
