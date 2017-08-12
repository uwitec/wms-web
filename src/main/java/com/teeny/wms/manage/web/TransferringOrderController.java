package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.TransferListDTO;
import com.teeny.wms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lilei on 2017/7/19.
 * 调拨单
 */
@Controller
public class TransferringOrderController {

    @Autowired
    private TransferService transferService;

    @RequestMapping(value = "/api/transfer/list/{billNo}/{goodsName}/{s_inid}/{s_outid}/{sa_outid}/{sa_outid}/{l_inid}/{l_outid}", method = RequestMethod.GET)
    public void getTranList(Model model, @PathVariable("billNo") String billNo,
                            @PathVariable("goodsName") String goodsName,
                            @PathVariable("s_inid") int s_inid,
                            @PathVariable("s_outid") int s_outid,
                            @PathVariable("sa_inid") int sa_inid,
                            @PathVariable("sa_outid") int sa_outid,
                            @PathVariable("l_inid") int l_inid,
                            @PathVariable("l_outid") int l_outid, @RequestHeader("account") String account) {
        BaseEntity<TransferListDTO> data = transferService.getTransferList(billNo, goodsName, s_inid, s_outid, sa_inid, sa_outid, l_inid, l_outid, account);
    }

}
