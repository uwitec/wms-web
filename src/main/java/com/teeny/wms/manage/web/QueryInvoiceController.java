package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.service.QueryInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 单据查询
 */
@Controller
public class QueryInvoiceController {

    @Autowired
    private QueryInvoiceService queryInvoiceService;

    @RequestMapping(value = "/api/document/{type}", method = RequestMethod.GET)
    public void getDocument(Model model, @PathVariable("type") int type, @RequestHeader("account") int account) {
        BaseEntity<List<QueryDocumentDTO>> data = queryInvoiceService.getDocumentByType(type, account);
        model.addAttribute("data", data);
    }



}
