package com.example.webmusic.models.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/*使用Lombok注解，@Date,自动生成
@ToString
@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
方法*/

@Data
//注释表名
@TableName("user")
public class User {
    //注释主键名，type属性指定主键类型，auto指定数据库ID自增
    @TableId(type=IdType.AUTO)
    String id;
    //如果名称不一致
    //@TableField(value="name")
    String username;
    String password;

    //也可以多表查询

}
