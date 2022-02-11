package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.ServiceDto;
import ir.maktab.project.data.dto.SubServiceDto;
import ir.maktab.project.data.dto.SubServiceRequestDto;
import ir.maktab.project.data.dto.mapper.SubServiceMapper;
import ir.maktab.project.data.entity.services.SubService;
import ir.maktab.project.data.repository.SubServiceRepository;
import ir.maktab.project.exception.HomeServiceException;
import ir.maktab.project.service.ServiceService;
import ir.maktab.project.service.SubServiceService;
import ir.maktab.project.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final Validation validation;
    private final ServiceService serviceService;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<String> getAllServiceName() {
        return subServiceRepository.findAll().stream().map(SubService::getName).collect(Collectors.toList());
    }

    public boolean validateNewName(String name) {
        return validation.validateNewName(name, getAllServiceName());
    }

    public boolean save(SubServiceDto subServiceDto) {
        subServiceRepository.save(SubServiceMapper.mapSubServiceDtoToSubServiceForSaving(subServiceDto));
        return true;
    }

    public SubServiceDto findSubServiceByName(String name) {
        Optional<SubService> subService = subServiceRepository.findByName(name);
        if (subService.isEmpty())
            throw new HomeServiceException("we have n't this sub service!");
        return SubServiceMapper.mapSubServiceToSubServiceDto(subService.get());
    }

    @Override
    public void addNewSubService(SubServiceRequestDto subServiceRequestDto) {
        validateNewName(subServiceRequestDto.getName());
        ServiceDto serviceDto = serviceService.findServiceByName(subServiceRequestDto.getServiceName());
        SubServiceDto subServiceDto = SubServiceDto.builder()
                .service(serviceDto)
                .cost(subServiceRequestDto.getCost())
                .description(subServiceRequestDto.getDescription())
                .name(subServiceRequestDto.getName())
                .build();
        save(subServiceDto);
    }
}
