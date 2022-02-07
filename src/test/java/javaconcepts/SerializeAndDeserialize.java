package javaconcepts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SerializeAndDeserialize {


    public static void main(String[] args) throws IOException {

        serialize();
        serizalizeFakeData();
    }



    public static void serialize() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> students = new ArrayList<>();
        Person person1 = new Person(UUID.randomUUID(),"Bolofinde","Olusegun","bolofbaba@gmail.com","female",30);
        Person person2 = new Person(UUID.randomUUID(),"Bolofinde","Dammy","test01@gmail.com","Male",32);
        Person person3 = new Person(UUID.randomUUID(),"Bolofinde","Adriel","baba@gmail.com","female",5);
        Person person4 = new Person(UUID.randomUUID(),"Adu","Olusegun","segunbaba@gmail.com","Male",34);
        students.add(person1);
        students.add(person2);
        students.add(person3);
        students.add(person4);
        objectMapper.writeValue(new File("src/test/java/javaconcepts/person-serialize.json"),students);

    }

    public static void serizalizeFakeData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Faker faker = new Faker();
        List<Person> students = new ArrayList<>();
        for(int i=0;i<1000;i++){
            Person person  = new Person(UUID.randomUUID(),faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),
                    faker.name().title(),faker.random().nextInt(10,100));
            students.add(person);
        }
        objectMapper.writeValue(new File("src/test/java/javaconcepts/person-fake-data.json"),students);
    }

}
