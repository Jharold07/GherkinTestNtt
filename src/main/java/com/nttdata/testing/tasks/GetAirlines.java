package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetAirlines implements Task {

    private final String endpoint;

    public GetAirlines(String endpoint) {
        this.endpoint = endpoint;
    }

    public static Performable fromEndpoint(String endpoint){
        return instrumented(GetAirlines.class, endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint).
                        with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .log().all()));
    }
}
