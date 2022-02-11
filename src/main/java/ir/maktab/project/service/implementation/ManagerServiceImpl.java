package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.LoginDto;
import ir.maktab.project.data.dto.ManagerDto;
import ir.maktab.project.data.dto.SubServiceDto;
import ir.maktab.project.data.dto.mapper.ManagerMapper;
import ir.maktab.project.data.entity.members.Manager;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.data.repository.ManagerRepository;
import ir.maktab.project.exception.HomeServiceException;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.ManagerService;
import ir.maktab.project.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ExpertService expertService;
    private final UserService userService;

    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    public ManagerDto findByUserNameAndPassword(LoginDto loginDto) {
        Optional<Manager> manager = managerRepository.findByEmailAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (manager.isEmpty())
            throw new HomeServiceException("username or password is incorrect");
        return ManagerMapper.mapManagerToManagerDto(manager.get());
    }

    public void confirmUser(int identity) {
        userService.updateUserStatus(identity, UserStatus.CONFIRMED);
    }

    @Override
    public Map<ExpertDto, Set<SubServiceDto>> getExpertAndSubServices(int identity) {
        ExpertDto expertDto = expertService.findById(identity);
        Set<SubServiceDto> subServices = expertService.getSubServices(expertDto);
        Map<ExpertDto, Set<SubServiceDto>> result = new HashMap<>();
        result.put(expertDto, subServices);
        return result;
    }

    @Override
    public void addSubServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        expertService.addSubServices(expertDto, subServiceDto);
    }
}
