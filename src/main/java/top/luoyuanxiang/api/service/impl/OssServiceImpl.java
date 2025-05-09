package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.luoyuanxiang.api.entity.Oss;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.handler.DynamicResourceHandlerMapping;
import top.luoyuanxiang.api.mapper.OssMapper;
import top.luoyuanxiang.api.service.IOssService;
import top.luoyuanxiang.api.utils.OssUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * oss配置表 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements IOssService {

    @Resource
    private DynamicResourceHandlerMapping dynamicResourceHandlerMapping;

    @Override
    public void saveOss(Oss oss) {
        // 判断是否有重复
        Long count = this.lambdaQuery().eq(Oss::getPlatform, oss.getPlatform()).count();
        if (count > 0) throw new CustomException("该平台已存在，请勿重复添加");
        this.save(oss);
    }

    @Override
    public void delOss(Integer id) {
        Oss oss = this.getById(id);
        if (oss == null) throw new CustomException("删除失败");
        // 如果是默认的平台，提示不可删除
        if (oss.getPlatform().equals("local")) throw new CustomException("默认平台不可删除");
        boolean result = this.removeById(id);
        if (result) OssUtils.removeStorage(OssUtils.getStorageList(), oss.getPlatform());
    }

    @Override
    public void updateOss(Oss oss) {
        String platform = oss.getPlatform();

        // 不允许更改平台
        oss.setPlatform(null);
        boolean result = this.updateById(oss);
        if (result) {
            // 添加映射信息
            dynamicResourceHandlerMapping.addMapping(oss);
            oss.setPlatform(platform);
            OssUtils.registerPlatform(oss);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(Integer id) {
        // 先禁用所有的配置
        boolean temp1 = this.update(Wrappers.<Oss>update().lambda().set(Oss::getIsEnable, 0));
        if (!temp1) throw new CustomException("操作失败");

        // 再启用制定的配置
        boolean temp2 = this.update(Wrappers.<Oss>update().lambda().set(Oss::getIsEnable, 1).eq(Oss::getId, id));
        if (!temp2) throw new CustomException("启用失败");

        Oss oss = this.getById(id);
        // 添加映射信息
        dynamicResourceHandlerMapping.addMapping(oss);
        OssUtils.registerPlatform(oss);
    }

    @Override
    public Oss getEnableOss() {
        QueryWrapper<Oss> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Oss::getIsEnable, 1);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Map<String, String>> getPlatform() {
        List<Map<String, String>> result = new ArrayList<>();
        String[] list = {"huawei", "aliyun", "qiniu", "tencent", "minio"};

        for (String item : list) {
            Map<String, String> data = new HashMap<>();
            data.put("name", platformName(item));
            data.put("value", item);
            result.add(data);
        }

        return result;
    }

    // 处理平台名称
    public String platformName(String data) {
        return switch (data) {
            case "local" -> "本地存储";
            case "huawei" -> "华为云";
            case "aliyun" -> "阿里云";
            case "qiniu" -> "七牛云";
            case "tencent" -> "腾讯云";
            case "minio" -> "Minio";
            default -> data;
        };
    }
}
