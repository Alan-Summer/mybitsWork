package anle.mybits.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author an
 */
@Data
@TableName("user")
public class User {

    @TableId
    private Long id;

    @TableField(value = "name")
    private String name;

    private Integer age;

    private String email;


    private Long managerId;


    private LocalDateTime createTime;

    @TableField( exist = false)
    private String reMark;
}
