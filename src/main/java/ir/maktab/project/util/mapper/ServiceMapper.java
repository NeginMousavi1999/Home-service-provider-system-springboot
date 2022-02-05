package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.ServiceDto;
import ir.maktab.project.data.dto.SubServiceDto;
import ir.maktab.project.data.entity.services.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
public class ServiceMapper {
    private static final int suffix = 1000;

    public static Service mapServiceDtoToService(ServiceDto serviceDto) {
        return Service.builder()
                .id(serviceDto.getIdentity() - suffix)
                .name(serviceDto.getName())
                .build();
    }

    public static ServiceDto mapServiceToServiceDto(Service service) {
        return ServiceDto.builder()
                .identity(service.getId() + suffix)
                .name(service.getName())
                .build();
    }

    public static ServiceDto mapServiceToServiceDtoIncludeSubService(Service service) {
        Set<SubServiceDto> subServiceDtos = service.getSubServices().stream()
                .map(SubServiceMapper::mapSubServiceToSubServiceDto).collect(Collectors.toSet());
        return ServiceDto.builder()
                .identity(service.getId() + suffix)
                .name(service.getName())
                .subServices(subServiceDtos)
                .build();
    }
}
