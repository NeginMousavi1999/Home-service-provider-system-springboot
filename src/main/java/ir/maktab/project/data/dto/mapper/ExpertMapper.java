package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.entity.members.Expert;

/**
 * @author Negin Mousavi
 */
public class ExpertMapper {
    private static final int suffix = 1000;

    public static Expert mapExpertDtoToExpert(ExpertDto expertDto) {
        return Expert.builder()
                .id(expertDto.getIdentity() - suffix)
                .firstName(expertDto.getFirstName())
                .lastName(expertDto.getLastName())
                .email(expertDto.getEmail())
                .password(expertDto.getPassword())
                .credit(expertDto.getCredit())
                .userStatus(expertDto.getUserStatus())
                .userRole(expertDto.getUserRole())
                .registrationDate(expertDto.getRegistrationDate())
                .picture(expertDto.getPicture())
                .score(expertDto.getScore())
                .build();
    }

    public static ExpertDto mapExpertToExpertDto(Expert expert) {
        return ExpertDto.builder()
                .identity(expert.getId() + suffix)
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .email(expert.getEmail())
                .password(expert.getPassword())
                .credit(expert.getCredit())
                .userStatus(expert.getUserStatus())
                .userRole(expert.getUserRole())
                .registrationDate(expert.getRegistrationDate())
                .picture(expert.getPicture())
                .score(expert.getScore())
                .build();
    }
}
