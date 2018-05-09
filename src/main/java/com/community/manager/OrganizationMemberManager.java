/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.manager;

import com.community.common.util.BeanUtils;
import com.community.common.util.UserUtils;
import com.community.dao.mapper.OrganizationMemberMapper;
import com.community.dao.mapper.UserInfoMapper;
import com.community.domain.core.Page;
import com.community.domain.core.Pagination;
import com.community.domain.core.Response;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.model.db.UserInfoDO;
import com.community.domain.request.OrganizationMemberRequest;
import com.community.domain.response.OrganizationMemberResponse;
import com.community.domain.response.vo.OrganizationMemberVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class OrganizationMemberManager {

    @Autowired
    private OrganizationMemberMapper organizationMemberMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Response<OrganizationMemberResponse> listPage(OrganizationMemberRequest request) {
        Page<OrganizationMemberDO> organizationMemberDOPage = organizationMemberMapper.listPage(request);
        List<OrganizationMemberDO> memberDOList = Optional.ofNullable(organizationMemberDOPage).flatMap(Page::getData).orElse(null);
        Pagination pagination = Optional.ofNullable(organizationMemberDOPage).flatMap(Page::getPagination).orElse(null);
        Boolean hadMore = Optional.ofNullable(organizationMemberDOPage).map(Page::getHasMore).orElse(null);
        //设置用户
        List<OrganizationMemberVO> memberVOList = null;
        if (CollectionUtils.isNotEmpty(memberDOList)) {
            //查找用户
            Set<Long> userIdSet = memberDOList.stream().map(OrganizationMemberDO::getUserId).collect(Collectors.toSet());
            Map<Long, UserInfoDO> userInfoDOMap = userInfoMapper.getByKeys(userIdSet);
            BeanUtils.list2list(memberDOList, OrganizationMemberVO.class);
            memberVOList.forEach(memberVO -> {
                memberVO.setUserInfo(UserUtils.getUserInfoVO(userInfoDOMap, memberVO.getUserId()));
            });
        }
        //返回结果
        OrganizationMemberResponse organizationMemberResponse = new OrganizationMemberResponse();
        organizationMemberResponse.setOrgMemberList(memberVOList);
        organizationMemberResponse.setPagination(pagination);
        organizationMemberResponse.setHasMore(hadMore);
        return Response.success(organizationMemberResponse);
    }

    public Future<Response<OrganizationMemberResponse>> listPageAsync(OrganizationMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> this.listPage(request));
    }

    public OrganizationMemberDO getOrgMember(OrganizationMemberRequest request) {
        OrganizationMemberDO organizationMemberDO = null;
        if (null == request.getOrgId() || null == request.getUserId()) {
            return organizationMemberDO;
        }
        Page<OrganizationMemberDO> memberDOPage = organizationMemberMapper.listPage(request);
        List<OrganizationMemberDO> organizationMemberDOList = Optional.of(memberDOPage).flatMap(Page::getData).orElse(null);
        if (CollectionUtils.isNotEmpty(organizationMemberDOList) && organizationMemberDOList.size() == 1) {
            organizationMemberDO = organizationMemberDOList.get(0);
        }
        return organizationMemberDO;
    }

    public Future<OrganizationMemberDO> getOrgMemberAsync(OrganizationMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> getOrgMember(request));
    }

//    public Integer orgRole(OrganizationMemberRequest request){
//        if (null == request.getUserId() || null ==request.getOrgId()){
//            return null;
//        }
//        OrganizationMemberDO memberDO = this.getOrgMember(request);
//        Optional.ofNullable(memberDO).map(OrganizationMemberDO::getRole);
//    }

    public static void main(String[] args) {
        OrganizationMemberDO organizationMemberDO = null;
        Page<OrganizationMemberDO> memberDOPage = null;
        memberDOPage.getData();

    }
}
