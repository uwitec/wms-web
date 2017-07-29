package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.EmployessRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bao on 2017/7/27.
 */
@Service
@Transactional
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployessRepository employessRepository;

    @Override
    public BaseEntity<List<CommonDTO>> getReviewer(int id) {
        return new BaseEntity<List<CommonDTO>>(employessRepository.findAll());
    }
}
