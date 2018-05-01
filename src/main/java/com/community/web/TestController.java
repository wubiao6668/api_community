//package com.community.web;
//
//import com.community.common.enums.PageAbleEnum;
//import com.community.dao.mapper.OrganizationMapper;
//import com.community.domain.core.Order;
//import com.community.domain.core.PageList;
//import com.community.domain.core.PaginationAble;
//import com.community.domain.core.Response;
//import com.community.domain.model.db.OrganizationDO;
//import com.community.domain.query.OrganizationQuery;
//import com.community.service.OrganizationService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@ResponseBody
//public class TestController {
//
//    @Resource
//    private OrganizationMapper organizationMapper;
//    @Resource
//    private OrganizationService organizationService;
//
//
//    @RequestMapping("/test")
//    public Response test() {
//        OrganizationDO organization = new OrganizationDO();
////        Map<Long, OrganizationDO> sdfs = organizationMapper.getByKeySet(1L);
////        System.out.println(sdfs);
//        OrganizationQuery organizationQuery = new OrganizationQuery();
////        organizationQuery.setPagination(new Pagination());
////        List<OrganizationDO> organizationList = organizationMapper.findList(organizationQuery);
////        organizationMapper.findList(new PageableQuery() );
////        System.out.println(organizationList);
////        PaginationAble<OrganizationQuery> query = new PaginationAble(new Pagination(), organizationQuery);
////        PageList<OrganizationDO> organizationPage = organizationMapper.findList(organizationQuery,query);
//        PaginationAble pageBounds = new PaginationAble();
////        pageBounds.setContainsTotalCount(true);
//        pageBounds.setPage(1);
//        pageBounds.setLimit(1);
//        pageBounds.setQueryCountType(PageAbleEnum.HAD_MORE.getCode());
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order("create_time", Order.Direction.DESC,null));
//        pageBounds.setOrders(orders);
//        organizationQuery.setUpdateTimeEnd(new Date());
//        Response organizationPage = organizationService.findList(organizationQuery,pageBounds);
//
//        System.out.println(organizationPage);
//        Response organization1Db = organizationService.getByKey(1L);
//        System.out.println("sdfsdfsdfs");
//        return organizationPage;
//    }
//
//    public static String ddd(String... key) {
//        return "dsdsds";
//    }
//
//    public final static void main(String[] arg) {
//        System.out.println(ddd("sdsd", "wewewewewe"));
//    }
//}
