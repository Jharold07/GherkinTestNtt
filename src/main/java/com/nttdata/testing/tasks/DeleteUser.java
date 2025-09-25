package com.nttdata.testing.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {
    public static DeleteUser byId() {
        return instrumented(DeleteUser.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String id = actor.recall("PostID");
        actor.attemptsTo(Delete.from("/posts/" + id).with(req -> req.relaxedHTTPSValidation().log().all()));
    }
}
