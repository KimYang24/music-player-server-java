package com.example.webmusic.models.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/*使用Lombok注解，@Date,自动生成
@ToString
@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
方法*/

/**
 * user
 */
@Builder
@Data
@TableName("user")
public class User {
    @TableId(type=IdType.AUTO)
    @JsonProperty("userId")
    private long user_id;
    private int age;
    private String email;
    private String gender;
    private String nickname;
    private String password;
    private String phone;
    private String username;
    @JsonProperty("picUrl")
    @TableField("pic_url")
    private String picurl;
}