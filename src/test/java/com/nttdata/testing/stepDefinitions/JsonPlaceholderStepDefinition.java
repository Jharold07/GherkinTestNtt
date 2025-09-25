package com.nttdata.testing.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;

import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.rest.SerenityRest;

import com.nttdata.testing.tasks.GetAllUsers;
import com.nttdata.testing.tasks.PostUser;
import com.nttdata.testing.tasks.PutUser;
import com.nttdata.testing.tasks.DeleteUser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JsonPlaceholderStepDefinition {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Before
    public void setup() {
        OnStage.setTheStage(new Cast());
    }

    @Given("el actor establece el endpoint de jsonplaceholder")
    public void elActorEstableceElEndpoint() {
        Actor actor = OnStage.theActorCalled("Tester");
        actor.whoCan(CallAnApi.at(BASE_URL));
    }

    @When("el actor obtiene todos los usuarios")
    public void elActorObtieneTodosLosUsuarios() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetAllUsers.fromAPI());
    }

    @When("el actor crea un post con {string} {string}")
    public void elActorCreaUnPost(String title, String body) {
        OnStage.theActorInTheSpotlight().attemptsTo(PostUser.withData(title, body));
        String id = SerenityRest.lastResponse().jsonPath().getString("id");
        if (id != null) {
            OnStage.theActorInTheSpotlight().remember("PostID", id);
        }
    }

    @When("el actor actualiza el post con {string} {string}")
    public void elActorActualizaElPost(String title, String body) {
        String id = OnStage.theActorInTheSpotlight().recall("PostID");
        if (id == null || Integer.parseInt(id) > 100) {
            id = "1";
            OnStage.theActorInTheSpotlight().remember("PostID", id);
        }
        OnStage.theActorInTheSpotlight().attemptsTo(PutUser.withData(title, body));
    }

    @When("el actor elimina el post")
    public void elActorEliminaElPost() {
        String id = OnStage.theActorInTheSpotlight().recall("PostID");
        if (id == null) id = "1";
        OnStage.theActorInTheSpotlight().remember("PostID", id);
        OnStage.theActorInTheSpotlight().attemptsTo(DeleteUser.byId());
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void assertCodigo(Integer status) {
        assertThat(SerenityRest.lastResponse().statusCode(), is(status));
    }
}
