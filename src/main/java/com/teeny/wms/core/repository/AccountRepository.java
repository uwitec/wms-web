package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bao on 2017/7/25.
 */
@Repository
public interface AccountRepository {

    @Select("SELECT a_id AS id, account_name AS name FROM pda_account")
    List<CommonDTO> getAccountSet();
}
