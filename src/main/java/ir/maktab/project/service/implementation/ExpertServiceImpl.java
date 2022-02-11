package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.*;
import ir.maktab.project.data.dto.mapper.ExpertMapper;
import ir.maktab.project.data.dto.mapper.SubServiceMapper;
import ir.maktab.project.data.dto.mapper.UserMapper;
import ir.maktab.project.data.entity.members.Expert;
import ir.maktab.project.data.entity.services.SubService;
import ir.maktab.project.data.enumuration.OrderStatus;
import ir.maktab.project.data.enumuration.SuggestionStatus;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.data.repository.ExpertRepository;
import ir.maktab.project.exception.HomeServiceException;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.OrderService;
import ir.maktab.project.service.SuggestionService;
import ir.maktab.project.util.GenerateDate;
import ir.maktab.project.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private final SuggestionService suggestionService;
    private final OrderService orderService;
    private final Validation validation;
    private final ModelMapper modelMapper = new ModelMapper();

    public void save(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        System.out.println(expert);
        expertRepository.save(expert);
    }

    public boolean delete(ExpertDto expertDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        expertRepository.delete(expert);
        return true;
    }

    public boolean update(ExpertDto expertDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        expertRepository.save(expert);
        return true;
    }

    public ExpertDto findByEmail(String email) {
        Optional<Expert> expert = expertRepository.findByEmail(email);
        if (expert.isEmpty())
            throw new HomeServiceException("we have not this expert!");
        return ExpertMapper.mapExpertToExpertDto(expert.get());
    }

    public Long getCountOfRecords() {
        return expertRepository.count();
    }

    public void addSubServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        Optional<List<SubService>> optionalServices = expertRepository.customeGetSubServiceByExpertId(expert.getId());
        List<SubService> expertSubServices = optionalServices.orElseGet(ArrayList::new);
        expertSubServices.add(SubServiceMapper.mapSubServiceDtoToSubService(subServiceDto));
        expert.setSubServices(new HashSet<>(expertSubServices));
        expertRepository.save(expert);
    }

    @Override
    public Set<SubServiceDto> getSubServices(ExpertDto expertDto) {
        Optional<List<SubService>> subServices = expertRepository
                .customeGetSubServiceByExpertId(ExpertMapper.mapExpertDtoToExpert(expertDto).getId());
        if (subServices.isEmpty())
            throw new HomeServiceException("no sub services!");
        return subServices.get().stream().map(SubServiceMapper::mapSubServiceToSubServiceDto).collect(Collectors.toSet());
    }

    @Override
    public void updateScore(ExpertDto expertDto, double score) {
        double oldScore = expertDto.getScore();
        expertDto.setScore((oldScore + score) / 2);
        update(expertDto);
    }

    @Override
    public void addNewSuggestion(String date, double suggestedPrice, int durationOfWork, OrderDto orderDto, ExpertDto expertDto) {
        if (suggestedPrice < orderDto.getSubService().getCost())
            throw new HomeServiceException("suggested price can not be less than subservice price!");
        Set<SuggestionDto> suggestionDtoSet = suggestionService.getByOrder(orderDto);
        orderDto.setSuggestions(suggestionDtoSet);
        validation.validateUserStatus(UserStatus.CONFIRMED, expertDto.getUserStatus());
        orderDto.setSuggestions(suggestionDtoSet);
        SuggestionDto suggestionDto = SuggestionDto.builder()
                .expert(expertDto)
                .order(orderDto)
                .durationOfWork(durationOfWork)
                .suggestedPrice(suggestedPrice)
                .startTime(GenerateDate.generateByPattern("yyyy-MM-dd", date))
                .suggestionStatus(SuggestionStatus.NEW)
                .build();
        orderDto.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        suggestionService.addNewSuggestion(suggestionDto, orderDto);
    }

    @Override
    public Map<UserDto, Integer> getExpertAndNumberOfRegisteredRequests() {
        List<ExpertDto> experts = getAll();
        Map<UserDto, Integer> map = new HashMap<>();
        for (ExpertDto expert : experts) {
            int count = orderService.findNumberOfOrdersPlacedByExpert(expert);
            map.put(UserMapper.mapExpertToUserDto(expert), count);
        }
        return map;
    }

    @Override
    public List<ExpertDto> getAll() {
        List<Expert> experts = (List<Expert>) expertRepository.findAll();
        return experts.stream().map(ExpertMapper::mapExpertToExpertDto).collect(Collectors.toList());
    }

    @Override
    public List<SuggestionDto> getSuggestions(ExpertDto expertDto) {
        return suggestionService.getAllSuggestions(expertDto);
    }

    @Override
    public ExpertDto findById(int identity) {
        Optional<Expert> expert = expertRepository.findById(identity - 1000);
        if (expert.isEmpty())
            throw new HomeServiceException("no such expert!");
        return ExpertMapper.mapExpertToExpertDto(expert.get());
    }

    @Override
    public void confirmEmail(ExpertDto expertDto) {
        expertDto.setUserStatus(UserStatus.WAITING);
        update(expertDto);
    }
}
