package top.luoyuanxiang.api.vo;

import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.api.entity.Oss;

/**
 * @author luoyuanxiang
 */
@Getter
@Setter
public class OssVO extends Oss {

    /**
     * 项目路径
     */
    private String projectPath;
}
