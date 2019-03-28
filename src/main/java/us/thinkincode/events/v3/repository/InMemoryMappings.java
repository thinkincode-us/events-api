package us.thinkincode.events.v3.repository;

import us.thinkincode.events.v3.domain.Entity;
import us.thinkincode.events.v3.domain.Event;
import us.thinkincode.events.v3.domain.SignupUser;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryMappings {

    public static Map<String, Entity> ENTITIES = new HashMap<>();
    public static Map<String, Event> EVENTS = new HashMap<>();
    public static Map<String, SignupUser> USERS;

    static {
        var createDate = LocalDateTime.of(2019, 3, 20, 11, 0);

        var id1 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b491";
        var employeeEntity = new Entity(id1, "Employee", "System", createDate);
        ENTITIES.put(id1, employeeEntity);

        var id2 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b492";
        var buildingEntity = new Entity(id2, "Building", "System", createDate);
        ENTITIES.put(id2, buildingEntity);

        var id3 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b493";
        EVENTS.put(id3, new Event(id3, "Hired", employeeEntity, "System", createDate));

        var id4 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b494";
        EVENTS.put(id4, new Event(id4, "Promoted", employeeEntity, "System", createDate));

        var id5 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b495";
        EVENTS.put(id5, new Event(id5, "Employee appreciation day preparation", buildingEntity, "System", createDate));

        var id6 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b496";
        EVENTS.put(id6, new Event(id6, "Drill preparation", buildingEntity, "System", createDate));

        USERS = new HashMap<>();

        var id7 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b497";
        var user1 = new SignupUser(
                id7,
                "user",
                "$2a$10$W9zDR0Epf5La/0yGqlbgoOmVSWI9K/moJXgPmqTkPIkpRilHm0nCS", //password
                "John", "Perez",
                "4041234567");
        USERS.put(id7, user1);
    }
}
