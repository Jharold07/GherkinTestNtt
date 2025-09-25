package com.nttdata.testing.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutUser implements Task {
    private final String title;
    private final String body;

    public PutUser(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public static PutUser withData(String title, String body) {
        return instrumented(PutUser.class, title, body);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String id = actor.recall("PostID");

        if (id == null || Integer.parseInt(id) > 100) {
            id = "1"; // usar un id válido de JSONPlaceholder
            actor.remember("PostID", id);
        }

        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("title", title);
        json.put("body", body);
        json.put("userId", 1);

        actor.attemptsTo(
                Put.to("/posts/" + id)
                        .with(req -> req.body(json).relaxedHTTPSValidation().log().all())
        );
    }

}
