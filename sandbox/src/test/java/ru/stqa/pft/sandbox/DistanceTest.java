package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testDist() {
        PointSecond p = new PointSecond(2,2, 2, 2);
        Assert.assertEquals(p.distanceSecond(),0.0);
        System.out.println("First test - done!");

        PointSecond p1 = new PointSecond(2,2, 2, 2);
        Assert.assertEquals(p1.distanceSecond(),0.0);
        System.out.println("Second test - done!");

    }
}
