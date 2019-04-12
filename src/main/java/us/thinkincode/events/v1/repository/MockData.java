package us.thinkincode.events.v1.repository;

import io.micronaut.context.annotation.Context;
import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.SignupUser;
import us.thinkincode.events.v1.domain.Task;
import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;
import us.thinkincode.events.v1.service.InMemoryAccountServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static us.thinkincode.events.v1.repository.InMemoryMappings.*;

@Context
public class MockData {
    static {
        var createDate = LocalDateTime.of(2019, 3, 20, 11, 0);

        var id1 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b491";
        var employeeEntityCatalog = new EntityCatalogItem(id1, "Employee", "System", createDate);
        ENTITIES_MASTER_CATALOG.put(id1, employeeEntityCatalog);

        var id2 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b492";
        var buildingEntityCatalog = new EntityCatalogItem(id2, "Building", "System", createDate);
        ENTITIES_MASTER_CATALOG.put(id2, buildingEntityCatalog);

        var id3 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b493";
        EVENTS_MASTER_CATALOG.put(id3, new EventCatalogItem(id3, "Hired", employeeEntityCatalog, "System", createDate, List.of(
                new Task("0dd5bdbf-40b7-48d2-aa64-8fe6f970b49_EmpHired1", "Send notification", "category1", "_for", "", false)
        )));

        var id4 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b494";
        EVENTS_MASTER_CATALOG.put(id4, new EventCatalogItem(id4, "Promoted", employeeEntityCatalog, "System", createDate, List.of(
                new Task("0dd5bdbf-40b7-48d2-aa64-8fe6f970b49_EmpPromo1", "Send notification", "category1", "_for", "", false)
        )));

        var id5 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b495";
        EVENTS_MASTER_CATALOG.put(id5, new EventCatalogItem(id5, "Employee appreciation day preparation", buildingEntityCatalog, "System", createDate, List.of(
                new Task("0dd5bdbf-40b7-48d2-aa64-8fe6f970b49_EmpApp1", "Prepare food", "category1", "_for", "", false),
                new Task("0dd5bdbf-40b7-48d2-aa64-8fe6f970b49_EmpApp2", "Send notifications to employees", "category1", "_for", "", false)
        )));

        var id6 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b496";
        var drillPrepEventCatalogItem = new EventCatalogItem(id6, "Fire Drill preparation", buildingEntityCatalog, "System", createDate, List.of(
                new Task("0dd5bdbf-40b7-48d2-aa64-8fe6f970b49_drillt1", "Ring alarm ", "category1", "_for", "", false),
                new Task("0dd5bdbf-40b7-48d2-aa64-8fe6f970b49_drillt2", "Notify coordinators", "category1", "_for", "", false)
        ));
        EVENTS_MASTER_CATALOG.put(id6, drillPrepEventCatalogItem);

        USERS = new HashMap<>();

        var id7 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b497";
        var user1 = new SignupUser(
                id7,
                "user",
                "$2a$10$W9zDR0Epf5La/0yGqlbgoOmVSWI9K/moJXgPmqTkPIkpRilHm0nCS", //password
                "John", "Perez",
                "4041234567");
        USERS.put(id7, user1);
        InMemoryAccountServiceImpl.onboard(id7);

        var id9 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b499";
        var id10 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b410";
        var id11 = "0dd5bdbf-40b7-48d2-aa64-8fe6f970b411";

        var userUserEvents = new ArrayList<Event>();
        userUserEvents.add(
                new Event(id3, "Hired", employeeEntityCatalog.getId(), employeeEntityCatalog.getName(), "System", createDate, List.of(
                    new Task(id9, "task1", "category1", "_for", "responsible", false),
                    new Task(id10, "task2", "category1", "_for", "responsible", false)
        )));

        userUserEvents.add(new Event(id6, "Drill preparation", buildingEntityCatalog.getId(), employeeEntityCatalog.getName(), "System", createDate, List.of(
                new Task(id11, "drilltask1", "category1", "_for", "responsible", false)
        )));

        ACCOUNT_EVENTS.put(id7, userUserEvents);

    }

}
