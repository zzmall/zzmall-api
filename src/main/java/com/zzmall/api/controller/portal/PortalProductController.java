package com.zzmall.api.controller.portal;

import com.zzmall.api.common.form.ProductListForm;
import com.zzmall.api.common.type.ProductSortType;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Product;
import com.zzmall.api.service.ProductService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author Connor
 * @Date 2019/05/19 17:22
 */
@RestController
@RequestMapping("/product")
public class PortalProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/list.do")
    public ResponseVO<Page<Product>> list(@Valid ProductListForm form, BindingResult result) {

        if (result.hasErrors()) {
            throw new ApiException(result.getFieldError().getDefaultMessage());
        }

        Page<Product> pages = null;

        //如果orderBy为空
        if (form.getOrderBy().equals(StringUtil.EMPTY_STRING)) {
            pages = productService.findAllByCategoryId(
                    form.getCategoryId(),
                    PageRequest.of(form.getPageNum() - 1, form.getPageSize())
            );

            return ResponseVO.success(pages);
        }

        pages = productService.findAllByCategoryId(
                form.getCategoryId(),
                PageRequest.of(form.getPageNum() - 1, form.getPageSize(), ProductSortType.toSort(form.getOrderBy()))
        );

        return ResponseVO.success(pages);
    }

    @RequestMapping("/detail.do")
    public ResponseVO<Product> detail(Integer productId) {
        Product product = productService.getProductById(productId);
        return ResponseVO.success(product);
    }

}
