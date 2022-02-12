package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.ServiceDto;
import ir.maktab.project.data.dto.mapper.ServiceMapper;
import ir.maktab.project.data.entity.services.Service;
import ir.maktab.project.data.repository.ServiceRepository;
import ir.maktab.project.exceptions.NotFoundException;
import ir.maktab.project.exceptions.ValidationException;
import ir.maktab.project.service.ServiceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Getter
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final Environment environment;

    public ServiceDto getServiceById(int id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isEmpty())
            throw new NotFoundException(environment.getProperty("No.Service"));
        return ServiceMapper.mapServiceToServiceDto(service.get());
    }

    public List<String> getAllServiceName() {
        return serviceRepository.findAll().stream().map(Service::getName).collect(Collectors.toList());
    }

    public boolean addNewService(ServiceDto serviceDto) {
        if (getAllServiceName().contains(serviceDto.getName()))
            throw new ValidationException(environment.getProperty("Duplicate.Service"));
        serviceRepository.save(ServiceMapper.mapServiceDtoToService(serviceDto));
        return true;
    }

    public ServiceDto findServiceByName(String name) {
        Optional<Service> service = serviceRepository.findByName(name);
        if (service.isEmpty())
            throw new NotFoundException(environment.getProperty("No.Service"));
        return ServiceMapper.mapServiceToServiceDto(service.get());
    }

    public Set<ServiceDto> getAllServiceIncludingSubService() {
        Optional<List<Service>> optionalServices = serviceRepository.getAllIncludeSubService();
        if (optionalServices.isEmpty())
            throw new NotFoundException(environment.getProperty("No.Service.System"));
        return optionalServices.get().stream().map(ServiceMapper::mapServiceToServiceDtoIncludeSubService)
                .collect(Collectors.toSet());
    }
}
