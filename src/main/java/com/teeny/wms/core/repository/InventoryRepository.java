package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.model.response.InventoryCountEntity;
import com.teeny.wms.model.response.InventoryGoodsEntity;
import com.teeny.wms.model.response.RepositoryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see InventoryRepository
 * @since 2017/12/27
 */
@Repository
public interface InventoryRepository {

    List<CommonDTO> getPdType(@Param("account") String account, @Param("type") int type, @Param("sId") int sId);

    /**
     * 获取库区和区域的级联
     *
     * @param account 账户
     * @param id      盘点单id
     * @return
     */
    List<RepositoryEntity> getCollection(@Param("account") String account, @Param("id") int id);

    /**
     * 获取统计数量
     *
     * @param account      账户
     * @param pdId         盘点单id
     * @param repositoryId 库区id
     * @param areaId       区域id
     * @param barcode      货位码
     * @param isMerge      是否合并数据
     * @return
     */
    InventoryCountEntity count(@Param("account") String account, @Param("id") int pdId, @Param("repositoryId") int repositoryId, @Param("areaId") int areaId, @Param("barcode") String barcode, @Param("isMerge") boolean isMerge);

    List<InventoryGoodsEntity> getList(@Param("account") String account, @Param("id") int pdId, @Param("repositoryId") int repositoryId, @Param("areaId") int areaId, @Param("locationCode") String locationCode, @Param("isMerge") boolean isMerge);

    /**
     * 更新盘点单状态
     *
     * @param account 账套
     * @param pdId    盘点单id
     * @param status  盘点单状态 0提供,1pda已读取,2pda已完成,3pda已回写(后台更改,前台不做)
     */
    void updateInventoryStatus(@Param("account") String account, @Param("id") int pdId, @Param("status") int status);

    void complete(@Param("account") String account, @Param("list") List<Integer> ids, @Param("userId") int userId);
}
