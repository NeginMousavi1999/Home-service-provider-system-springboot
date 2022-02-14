package ir.maktab.project.service;

import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.SuggestionDto;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface SuggestionService {

    void saveSuggestion(SuggestionDto suggestion);

    List<SuggestionDto> getAllSuggestions(ExpertDto expert);

    void update(SuggestionDto suggestion);

    Set<SuggestionDto> getByOrder(OrderDto order);

    Long getCountOfRecords();

    List<SuggestionDto> getSortedBySuggestedPriceAndExpertByOrder(OrderDto order);

    List<SuggestionDto> getSortedByExpertByOrder(OrderDto orderDto);

    List<SuggestionDto> getSortedBySuggestedPriceByOrder(OrderDto orderDto);

    void chooseSuggestion(int suggestionIdentity, List<SuggestionDto> suggestions);

    void addNewSuggestion(SuggestionDto suggestionDto, OrderDto orderDto);
}
