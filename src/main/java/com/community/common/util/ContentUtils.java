package com.community.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.community.common.constant.Constant;
import com.community.domain.bo.ContentBO;
import com.community.domain.response.ContentResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.community.common.constant.Constant.MAX_SUMMARY_LENGTH;

public class ContentUtils {

    /**
     * 设置摘要
     *
     * @param contentResponse
     * @return
     */
    public static String extractSummary(ContentResponse contentResponse) {
        String contentJson = contentResponse.getContentJson();
        StringBuilder summarySb = new StringBuilder();
        if (StringUtils.isNotEmpty(contentJson)) {
            List<ContentBO> contentBOList = JSON.parseObject(contentJson, new TypeReference<List<ContentBO>>() {
            });
            contentResponse.setContentList(contentBOList);
            if (CollectionUtils.isNotEmpty(contentBOList)) {
                for (ContentBO contentBOTemp : contentBOList) {
                    //计算摘要
                    if (summarySb.length() >= MAX_SUMMARY_LENGTH) {
                        break;
                    }
                    boolean isImgType = Constant.ContextTypeEnum.IMG.equals(contentBOTemp.getType());
                    if (isImgType) {
                        String text = contentBOTemp.getText();
                        text = null != text ? text.substring(0, MAX_SUMMARY_LENGTH) : "";
                        summarySb.append(text);
                    }
                }
            }
        }
        return summarySb.toString();
    }

    /**
     * 设置摘要
     *
     * @param contentResponseList
     * @return
     */
    public static void extractSummary(List<ContentResponse> contentResponseList) {
        if (CollectionUtils.isEmpty(contentResponseList)) {
            return;
        }
        contentResponseList.forEach(contentResponse -> {
            //设置摘要
            contentResponse.setSummary(extractSummary(contentResponse));
        });
    }


}
