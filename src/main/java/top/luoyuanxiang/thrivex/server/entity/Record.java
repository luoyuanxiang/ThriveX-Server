package top.luoyuanxiang.thrivex.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("record")
public class Record extends Model<Record> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 图片
     */
    @TableField("images")
    private String images;

    /**
     * 时间
     */
    @TableField("create_time")
    private String createTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
