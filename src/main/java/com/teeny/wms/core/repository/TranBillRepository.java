package com.teeny.wms.core.repository;

import com.teeny.wms.dto.QueryDocumentDTO;
import com.teeny.wms.dto.TransferListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/8/1.
 *
 */
@Repository
public interface TranBillRepository {


    int countByWarehousId(@Param("warehouseId") int warehouseId, @Param("account") String account);

    TransferListDTO getTransferList(@Param("billNo") String billNo,@Param("goodsName") String goodsName,@Param("s_inid") int s_inid,@Param("s_outid") int s_outid,@Param("sa_inid") int sa_inid,@Param("sa_outid") int sa_outid,@Param("l_inid") int l_inid,@Param("l_outid") int l_outid,@Param("account") String account);

    @Select("SELECT b.billid AS id,b.billnumber AS documentNo, b.pdaInTime AS documentDate,CASE b.billstates WHEN 10 THEN '验收中' WHEN 13 THEN '已验收' ELSE '' END AS status FROM ${account}.dbo.pda_TranBill b;")
    List<QueryDocumentDTO> getBill(String account);
}
