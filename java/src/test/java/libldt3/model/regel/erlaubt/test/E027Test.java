package libldt3.model.regel.erlaubt.test;

import libldt3.model.regel.erlaubt.E027;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class E027Test {

    @Test
    public void testPositive() {
        Assertions.assertTrue(new E027().isValid("01"));
        Assertions.assertTrue(new E027().isValid("09"));
        Assertions.assertTrue(new E027().isValid("12"));
        Assertions.assertTrue(new E027().isValid("14"));
        Assertions.assertTrue(new E027().isValid("18"));
        Assertions.assertTrue(new E027().isValid(null));
    }

    @Test
    public void testNegative() {
        Assertions.assertFalse(new E027().isValid("00"));
        Assertions.assertFalse(new E027().isValid("13"));
        Assertions.assertFalse(new E027().isValid("15"));
        Assertions.assertFalse(new E027().isValid("99"));
        Assertions.assertFalse(new E027().isValid(UUID.randomUUID().toString()));
        Assertions.assertFalse(new E027().isValid(""));
    }

}
