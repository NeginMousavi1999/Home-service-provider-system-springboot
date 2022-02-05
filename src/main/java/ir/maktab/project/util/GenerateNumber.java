package ir.maktab.project.util;

import lombok.Getter;

import java.util.Random;

/**
 * @author Negin Mousavi
 */
@Getter
public class GenerateNumber {
    private static final Random random = new Random();
    private static final int randomInt = random.nextInt(1000000) + 1;
}
