package com.community.domain.response.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrganizationMemberVO implements Serializable {
    private static final long serialVersionUID = 1646329274900805085L;

    /**
     * id
     */
    private Long id;
    /**
     * 成员user_id
     */
    private Long userId;
    /**
     * 组织表主键
     */
    private Long orgId;
    /**
     * 状态（1-关注、2-取消关注3、拒绝加入）
     */
    private Integer status;
    /**
     * 申请次数
     */
    private Integer applyNum;
    /**
     * 最后申请时间
     */
    private LocalDateTime applyLastTime;
    /**
     * 加入理由
     */
    private String joinReason;
    /**
     * 操作人
     */
    private Long operatorId;
    /**
     * 角色（1-管理员、2-班委,3-普通用户）
     */
    private Integer role;
    /**
     * 角色别名
     */
    private String roleAlias;

    private UserInfoVO userInfo;

}
