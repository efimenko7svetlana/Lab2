package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    public void testRToString() {
        Money money= new Money(1000, Money.Measure.r);
        assertEquals("1000.0 рублей", money.toString());
    }

    @Test
    public void testDToString() {
        Money length = new Money(150, Money.Measure.d);
        assertEquals("150.0 долларов", length.toString());
    }

    @Test
    public void testEToString() {
        Money length = new Money(100, Money.Measure.e);
        assertEquals("100.0 евро", length.toString());
    }
    @Test
    public void testAddNumber() {
        Money money = new Money(1000, Money.Measure.r);
        money = money.add(200.5);
        assertEquals("1200.5 рублей", money.toString());
    }
    @Test
    public void testSubtractNumber() {
        Money money = new Money(100, Money.Measure.d);
        money = money.subtract(50);
        assertEquals("50.0 долларов", money.toString());
    }
    @Test
    public void testMultiplByNumber() {
        Money money = new Money(100, Money.Measure.e);
        money = money.multiply(5);
        assertEquals("500.0 евро", money.toString());
    }
    @Test
    public void testConvertRubToAny() {
        Money money;

        money = new Money(1, Money.Measure.r);
        assertEquals("0.012861736334405145 долларов", money.to(Money.Measure.d).toString());

        money = new Money(1, Money.Measure.r);
        assertEquals("0.010956502684343158 евро", money.to(Money.Measure.e).toString());
    }
    @Test
    public void testConvertAnyToRub() {
        Money money;

        money = new Money(1, Money.Measure.d);
        assertEquals("77.75 рублей", money.to(Money.Measure.r).toString());

        money = new Money(1, Money.Measure.e);
        assertEquals("91.27 рублей", money.to(Money.Measure.r).toString());
    }
    @Test
    public void testAddTwoMoneys() {
        Money money1 = new Money(10, Money.Measure.d);
        Money money2 = new Money(77.75, Money.Measure.r);

        assertEquals(money1.add(money2), new Money(11, Money.Measure.d));
        assertEquals(money1.add(money2), new Money(855.25, Money.Measure.r));
    }

    @Test
    public void testSubtractTwoMoneys() {
        Money money1 = new Money(10, Money.Measure.d);
        Money money2 = new Money(77.75, Money.Measure.r);

        assertEquals(money1.subtract(money2), new Money(9, Money.Measure.d));
        assertEquals(money1.subtract(money2), new Money(699.75, Money.Measure.r));
    }
}