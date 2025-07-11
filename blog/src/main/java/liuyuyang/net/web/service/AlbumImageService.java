package liuyuyang.net.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import liuyuyang.net.dto.album.AlbumImageDTO;
import liuyuyang.net.model.AlbumImage;

import java.util.List;

public interface AlbumImageService extends IService<AlbumImage> {
    void add(AlbumImageDTO albumImageDTO);

    void del(Integer id);
    
    void batchDel(List<Integer> ids);
    
    void edit(AlbumImage albumImage);

    AlbumImage get(Integer id);
    
    List<AlbumImage> list();
    
    Page<AlbumImage> paging(Integer page, Integer size);
}
