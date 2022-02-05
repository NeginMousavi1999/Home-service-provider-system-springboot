package ir.maktab.project.service;

import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.FeedbackDto;
import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.entity.order.Feedback;

/**
 * @author Negin Mousavi
 */
public interface FeedbackService {
    void save(Feedback feedback);

    Long getCountOfRecords();

    void addFeedback(FeedbackDto feedbackDto, String score, String comment);

    FeedbackDto getByExpertAndOrder(ExpertDto expert, OrderDto order);
}
