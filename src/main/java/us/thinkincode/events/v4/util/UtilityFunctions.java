package us.thinkincode.events.v4.util;

import java.util.UUID;
import java.util.function.Supplier;

public class UtilityFunctions {

    public static final Supplier<String> generateUUID = () -> UUID.randomUUID().toString();
}
