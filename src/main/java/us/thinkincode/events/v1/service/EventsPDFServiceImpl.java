package us.thinkincode.events.v1.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import us.thinkincode.events.v1.domain.CreatedObj;
import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.Task;

import javax.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.joining;

@Singleton
class EventsPDFServiceImpl implements IEventsPDFService {

    private static final List<String> HEADERS = List.of("Name", "Created on", "Tasks");

    public InputStream toPdf(List<Event> events) {

        var outputStream = new ByteArrayOutputStream();
        Document document = new Document(new PdfDocument(new PdfWriter(outputStream)));

        Table table =  new Table(HEADERS.size(), true);

        HEADERS.forEach(header -> {
            addHeader.accept(table, header);
        });

        document.add(table);

        events.forEach(event -> {
            addCell.accept(table, event.getName());
            addCell.accept(table, event.getCreated().getDate().toString());
            addCell.accept(table, event.getTasks().stream()
                    .map(task -> task.getName() + " " + (task.isComplete() ? "completed" : "pending"))
                    .collect(joining("\n")));
        });

        table.complete();
        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    BiConsumer<Table, String> addCell = (table, val) -> table.addCell(
            new Cell().setKeepTogether(true).add(new Paragraph(val).setMargins(0, 0, 0, 0))
    );

    BiConsumer<Table, String> addHeader = (table, val) -> table.addCell(
            new Cell().setKeepTogether(true).add(new Paragraph(val))
    );

    public static void main(String[] args) {
        var event1 = new Event();
        event1.setName("name1 ");
        event1.setCreated(new CreatedObj("admin", LocalDateTime.now()));
        event1.setTasks(List.of(
                new Task("123", "task1", "category", "for ", "responsible", false),
                new Task("124", "task2", "category", "for ", "responsible", false)
        ));

        InputStream inputStream = new EventsPDFServiceImpl().toPdf(List.of(event1));

        try (FileOutputStream fileOutputStream = new FileOutputStream("abc.pdf")) {
            fileOutputStream.write(inputStream.readAllBytes());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
