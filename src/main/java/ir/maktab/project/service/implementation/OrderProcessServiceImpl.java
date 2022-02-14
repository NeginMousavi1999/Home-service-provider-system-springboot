package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.OrderProcessDto;
import ir.maktab.project.data.dto.mapper.OrderMapper;
import ir.maktab.project.data.dto.mapper.OrderProcessMapper;
import ir.maktab.project.data.entity.order.OrderProcess;
import ir.maktab.project.data.repository.OrderProcessRepository;
import ir.maktab.project.exceptions.NotFoundException;
import ir.maktab.project.service.OrderProcessService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class OrderProcessServiceImpl implements OrderProcessService {
    private final OrderProcessRepository processRepository;
    private final Environment environment;

    public void save(OrderProcessDto orderProcessDto) {
        processRepository.save(OrderProcessMapper.mapOrderProcessDtoToOrderProcessForSaving(orderProcessDto));
    }

    public int getActualDurationOfDoingOrderProcess(OrderProcessDto orderProcessDto) {
        return getActualDurationOfWork(orderProcessDto.getStartTime(), orderProcessDto.getFinishTime());
    }

    @Override
    public int getActualDurationOfWork(Date start, Date finish) {
        long durationInMs = finish.getTime() - start.getTime();
        return (int) (durationInMs / (60 * 60 * 1000));
    }

    @Override
    public OrderProcessDto findByOrder(OrderDto orderDto) {
        Optional<OrderProcess> optionalOrderProcess = processRepository
                .findByOrder(OrderMapper.mapOrderDtoToOrderWithId(orderDto));
        if (optionalOrderProcess.isEmpty())
            throw new NotFoundException(environment.getProperty("NO.Order.Process"));
        return OrderProcessMapper.mapOrderProcessToOrderProcessDto(optionalOrderProcess.get());
    }
}
