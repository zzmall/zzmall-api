package com.zzmall.api.common.form;

import com.zzmall.api.common.constant.ApiConstant;
import io.netty.util.internal.StringUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Connor
 * @Date 2019/05/19 17:27
 */
@Data
public class ProductListForm {

//    private String keyword;

    /**
     * 类目id
     */
    private Integer categoryId;

    /**
     * 当前页
     */
    private Integer pageNum = ApiConstant.DEFAULT_PAGE;

    /**
     * 每页大小
     */
    private Integer pageSize = ApiConstant.DEFAULT_PAGE_SIZE;

    /**
     * 排序
     */
    private String orderBy = StringUtil.EMPTY_STRING;


}
