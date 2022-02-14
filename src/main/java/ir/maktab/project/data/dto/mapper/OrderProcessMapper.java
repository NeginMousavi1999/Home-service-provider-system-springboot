package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.OrderProcessDto;
import ir.maktab.project.data.entity.order.OrderProcess;

/**
 * @author Negin Mousavi
 */
public class OrderProcessMapper {
    private static final int suffix = 1000;

    public static OrderProcess mapOrderProcessDtoToOrderProcess(OrderProcessDto processDto) {
        return OrderProcess.builder()
                .id(processDto.getIdentity() - suffix)
                .finishTime(processDto.getFinishTime())
                .startTime(processDto.getStartTime())
                .order(OrderMapper.mapOrderDtoToOrderWithoutSuggestion(processDto.getOrder()))
                .build();
    }

    public static OrderProcess mapOrderProcessDtoToOrderProcessForSaving(OrderProcessDto processDto) {
        return OrderProcess.builder()
                .finishTime(processDto.getFinishTime())
                .startTime(processDto.getStartTime())
                .order(OrderMapper.mapOrderDtoToOrderWithoutSuggestion(processDto.getOrder()))
                .build();
    }

    public static OrderProcessDto mapOrderProcessToOrderProcessDto(OrderProcess process) {
        return OrderProcessDto.builder()
                .identity(process.getId() + suffix)
                .finishTime(process.getFinishTime())
                .startTime(process.getStartTime())
                .order(OrderMapper.mapOrderToOrderDtoToPay(process.getOrder()))
                .build();
    }
}
