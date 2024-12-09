package org.example;

/**
 *  Your Name: Seán Afolabi
 *  Class Group: SD2B
 */
public class Question1
{
    public static void main(String[] args)
    {
        System.out.println("Question 1");

        // create container here...
        ContainerManager manager = new ContainerManager();

        // Adding containers
        manager.add(new Box(6.5, 8.0, 9.0, 10));
        manager.add(new Cylinder(7.0, 4.0, 12.0));
        manager.add(new Pyramid(6.0, 6.0, 10));

        System.out.println("Total Weight of all shapes: " + manager.totalWeight());
        System.out.println("Total Rectangular Volume of all shapes: " + manager.totalRectangularVolume());

        System.out.println("All of the Conatiners: " + manager.getAllContainers());

        manager.clearAll();
        System.out.println("All containers cleared: " + manager.getAllContainers());
    }
}



