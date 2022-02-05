package ir.maktab.project.service;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.PaymentDto;

/**
 * @author Negin Mousavi
 */
public interface PaymentService {
    CustomerDto pay(PaymentDto paymentDto);
}
