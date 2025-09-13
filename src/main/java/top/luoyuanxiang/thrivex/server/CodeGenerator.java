package top.luoyuanxiang.thrivex.server;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import io.jsonwebtoken.security.Keys;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

/**
 * 代码生成
 *
 * @author luoyuanxiang
 */
public class CodeGenerator {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

        // 生成256位(32字节)密钥
        byte[] keyBytes = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated JWT Secret: " + base64Key);
//        codeGenerator();
    }

    /**
     * 代码生成器
     *
     */
    public static void codeGenerator() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/thrive?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false", "root", "123456")
                .globalConfig(builder -> builder
                        .author("luoyuanxiang")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("top.luoyuanxiang.thrivex.server")
                        .pathInfo(Map.of(OutputFile.xml, Paths.get(System.getProperty("user.dir")) + "/src/main/resources/mapper"))
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .controller("controller")
                        .serviceImpl("service.impl")
                        .xml("mapper")
                )
                .strategyConfig(builder -> builder
                        .addInclude("email_logs")
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .disableSerialVersionUID()
                        .enableActiveRecord()
                        .enableFileOverride()
                        .mapperBuilder()
                        .enableFileOverride()
                        .mapperAnnotation(Mapper.class)
                        .enableBaseResultMap()
                        .serviceBuilder()
                        .enableFileOverride()
                        .controllerBuilder()
                        .enableFileOverride()
                        .enableRestStyle()
                        .enableHyphenStyle()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
