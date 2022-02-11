package ir.maktab.project.controller;

import ir.maktab.project.data.dto.NewOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Negin Mousavi
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    @PostMapping("/doAddOrder")
    public String doAddOrder(@RequestBody NewOrderDto orderDto) {
        return "";
    }
}
