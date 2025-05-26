package top.luoyuanxiang.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
 *
 * @author luoyuanxiang
 */
public record DocumentVO(String content, String operation) implements Serializable {

}
