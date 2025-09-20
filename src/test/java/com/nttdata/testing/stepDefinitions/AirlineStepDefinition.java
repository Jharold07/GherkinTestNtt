package com.nttdata.testing.stepDefinitions;

import com.nttdata.testing.questions.ResponseCode;
import com.nttdata.testing.tasks.GetAirlines;
import com.nttdata.testing.tasks.PostAirline;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class AirlineStepDefinition {

    public static Logger LOGGER = LoggerFactory.getLogger(AirlineStepDefinition.class);
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint para obtener las aerolineas")
    public void elActorEstableceElEndpointParaObtenerLasAerolineas(Actor actor) {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el {actor} envia una solicitud GET")
    public void elActorEnviaUnaSolicitudGET(Actor actor) {
        actor.attemptsTo(GetAirlines.fromEndpoint("/airlines"));
    }

    @Then("el codigo de respuesta deberia ser {int}")
    public void elCodigoDeRespuestaDeberiaSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El código de respuesta", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @Given("el {actor} establece el endpoint POST para crear una aerolinea")
    public void elActorEstableceElEndpointPOSTParaCrearUnaAerolinea(Actor actor) {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el envia una solicitud HTTP POST con el {string} {string} {string} {string} {string} {string} {string} {string}")
    public void elEnviaUnaSolicitudHTTPPOSTConEl(String _id, String name, String country, String logo, String slogan, String head_quaters, String website, String established) {
        theActorInTheSpotlight().attemptsTo(PostAirline.fromPage(_id, name, country, logo, slogan, head_quaters, website, established));
    }

}
