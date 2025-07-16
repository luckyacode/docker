package com.praveen.docker;

import java.lang.reflect.*;

public class ReflectionExample {
    public static void main(String[] args) throws Exception {


        Class<?> clazz = Class.forName("Person");

        // Access private constructor
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true); // Enable access
        Object person = constructor.newInstance();

        // Access and modify private field "name"
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Alice");

        // Access private final field "age"
        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        System.out.println("Original age: " + ageField.get(person));
        ageField.set(person, 35); // May not actually change value
        System.out.println("Modified age: " + ageField.get(person)); // Often remains unchanged due to final

        // Access private static final field "COUNTRY"
        Field countryField = clazz.getDeclaredField("COUNTRY");
        countryField.setAccessible(true);
        System.out.println("Original COUNTRY: " + countryField.get(null));

        // Attempting to modify a static final field (won't work without unsafe hacks)
        try {
            countryField.set(null, "USA"); // No effect in Java 12+
        } catch (IllegalAccessException e) {
            System.out.println("Modification blocked: " + e);
        }

        System.out.println("After attempt, COUNTRY: " + countryField.get(null));

        // Call private method greet()
        Method greetMethod = clazz.getDeclaredMethod("greet", String.class);
        greetMethod.setAccessible(true);
        greetMethod.invoke(person, "Hello");

        // Print final state
        System.out.println(person);
    }
}

class Person {
    private String name = "John Doe";
    private static final String COUNTRY = "India";
    private final int age = 30;

    private Person() {
        System.out.println("Private constructor called");
    }

    private void greet(String message) {
        System.out.println("Greeting: " + message + ", " + name);
    }

    @Override
    public String toString() {
        return "Person{name='%s', age=%d, country='%s'}"
                .formatted(name, age, COUNTRY);
    }
}
