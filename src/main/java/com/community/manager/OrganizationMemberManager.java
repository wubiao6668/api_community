/**
 * Created by wubiao on - 2018/04/30.
 */

package com.community.manager;

import com.community.dao.mapper.OrganizationMemberMapper;
import com.community.domain.core.Page;
import com.community.domain.model.db.OrganizationMemberDO;
import com.community.domain.request.OrganizationMemberRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class OrganizationMemberManager {

    @Autowired
    private OrganizationMemberMapper organizationMemberMapper;

    public Future<Page<OrganizationMemberDO>> listPageAsync(OrganizationMemberRequest request) {
        return CompletableFuture.supplyAsync(() -> organizationMemberMapper.listPage(request));
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
