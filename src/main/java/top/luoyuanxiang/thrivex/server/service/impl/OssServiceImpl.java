package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.entity.OssEntity;
import top.luoyuanxiang.thrivex.server.exception.CustomException;
import top.luoyuanxiang.thrivex.server.mapper.OssMapper;
import top.luoyuanxiang.thrivex.server.service.IOssService;
import top.luoyuanxiang.thrivex.server.utils.OssUtils;

/**
 * <p>
 * oss配置表 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class OssServiceImpl extends ServiceImpl<OssMapper, OssEntity> implements IOssService {

    @Override
    public void saveOss(OssEntity oss) {
        // 判断是否有重复
        long count = this.lambdaQuery().eq(OssEntity::getPlatform, oss.getPlatform()).count();
        if (count > 0) throw new CustomException("该平台已存在，请勿重复添加");

        if ("local".equals(oss.getPlatform())) {
            // 获取当前项目的路径
            String projectPath = System.getProperty("user.dir");
            oss.setEndPoint(projectPath + "/");
        }

        this.save(oss);
    }

    @Override
    public void delOss(Integer id) {
        OssEntity oss = this.getById(id);
        if (oss == null) throw new CustomException("删除失败");
        // 如果是默认的平台，提示不可删除
        if (oss.getPlatform().equals("local")) throw new CustomException("默认平台不可删除");
        boolean result = this.removeById(id);
        if (result) OssUtils.removeStorage(OssUtils.getStorageList(), oss.getPlatform());
    }

    @Override
    public void updateOss(OssEntity oss) {
        String platform = oss.getPlatform();

        if ("local".equals(platform)) {
            // 获取当前项目的路径
            String projectPath = System.getProperty("user.dir");
            oss.setEndPoint(projectPath + "/");

            // 每次修改时候，如果路径不包含static则追加上
            if (!oss.getDomain().contains("static")) {
                oss.setDomain(oss.getDomain() + "static/");
            }
        }

        // 不允许更改平台
        oss.setPlatform(null);
        boolean result = this.updateById(oss);
        if (result) {
            oss.setPlatform(platform);
            OssUtils.registerPlatform(oss);
        }
    }

    @Override
    public void enable(Integer id) {
        // 先禁用所有的配置
        boolean temp1 = this.update(Wrappers.<OssEntity>update().lambda().set(OssEntity::getIsEnable, 0));
        if (!temp1) throw new CustomException("操作失败");

        // 再启用制定的配置
        boolean temp2 = this.update(Wrappers.<OssEntity>update().lambda().set(OssEntity::getIsEnable, 1).eq(OssEntity::getId, id));
        if (!temp2) throw new CustomException("启用失败");

        OssEntity oss = this.getById(id);
        OssUtils.registerPlatform(oss);
    }
}
