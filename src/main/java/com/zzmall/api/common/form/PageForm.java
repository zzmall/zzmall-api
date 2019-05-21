package com.zzmall.api.common.form;

import com.zzmall.api.common.constant.ApiConstant;
import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/21 20:18
 */
@Data
public class PageForm {

    /**
     * 当前页
     */
    private Integer pageNum = ApiConstant.DEFAULT_PAGE;

    /**
     * 每页大小
     */
    private Integer pageSize = ApiConstant.DEFAULT_PAGE_SIZE;
}
