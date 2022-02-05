package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.FeedbackDto;
import ir.maktab.project.data.entity.order.Feedback;

/**
 * @author Negin Mousavi
 */
public class FeedbackMapper {
    private static final int suffix = 1000;

    public static Feedback mapFeedbackDtoToFeedback(FeedbackDto feedbackDto) {
        return Feedback.builder()

                .build();
    }

    public static Feedback mapFeedbackDtoToFeedbackForSaving(FeedbackDto feedbackDto) {
        String comment = feedbackDto.getComment();
        String score = feedbackDto.getScore();
        Feedback feedback = Feedback.builder()
                .expert(ExpertMapper.mapExpertDtoToExpert(feedbackDto.getExpert()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(feedbackDto.getCustomer()))
                .order(OrderMapper.mapOrderDtoToOrderWithId(feedbackDto.getOrder()))
                .build();
        if (comment != null)
            feedback.setComment(comment);
        if (score != null)
            feedback.setScore(Double.parseDouble(score));
        return feedback;
    }

    public static FeedbackDto mapFeedbackToFeedbackDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        String comment = feedback.getComment();
        if (comment != null)
            feedbackDto.setComment(comment);
        feedbackDto.setScore(String.valueOf(feedback.getScore()));
        return feedbackDto;
    }
}
