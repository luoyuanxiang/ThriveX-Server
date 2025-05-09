package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;
import top.luoyuanxiang.api.entity.Config;
import top.luoyuanxiang.api.mapper.ConfigMapper;
import top.luoyuanxiang.api.service.IConfigService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 配置 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Override
    public Map<String, Object> groupByMap(String group) {
        if (Objects.isNull(group)) {
            return Map.of();
        }
        if (Objects.equals("all", group)) {
            return list().parallelStream()
                    .collect(Collectors.toMap(Config::getName, Config::getValue));
        }
        if (Objects.equals("system", group)) {
            return getSystemInfo();
        }
        return lambdaQuery()
                .eq(Config::getGroup, group)
                .list()
                .parallelStream()
                .collect(Collectors.toMap(Config::getName, Config::getValue));
    }

    @Override
    public <T> T get(String url, Class<T> tClass) {
        Config config = lambdaQuery().eq(Config::getName, url)
                .one();
        String value = config.getValue();
        if (Objects.nonNull(value)) {
            return new ObjectMapper().convertValue(value, tClass);
        }
        return null;
    }

    @Override
    public void edit(String group, Map<String, String> config) {
        for (Map.Entry<String, String> entry : config.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();

            LambdaQueryWrapper<Config> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Config::getName, name);
            Config c = new Config();
            c.setValue(value);
            baseMapper.update(c, wrapper);
        }
    }

    /**
     * 获取系统信息
     *
     * @return {@link Map }<{@link String }, {@link Object }>
     */
    public Map<String, Object> getSystemInfo() {
        SystemInfo systemInfo = new SystemInfo();

        // 获取操作系统信息
        OperatingSystem os = systemInfo.getOperatingSystem();
        // 获取系统名称
        String osName = os.getFamily();
        // 获取系统版本
        String osVersion = os.getVersionInfo().getVersion();

        // 获取操作系统内存信息
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        // 总内存量
        long totalMemory = memory.getTotal();
        // 可用内存量
        long availableMemory = memory.getAvailable();
        // 内存使用率
        double memoryUsage = (double) (totalMemory - availableMemory) / totalMemory * 100;

        Map<String, Object> result = new HashMap<>();
        result.put("osName", osName);
        result.put("osVersion", osVersion);
        result.put("totalMemory", (int) (totalMemory / (1024.0 * 1024 * 1024)));
        result.put("availableMemory", (int) (availableMemory / (1024.0 * 1024 * 1024)));
        result.put("memoryUsage", Float.valueOf(String.format("%.2f", memoryUsage)));

        return result;
    }
}
