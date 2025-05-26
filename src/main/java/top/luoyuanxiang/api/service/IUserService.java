package top.luoyuanxiang.api.service;

import top.luoyuanxiang.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.api.vo.req.LoginReqVO;
import top.luoyuanxiang.api.vo.resp.LoginRespVO;
import top.luoyuanxiang.api.vo.user.EditPassDTO;
import top.luoyuanxiang.api.vo.user.UserDTO;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     *
     * @param vo VO
     * @return {@link LoginRespVO }
     */
    LoginRespVO login(LoginReqVO vo);

    /**
     * 添加
     *
     * @param user 用户
     */
    void add(UserDTO user);

    /**
     * 删除
     *
     * @param id 身份证
     */
    void del(Integer id);

    /**
     * 获取
     *
     * @param id 身份证
     * @return {@link User }
     */
    User get(Integer id);

    /**
     * 编辑密码
     *
     * @param data 数据
     */
    void editPass(EditPassDTO data);
}
