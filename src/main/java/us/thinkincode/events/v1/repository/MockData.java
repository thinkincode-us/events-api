package us.thinkincode.events.v1.repository;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Context;
import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.SignupUser;
import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;
import us.thinkincode.events.v1.service.InMemoryAccountServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static us.thinkincode.events.v1.repository.InMemoryMappings.*;

@Context
public class MockData {
    static {
        ENTITIES_MASTER_CATALOG = readObject("entities_master_catalog.json", new TypeReference<Map<String, EntityCatalogItem>>() {});
        EVENTS_MASTER_CATALOG = readObject("events_master_catalog.json", new TypeReference<Map<String, EventCatalogItem>>() {});
        USERS = readObject("users.json", new TypeReference<Map<String, SignupUser>>() {});
        ACCOUNT_EVENTS = readObject("account_events.json", new TypeReference<Map<String, List<Event>>>() {});

        InMemoryAccountServiceImpl.onboard(USERS.values()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User not found")).getId()
        );

    }

    public static void main(String[] args) {

    }

    static void writeJsontoConsole(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        out.println("");
        try {
            mapper.writeValue(out, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static <T> T readObject(String json, TypeReference typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        try {
            return mapper.readValue(MockData.class.getResourceAsStream(json), typeReference);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
