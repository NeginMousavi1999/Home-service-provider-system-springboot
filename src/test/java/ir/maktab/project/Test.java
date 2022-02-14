package ir.maktab.project;

import ir.maktab.project.util.GenerateDate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@SpringBootTest
public class Test {
    @org.junit.jupiter.api.Test
    public void dateTest() {
        Date date1 = GenerateDate.generateByPattern("yyyy-MM-dd", "2022-02-01");
        Date date2 = GenerateDate.generateByPattern("yyyy-MM-dd", "2022-02-02");
        long l = date1.getTime() - date2.getTime();
        System.out.println(l);
    }
}
