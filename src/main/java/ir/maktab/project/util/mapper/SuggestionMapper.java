package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.SuggestionDto;
import ir.maktab.project.data.entity.order.Order;
import ir.maktab.project.data.entity.order.Suggestion;

/**
 * @author Negin Mousavi
 */
public class SuggestionMapper {
    private static final int suffix = 1000;

    public static Suggestion mapSuggestionDtoToSuggestionForUpdate(SuggestionDto suggestionDto) {
        return Suggestion.builder()
                .suggestionStatus(suggestionDto.getSuggestionStatus())
                .build();
    }

    public static Suggestion mapSuggestionDtoToSuggestionForSaving(SuggestionDto suggestionDto) {
        return Suggestion.builder()
                .startTime(suggestionDto.getStartTime())
                .suggestedPrice(suggestionDto.getSuggestedPrice())
                .durationOfWork(suggestionDto.getDurationOfWork())
                .expert(ExpertMapper.mapExpertDtoToExpert(suggestionDto.getExpert()))
                .suggestionStatus(suggestionDto.getSuggestionStatus())
                .order(OrderMapper.mapOrderDtoToOrderWithId(suggestionDto.getOrder()))
                .build();
    }

    public static SuggestionDto mapSuggestionToSuggestionDto(Suggestion suggestion) {
        SuggestionDto suggestionDto = SuggestionDto.builder()
                .identity(suggestion.getId() + suffix)
                .startTime(suggestion.getStartTime())
                .suggestedPrice(suggestion.getSuggestedPrice())
                .durationOfWork(suggestion.getDurationOfWork())
                .expert(ExpertMapper.mapExpertToExpertDto(suggestion.getExpert()))
                .registrationDate(suggestion.getRegistrationDate())
                .suggestionStatus(suggestion.getSuggestionStatus())
                .build();
        Order order = suggestion.getOrder();
        if (order != null)
            suggestionDto.setOrder(OrderMapper.mapOrderToOrderDtoToPay(order));
        return suggestionDto;
    }

    public static SuggestionDto mapSuggestionToSuggestionDtoForSorting(Suggestion suggestion) {
        SuggestionDto suggestionDto = mapSuggestionToSuggestionDto(suggestion);
        suggestionDto.setOrder(OrderMapper.mapOrderToOrderDtoWithoutSuggestion(suggestion.getOrder()));
        return suggestionDto;
    }
}