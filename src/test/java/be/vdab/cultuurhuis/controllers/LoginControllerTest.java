package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Adres;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.services.KlantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    private LoginController loginController;
    @Mock
    private KlantService klantService;
    private Klant klant;

     @Before
    public void before() {
         when(klantService.findById(1))
                 .thenReturn(Optional.of(new Klant("test", "test",
                         new Adres("test", "test", "test", "test"),
                         "test", "test")));
         loginController = new LoginController(klantService);
     }

    @Test
    public void loginGebruiktDeThymeleafPaginaLogin() {
        assertThat(loginController.login().getViewName()).isEqualTo("login");
    }

    @Test
    public void loginGeeftKlantDoorAanDeThymeleafPagina() {
        assertThat(loginController.login().getModel().get("klant")).isInstanceOf(Klant.class);
    }
}
