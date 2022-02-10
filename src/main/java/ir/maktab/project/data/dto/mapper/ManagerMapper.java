package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.ManagerDto;
import ir.maktab.project.data.entity.members.Manager;

/**
 * @author Negin Mousavi
 */
public class ManagerMapper {
    private static final int suffix = 1000;

    public static Manager mapManagerDtoToManager(ManagerDto managerDto) {
        return Manager.builder()
                .id(managerDto.getIdentity() - suffix)
                .email(managerDto.getEmail())
                .password(managerDto.getPassword())
                .build();
    }

    public static ManagerDto mapManagerToManagerDto(Manager manager) {
        return ManagerDto.builder()
                .identity(manager.getId() + suffix)
                .email(manager.getEmail())
                .password(manager.getPassword())
                .build();
    }
}
