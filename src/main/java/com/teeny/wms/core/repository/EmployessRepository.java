package com.teeny.wms.core.repository;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.dto.EmployeesDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**mvn
 * Created by lilei on 2017/7/16.
 *
 */
@Repository
public interface EmployessRepository {
    public Employess findEmployerByUsername(@Param("username") String username);
    public void addEmployer(Employess employess);
    public EmployeesDTO findByPinyin(@Param("username") String username);
}
