package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.entity.UserEntity;
import top.luoyuanxiang.thrivex.server.vo.EditPassVO;
import top.luoyuanxiang.thrivex.server.vo.UserQueryVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IUserService extends IService<UserEntity> {

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
     * @param queryVO 过滤 VO
     * @return {@link List }<{@link UserEntity }>
     */
    List<UserEntity> list(UserQueryVO queryVO);

    /**
     * 分页
     *
     * @param page    对象页面
     * @param queryVO 过滤 VO
     * @return {@link Page }<{@link UserEntity }>
     */
    Page<UserEntity> paging(Page<UserEntity> page, UserQueryVO queryVO);

    /**
     * 修改密码
     *
     * @param data 数据
     */
    void editPass(EditPassVO data);
}
