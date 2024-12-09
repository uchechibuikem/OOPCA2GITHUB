package org.example;

import java.util.Objects;

public class DistanceTo implements Comparable<DistanceTo> {
    private String target;
    private int distance;

    public DistanceTo(String city, int dist)
    {
        target = city;
        distance = dist;
    }
    public String getTarget()
    {
        return target;
    }
    public int getDistance()
    {
        return distance;
    }
    public int compareTo(DistanceTo other)
    {
        return distance - other.distance;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        DistanceTo that = (DistanceTo) o;
        return distance == that.distance && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(target, distance);
    }
}
