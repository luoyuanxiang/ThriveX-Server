package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.luoyuanxiang.api.entity.Comment;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.ArticleMapper;
import top.luoyuanxiang.api.mapper.CommentMapper;
import top.luoyuanxiang.api.service.ICommentService;
import top.luoyuanxiang.api.service.IConfigService;
import top.luoyuanxiang.api.utils.EmailUtils;
import top.luoyuanxiang.api.vo.comment.CommentFilterVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private IConfigService configService;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private EmailUtils emailUtils;

    @Override
    public List<Comment> list(CommentFilterVo filterVo) {
        List<Comment> list = baseMapper.list(filterVo);
        // 查询的结构格式
        if (Objects.equals(filterVo.getPattern(), "list")) return list;

        // 构建多级评论
        return buildCommentTree(list, 0);
    }

    @Override
    public Comment get(Integer id) {
        Comment data = getById(id);

        if (data == null) {
            throw new CustomException(400, "该评论不存在");
        }

        // 获取所有相关评论
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", data.getArticleId());
        List<Comment> list = baseMapper.selectList(queryWrapper);

        // 构建评论树
        data.setChildren(buildCommentTree(list, data.getId()));

        return data;
    }

    @Override
    public void paging(CommentFilterVo filterVo, Page<Comment> page) {
        // 分页查询出所有一级评论，然后在查询出全部评论进行树形组装
        baseMapper.paging(page, filterVo);
        List<Comment> list = list();
        page.getRecords().forEach(s -> {
            List<Comment> children = buildCommentTree(list, s.getId());
            s.setChildren(children);
        });
    }

    @Override
    public void getArticleCommentList(Integer articleId, Page<Comment> page) {
        CommentFilterVo filterVo = new CommentFilterVo();
        filterVo.setArticleId(articleId);
        paging(filterVo, page);
    }

    @Override
    public void add(Comment comment) {
        comment.insert();

        // 文章标题
        String title = articleMapper.selectById(comment.getArticleId()).getTitle();

        // 评论记录
        StringBuilder content = new StringBuilder();
        // 判断是否还有上一条评论
        Comment prev_comment = null;
        if (comment.getCommentId() != 0) {
            prev_comment = getById(comment.getCommentId());
            content.append(prev_comment.getName()).append("：").append(prev_comment.getContent()).append("<br>");
        }
        content.append(comment.getName()).append("：").append(comment.getContent());

        // 处理邮件模板
        Context context = new Context();
        context.setVariable("title", title);
        context.setVariable("recipient", comment.getName());

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String time = now.format(formatter);
        context.setVariable("time", time);

        context.setVariable("content", content.toString());

        // 获取url
        String url = configService.get("url", String.class);
        String path = String.format("%s/article/%d", url, comment.getArticleId());
        context.setVariable("url", path);

        String template = templateEngine.process("comment_email", context);

        // 如果是一级评论则邮件提醒管理员，否则邮件提醒被回复人和管理员
        String email = (prev_comment != null && !prev_comment.getEmail().isEmpty()) ? prev_comment.getEmail() : null;

        // 如果是一级评论则邮件提醒管理员，否则邮件提醒被回复人和管理员
        String emailTitle = (email != null) ? "您有最新回复~" : title;
        emailUtils.send(email, emailTitle, template);
    }

    /**
     * 构建评论树
     *
     * @param list 列表
     * @param cid  CID
     * @return {@link List }<{@link Comment }>
     */
    private List<Comment> buildCommentTree(List<Comment> list, Integer cid) {
        List<Comment> children = new ArrayList<>();
        for (Comment data : list) {
            if (data.getCommentId().equals(cid)) {
                data.setChildren(buildCommentTree(list, data.getId()));
                children.add(data);
            }
        }
        return children;
    }
}
