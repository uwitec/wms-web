package com.teeny.wms.core.repository;

import com.teeny.wms.dto.TransferListDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by bao on 2017/8/1.
 *
 */
@Repository
public interface TranBillRepository {


    int countByWarehoust(@Param("warehouseId") int warehouseId, @Param("account") String account);

    TransferListDTO getTransferList(@Param("billNo") String billNo,@Param("goodsName") String goodsName,@Param("s_inid") int s_inid,@Param("s_outid") int s_outid,@Param("sa_inid") int sa_inid,@Param("sa_outid") int sa_outid,@Param("l_inid") int l_inid,@Param("l_outid") int l_outid,@Param("account") String account);
}
