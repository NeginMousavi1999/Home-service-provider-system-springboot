package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.PaymentDto;
import ir.maktab.project.data.entity.order.Payment;

/**
 * @author Negin Mousavi
 */
public class PaymentMapper {
    public static Payment mapPaymentDtoToPaymentForSaving(PaymentDto paymentDto) {
        OrderDto orderDto = paymentDto.getOrder();
        CustomerDto customerDto = paymentDto.getCustomerDto();
        Payment payment = Payment.builder()
                .cardNumber(paymentDto.getCardNumber())
                .paymentMethod(paymentDto.getPaymentMethod())
                .build();
        if (orderDto != null)
            payment.setOrder(OrderMapper.mapOrderDtoToOrderWithoutSuggestion(orderDto));
        if (customerDto != null)
            payment.setCustomer(CustomerMapper.mapCustomerDtoToCustomer(customerDto));
        return payment;
    }
}
