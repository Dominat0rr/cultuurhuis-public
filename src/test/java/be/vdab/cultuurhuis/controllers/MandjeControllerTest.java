package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.session.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class MandjeControllerTest {
    private MandjeController mandjeController;
    @Mock
    VoorstellingService voorstellingService;
    @Mock
    Mandje mandje;
    @Mock
    MockHttpSession session;
    @Mock
    RedirectAttributes redirect;
    Optional<List<String>> optionalVoorstellingId;

    @Before
    public void before() {
        mandjeController = new MandjeController(mandje, voorstellingService);
        session = new MockHttpSession();
        session.setAttribute("mandje", mandje);
        redirect = null;
        optionalVoorstellingId.get().add("1");
    }

    @Test
    public void mandjeGebruiktDeThymeleafPaginaMandje() {
        assertThat(mandjeController.mandje().getViewName()).isEqualTo("mandje");
    }

    @Test
    public void verwijderenGebruiktDeThymeleafPaginaVerwijderen() {
        assertThat(mandjeController.verwijderen(optionalVoorstellingId, redirect).equals("verwijderen"));
    }
}
