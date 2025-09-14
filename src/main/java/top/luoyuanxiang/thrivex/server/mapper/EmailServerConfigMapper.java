package top.luoyuanxiang.thrivex.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.luoyuanxiang.thrivex.server.entity.EmailServerConfigEntity;

/**
 * <p>
 * 邮件服务器配置表 Mapper 接口
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@Mapper
public interface EmailServerConfigMapper extends BaseMapper<EmailServerConfigEntity> {

}
