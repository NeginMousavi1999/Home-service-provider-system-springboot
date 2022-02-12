package ir.maktab.project.service;

import ir.maktab.project.data.dto.ServiceDto;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface ServiceService {

    ServiceDto getServiceById(int id);

    List<String> getAllServiceName();

    boolean addNewService(ServiceDto service);

    ServiceDto findServiceByName(String name);

    Set<ServiceDto> getAllServiceIncludingSubService();
}
