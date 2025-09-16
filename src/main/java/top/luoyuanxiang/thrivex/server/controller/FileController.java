package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.get.ListFilesResult;
import org.dromara.x.file.storage.core.get.RemoteDirInfo;
import org.dromara.x.file.storage.core.get.RemoteFileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.thrivex.server.exception.CustomException;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.utils.OssUtils;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.io.IOException;
import java.util.*;

/**
 * 文件管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileStorageService fileStorageService;

    /**
     * 文件上传
     *
     * @param dir   迪尔
     * @param files 文件
     * @return {@link Result }<{@link Object }>
     * @throws IOException ioexception
     */
    @HasPermission("file:add")
    @PostMapping
    public Result<Object> add(@RequestParam(defaultValue = "") String dir, @RequestParam MultipartFile[] files) throws IOException {
        if (dir == null || dir.trim().isEmpty()) throw new CustomException(400, "请指定一个目录");

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            FileInfo result = fileStorageService.of(file)
                    .setPlatform(OssUtils.getPlatform())
                    .setPath(dir + '/')
                    .upload();

            if (result == null) throw new CustomException("上传文件失败");

            String url = result.getUrl();
            urls.add(url.startsWith("https://") ? url : "https://" + url);
        }

        return Result.success("文件上传成功：", urls);
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("file:del")
    @DeleteMapping
    public Result<String> del(@RequestParam String filePath) {
        String url = filePath.startsWith("https://") ? filePath : "https://" + filePath;
        boolean delete = fileStorageService.delete(url);
        return Result.status(delete);
    }

    /**
     * 批量删除文件
     *
     * @param pathList 路径列表
     * @return {@link Result }
     */
    @HasPermission("file:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody String[] pathList) {
        for (String url : pathList) {
            boolean delete = fileStorageService.delete(url.startsWith("https://") ? url : "https://" + url);
            if (!delete) throw new CustomException("删除文件失败");
        }
        return Result.success();
    }

    /**
     * 获取文件信息
     *
     * @param filePath 文件路径
     * @return {@link Result }<{@link FileInfo }>
     */
    @HasPermission("file:info")
    @GetMapping("/info")
    public Result<FileInfo> get(@RequestParam String filePath)  {
        FileInfo fileInfo = fileStorageService.getFileInfoByUrl(filePath);
        return Result.success(fileInfo);
    }

    /**
     * 获取目录列表
     *
     * @return {@link Result }<{@link List }<{@link Map }>>
     */
    @HasPermission("file:dir")
    @GetMapping("/dir")
    public Result<List<Map<String, Object>>> getDirList() {
        ListFilesResult result = fileStorageService.listFiles()
                .setPlatform(OssUtils.getPlatform())
                .listFiles();

        // 获取文件列表
        List<Map<String, Object>> list = new ArrayList<>();
        List<RemoteDirInfo> fileList = result.getDirList();

        for (RemoteDirInfo item : fileList) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", item.getName());
            data.put("path", item.getOriginal());
            list.add(data);
        }

        return Result.success(list);
    }

    /**
     * 获取指定目录中的文件
     *
     * @param dir  迪尔
     * @param page 页
     * @param size 大小
     * @return {@link Result }<{@link Map }<{@link String }, {@link Object }>>
     */
    @HasPermission("file:list")
    @GetMapping("/list")
    public Result<Map<String, Object>> getFileList(
            @RequestParam String dir,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        if (dir == null || dir.trim().isEmpty()) throw new CustomException(400, "请指定一个目录");

        ListFilesResult result = fileStorageService.listFiles()
                .setPlatform(OssUtils.getPlatform())
                .setPath(dir + '/')
                .listFiles();

        // 获取文件列表
        List<Map<String, Object>> fileList = new ArrayList<>();
        List<RemoteFileInfo> remoteFileList = result.getFileList();

        // 按lastModified时间降序排序（最新的在前）
        remoteFileList.sort((a, b) -> b.getLastModified().compareTo(a.getLastModified()));

        // 计算分页参数
        int total = remoteFileList.size();
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, total);

        // 分页处理
        List<RemoteFileInfo> pageList = remoteFileList.subList(startIndex, endIndex);

        for (RemoteFileInfo item : pageList) {
            // 如果是目录就略过
            if (Objects.equals(item.getExt(), "")) continue;

            Map<String, Object> data = new HashMap<>();
            data.put("basePath", item.getBasePath());
            data.put("dir", dir);
            data.put("path", item.getBasePath() + item.getPath() + item.getFilename());
            data.put("name", item.getFilename());
            data.put("size", item.getSize());
            data.put("type", item.getExt());
            data.put("date", item.getLastModified());

            String url = item.getUrl();
            if (!url.startsWith("https://")) url = "https://" + url;
            data.put("url", url);

            fileList.add(data);
        }

        // 构建分页结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", fileList);
        resultMap.put("size", size);
        resultMap.put("page", page);
        resultMap.put("pages", (total + size - 1) / size);
        resultMap.put("total", total);

        return Result.success(resultMap);
    }

}
