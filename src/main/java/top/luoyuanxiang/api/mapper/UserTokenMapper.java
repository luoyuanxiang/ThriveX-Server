package top.luoyuanxiang.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoyuanxiang.api.entity.UserToken;

/**
 * <p>
 * 用户 token Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

}
