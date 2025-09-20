package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostAirline implements Task {

    private final String _id, name, country, logo, slogan, head_quaters, website, established;

    public PostAirline(String _id, String name, String country, String logo, String slogan, String head_quaters, String website, String established) {
        this._id = _id;
        this.name = name;
        this.country = country;
        this.logo = logo;
        this.slogan = slogan;
        this.head_quaters = head_quaters;
        this.website = website;
        this.established = established;
    }

    public static Performable fromPage(String _id, String name, String country, String logo, String slogan, String head_quaters, String website, String established) {
        return instrumented(PostAirline.class, _id, name, country, logo, slogan, head_quaters, website, established);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/airlines").with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).
                body("{\"_id\":\"" + _id + "\","
                        + "\"name\":\"" + name + "\","
                        + "\"country\":\"" + country + "\","
                        + "\"logo\":\"" + logo + "\","
                        + "\"slogan\":\"" + slogan + "\","
                        + "\"head_quaters\":\"" + head_quaters + "\","
                        + "\"website\":\"" + website + "\","
                        + "\"established\":\"" + established + "\"}")
                .log().all()));
    }
}
