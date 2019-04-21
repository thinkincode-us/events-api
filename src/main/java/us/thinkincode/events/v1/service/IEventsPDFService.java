package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.Event;

import java.io.InputStream;
import java.util.List;

interface IEventsPDFService {

    InputStream toPdf(List<Event> events);
}
