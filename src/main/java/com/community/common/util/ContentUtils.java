package com.community.common.util;

import com.community.common.constant.Constant;
import com.community.domain.bo.ContentBO;
import com.community.domain.response.ContentResponse;
import com.community.domain.response.vo.ContentVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.List;

import static com.community.common.constant.Constant.MAX_SUMMARY_LENGTH;

public class ContentUtils {

    /**
     * 设置摘要
     *
     * @param contentVO
     * @return
     */
    public static String extractSummary(ContentVO contentVO) {
        StringBuilder summarySb = new StringBuilder();
        ContentBO[] contentBOs = contentVO.getContents();
        if (ArrayUtils.isNotEmpty(contentBOs)) {
            for (ContentBO contentBOTemp : contentBOs) {
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
        return summarySb.toString();
    }

    /**
     * 设置摘要
     *
     * @param contentVOList
     * @return
     */
    public static void extractSummary(List<ContentVO> contentVOList) {
        if (CollectionUtils.isEmpty(contentVOList)) {
            return;
        }
        contentVOList.forEach(contentVO -> {
            //设置摘要
            contentVO.setSummary(extractSummary(contentVO));
        });
    }


}
