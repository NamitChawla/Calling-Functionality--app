package com.example.namit.adddata;

/**
 * Created by Namit on 20-06-2017.
 */
public class Pets
{
    private String animalId,animalName,animalType;

    Pets()
    {

    }

    public Pets(String animalId, String animalName, String animalType) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.animalType = animalType;
    }

    public String getAnimalId() {
        return animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }
}
