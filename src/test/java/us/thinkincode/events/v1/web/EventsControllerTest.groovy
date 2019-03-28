package us.thinkincode.events.v1.web

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import  io.micronaut.http.client.HttpClient

class EventsControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer =
            ApplicationContext.run(EmbeddedServer)

    @Shared @AutoCleanup HttpClient client = HttpClient.create(embeddedServer.URL)

    void "test creating events"() {
        expect:
        client.toBlocking()
                .retrieve(HttpRequest.GET('/events')) == ""
    }
}