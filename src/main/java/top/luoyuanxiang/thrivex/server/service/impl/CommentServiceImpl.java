package top.luoyuanxiang.thrivex.server.service.impl;

import top.luoyuanxiang.thrivex.server.entity.Comment;
import top.luoyuanxiang.thrivex.server.mapper.CommentMapper;
import top.luoyuanxiang.thrivex.server.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
