package ir.maktab.project.service;

import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.LoginDto;
import ir.maktab.project.data.dto.ManagerDto;
import ir.maktab.project.data.dto.SubServiceDto;
import ir.maktab.project.data.entity.members.Manager;

import java.util.Map;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
public interface ManagerService {
    void save(Manager manager);

    ManagerDto findByUserNameAndPassword(LoginDto loginDto);

    void confirmUser(int identity);

    Map<ExpertDto, Set<SubServiceDto>> getExpertAndSubServices(int identity);

    void addSubServices(ExpertDto expertDto, SubServiceDto subServiceDto);
}
