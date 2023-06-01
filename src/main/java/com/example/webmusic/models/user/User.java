package com.example.webmusic.models.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private long user_id;

    private String age;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private String email;
    private String gender;
    private String nickname;
    private String password;
    private String phone;
    private String username;
}