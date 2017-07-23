package com.teeny.wms.service;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.dto.EmployeesDTO;

/**
 * Created by lilei on 2017/7/10.
 *
 */
public interface SystemService {
    Employess findByUsername(String username);

    EmployeesDTO findByPinyin(String username);
}
