package ir.maktab.project.service;

import ir.maktab.project.data.dto.SubServiceDto;
import ir.maktab.project.data.dto.SubServiceRequestDto;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface SubServiceService {

    List<String> getAllSubServiceName();

    boolean save(SubServiceDto subService);

    SubServiceDto findSubServiceByName(String name);

    void addNewSubService(SubServiceRequestDto subServiceRequestDto);
}
