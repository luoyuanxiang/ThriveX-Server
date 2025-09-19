package top.luoyuanxiang.thrivex.server.oss;

import org.springframework.web.multipart.MultipartFile;

/**
 * 策略模式
 *
 * @author luoyuanxiang
 */
public interface IOssStrategyServer {

    String uploadFile(MultipartFile file, String folder);
}
