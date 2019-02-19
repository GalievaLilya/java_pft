package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testDist() {
        PointSecond p = new PointSecond(2,0);
        Assert.assertEquals(p.distanceSecond(2,0),0.0);
        System.out.println("First test - done!");

    }
}
