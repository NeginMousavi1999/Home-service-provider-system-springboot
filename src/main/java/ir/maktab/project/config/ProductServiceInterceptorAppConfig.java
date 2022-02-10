package ir.maktab.project.config;

import ir.maktab.project.controller.LastViewInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Negin Mousavi
 */
@Component
@RequiredArgsConstructor
public class ProductServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    private final LastViewInterceptor lastViewInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lastViewInterceptor);
    }
}
