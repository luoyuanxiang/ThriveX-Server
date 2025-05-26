package top.luoyuanxiang.api.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author luoyuanxiang
 */
@RestController
@RequestMapping("/")
public class HomeController {
    /**
     * 首页
     *
     * @return {@link String }
     */
    @GetMapping
    public String home() {
        return "<h1>Hello ThriveX</h1>";
    }
}
