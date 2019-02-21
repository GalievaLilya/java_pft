package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testDist() {
        PointSecond p = new PointSecond(2,0);
        PointSecond p1 = new PointSecond(2,0);
        Assert.assertEquals(p.distanceSecond(p1),0.0);
        System.out.println("First test - done!");

    }
}
