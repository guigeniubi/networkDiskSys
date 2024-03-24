package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author xuange
 * @since 2023-09-05
 */
@Data
@TableName("x_file")
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "file_id", type = IdType.AUTO)
    private Integer fileId;
    private String fileName;
    private String filePath;
    private String fileType;
    private String fileSize;
    private String updateTime;
    private String parentId;
    private String userId;
    private String shareable;




    @TableField(exist = false)
    private List<Integer> fileIdList;

}
