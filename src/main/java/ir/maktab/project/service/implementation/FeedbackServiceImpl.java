package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.FeedbackDto;
import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.mapper.ExpertMapper;
import ir.maktab.project.data.dto.mapper.FeedbackMapper;
import ir.maktab.project.data.dto.mapper.OrderMapper;
import ir.maktab.project.data.entity.order.Feedback;
import ir.maktab.project.data.enumuration.OrderStatus;
import ir.maktab.project.data.repository.FeedbackRepository;
import ir.maktab.project.exception.HomeServiceException;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.FeedbackService;
import ir.maktab.project.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Getter
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ExpertService expertService;
    private final OrderService orderService;

    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public Long getCountOfRecords() {
        return feedbackRepository.count();
    }

    @Override
    public void addFeedback(FeedbackDto feedbackDto, String stringScore, String comment) {
        double score;
        if (stringScore.length() != 0) {
            feedbackDto.setScore(stringScore);
            score = Double.parseDouble(stringScore);
            expertService.updateScore(feedbackDto.getExpert(), score);
        }
        if (comment.length() != 0)
            feedbackDto.setComment(comment);
        feedbackRepository.save(FeedbackMapper.mapFeedbackDtoToFeedbackForSaving(feedbackDto));
        OrderDto orderDto = feedbackDto.getOrder();
        orderDto.setOrderStatus(OrderStatus.FEEDEDBACK);
        orderService.updateStatus(orderDto);
    }

    @Override
    public FeedbackDto getByExpertAndOrder(ExpertDto expert, OrderDto order) {
        Optional<Feedback> optionalFeedback = feedbackRepository
                .findByExpertAndOrder(ExpertMapper.mapExpertDtoToExpert(expert), OrderMapper.mapOrderDtoToOrderWithId(order));
        if (optionalFeedback.isEmpty())
            throw new HomeServiceException("no feedback!");
        return FeedbackMapper.mapFeedbackToFeedbackDto(optionalFeedback.get());
    }
}
