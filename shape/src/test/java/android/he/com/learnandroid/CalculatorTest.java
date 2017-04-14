package android.he.com.learnandroid;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/13.
 */
public class CalculatorTest {
    private  Calculator calculator;
    @Before
    public void setUp() throws Exception {
        System.err.println("setUp: ");
        calculator=new Calculator();
    }

    @After
    public void tearDown() throws Exception {
        System.err.println("tearDown: ");
        calculator=null;
    }

    @Test
    public void sum() throws Exception {
        Assert.assertEquals(5,calculator.sum(2,2),3);

    }

    @Test
    public void substract() throws Exception {

    }

    @Test
    public void divide() throws Exception {

    }

    @Test
    public void multiply() throws Exception {

    }

}