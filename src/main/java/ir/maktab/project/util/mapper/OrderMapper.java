package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.SuggestionDto;
import ir.maktab.project.data.entity.members.Expert;
import ir.maktab.project.data.entity.order.Order;
import ir.maktab.project.data.entity.order.Suggestion;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
public class OrderMapper {
    private static final int suffix = 1000;

    public static Order mapOrderDtoToOrder(OrderDto orderDto) {
        Set<Suggestion> suggestions = orderDto.getSuggestions().stream()
                .map(SuggestionMapper::mapSuggestionDtoToSuggestionForSaving).collect(Collectors.toSet());

        return Order.builder()
                .id(orderDto.getIdentity() - suffix)
                .address(AddressMapper.mapAddressDtoToAddress(orderDto.getAddress()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(orderDto.getCustomer()))
                .description(orderDto.getDescription())
                .orderStatus(orderDto.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceDtoToSubService(orderDto.getSubService()))
                .finalPrice(orderDto.getFinalPrice())
                .expert(ExpertMapper.mapExpertDtoToExpert(orderDto.getExpert()))
                .registrationDate(orderDto.getRegistrationDate())
                .toBeDoneDate(orderDto.getToBeDoneDate())
                .suggestions(suggestions)
                .build();
    }

    public static Order mapOrderDtoToOrderWithoutSuggestion(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getIdentity() - suffix)
                .address(AddressMapper.mapAddressDtoToAddress(orderDto.getAddress()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(orderDto.getCustomer()))
                .description(orderDto.getDescription())
                .orderStatus(orderDto.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceDtoToSubService(orderDto.getSubService()))
                .finalPrice(orderDto.getFinalPrice())
                .expert(ExpertMapper.mapExpertDtoToExpert(orderDto.getExpert()))
                .registrationDate(orderDto.getRegistrationDate())
                .toBeDoneDate(orderDto.getToBeDoneDate())
                .build();
    }

    public static Order mapOrderDtoToOrderForSavingSuggestion(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getIdentity() - suffix)
                .build();
    }

    public static Order mapOrderDtoToOrderForSaving(OrderDto orderDto) {
        return Order.builder()
                .address(AddressMapper.mapAddressDtoToAddress(orderDto.getAddress()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(orderDto.getCustomer()))
                .description(orderDto.getDescription())
                .orderStatus(orderDto.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceDtoToSubService(orderDto.getSubService()))
                .build();
    }

    public static Order mapOrderDtoToOrderWithId(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getIdentity() - suffix)
                .address(AddressMapper.mapAddressDtoToAddressForSaving(orderDto.getAddress()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(orderDto.getCustomer()))
                .description(orderDto.getDescription())
                .orderStatus(orderDto.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceDtoToSubService(orderDto.getSubService()))
                .build();
    }

    public static OrderDto mapOrderToOrderDto(Order order) {
        Set<SuggestionDto> suggestionsDto = order.getSuggestions().stream()
                .map(SuggestionMapper::mapSuggestionToSuggestionDto).collect(Collectors.toSet());
        return OrderDto.builder()
                .identity(order.getId() + suffix)
                .address(AddressMapper.mapAddressToAddressDto(order.getAddress()))
                .customer(CustomerMapper.mapCustomerToCustomerDto(order.getCustomer()))
                .description(order.getDescription())
                .orderStatus(order.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceToSubServiceDto(order.getSubService()))
                .finalPrice(order.getFinalPrice())
                .expert(ExpertMapper.mapExpertToExpertDto(order.getExpert()))
                .registrationDate(order.getRegistrationDate())
                .toBeDoneDate(order.getToBeDoneDate())
                .suggestions(suggestionsDto)
                .build();
    }

    public static OrderDto mapOrderToOrderDtoWithoutSuggestion(Order order) {
        return OrderDto.builder()
                .identity(order.getId() + suffix)
                .customer(CustomerMapper.mapCustomerToCustomerDto(order.getCustomer()))
                .description(order.getDescription())
                .orderStatus(order.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceToSubServiceDto(order.getSubService()))
                .registrationDate(order.getRegistrationDate())
                .address(AddressMapper.mapAddressToAddressDto(order.getAddress()))
                .build();
    }

    public static OrderDto mapOrderToOrderDtoToPay(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .identity(order.getId() + suffix)
                .customer(CustomerMapper.mapCustomerToCustomerDto(order.getCustomer()))
                .description(order.getDescription())
                .orderStatus(order.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceToSubServiceDto(order.getSubService()))
                .registrationDate(order.getRegistrationDate())
                .address(AddressMapper.mapAddressToAddressDto(order.getAddress()))
                .finalPrice(order.getFinalPrice())
                .build();
        Date toBeDoneDate = order.getToBeDoneDate();
        Expert expert = order.getExpert();
        if (expert != null)
            orderDto.setExpert(ExpertMapper.mapExpertToExpertDto(expert));
        if (toBeDoneDate != null)
            orderDto.setToBeDoneDate(toBeDoneDate);
        return orderDto;
    }

    public static OrderDto mapOrderToOrderDtoToStart(Order order) {
        return OrderDto.builder()
                .identity(order.getId() + suffix)
                .customer(CustomerMapper.mapCustomerToCustomerDto(order.getCustomer()))
                .description(order.getDescription())
                .orderStatus(order.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceToSubServiceDto(order.getSubService()))
                .registrationDate(order.getRegistrationDate())
                .address(AddressMapper.mapAddressToAddressDto(order.getAddress()))
                .toBeDoneDate(order.getToBeDoneDate())
                .build();
    }

    public static OrderDto mapOrderToOrderDtoForToBeSuggestioned(Order order) {
        return OrderDto.builder()
                .identity(order.getId() + suffix)
                .address(AddressMapper.mapAddressToAddressDto(order.getAddress()))
                .customer(CustomerMapper.mapCustomerToCustomerDto(order.getCustomer()))
                .description(order.getDescription())
                .orderStatus(order.getOrderStatus())
                .subService(SubServiceMapper.mapSubServiceToSubServiceDto(order.getSubService()))
                .registrationDate(order.getRegistrationDate())
                .build();
    }
}
