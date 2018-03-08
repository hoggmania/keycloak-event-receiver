package org.fintrace.keyclock.event.receiver;

import org.fintrace.keycloak.events.service.EventReceiver;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keycloak")
public class KeycloakEventsController {

    private final EventReceiver eventReceiver;

    @Autowired
    public KeycloakEventsController(EventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver;
    }

    @RequestMapping(value = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receiveEvent(Event event) {
        eventReceiver.onEvent(event);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receiveAdminEvent(AdminEvent event) {
        eventReceiver.onAdminEvent(event);
        return ResponseEntity.ok().build();
    }
}
