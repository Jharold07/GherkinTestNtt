package com.nttdata.testing.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostUser implements Task {
    private final String title;
    private final String body;

    public PostUser(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public static PostUser withData(String title, String body) {
        return instrumented(PostUser.class, title, body);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> json = new HashMap<>();
        json.put("title", title);
        json.put("body", body);
        json.put("userId", 1);

        actor.attemptsTo(Post.to("/posts").with(req -> req.body(json).relaxedHTTPSValidation().log().all()));
    }
}
