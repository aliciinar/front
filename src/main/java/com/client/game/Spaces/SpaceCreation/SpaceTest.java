package com.client.game.Spaces.SpaceCreation;

public class SpaceTest {


        public static void main(String args[])  //static method
        {
            SpaceCreator spaceCreator = new SpaceCreator(new NormalCreation());
            spaceCreator.CreateSpaces();

            System.out.println("Static method");
        }

}
