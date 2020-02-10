package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.session.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class VoorstellingControllerTest {
    private VoorstellingController voorstellingController;
    @Mock
    private VoorstellingService voorstellingService;
    @Mock
    private Mandje mandje;
    @Mock
    private Pageable pageable;

    @Before
    public void before () {
        when(voorstellingService.findById(1)).thenReturn(Optional.of(
                new Voorstelling("test", "test", LocalDateTime.now(),
                        new Genre("test"), BigDecimal.ONE, 10, 1)));
        voorstellingController = new VoorstellingController(voorstellingService, mandje);
        pageable = null;
    }

    // voorstellingen
    @Test
    public void voorstellingenGebruiktDeThymeleafPaginaVoorstellingen() {
        assertThat(voorstellingController.voorstellingen(pageable).getViewName()).isEqualTo("voorstellingen");
    }

    @Test
    public void voorstellingenGeeftVoorstellingenDoorAanDeThymeleafPagina() {
        assertThat(voorstellingController.voorstellingen(pageable).getModel().get("voorstellingen")).isInstanceOf(List.class);
    }

    // voorstelling
    @Test
    public void voorstellingGebruiktDeThymeleafPaginaVoorstelling() {
        assertThat(voorstellingController.voorstelling(1).getViewName()).isEqualTo("voorstelling");
    }

    @Test
    public void voorstellingGeeftGevondenVoorstellingDoorAanDeThymeleafPagina() {
        Voorstelling voorstelling = (Voorstelling) voorstellingController.voorstelling(1).getModel().get("voorstelling");
        assertThat(voorstelling.getId()).isEqualTo(1);
    }

    @Test
    public void voorstellingGeeftOnbestaandeVoorstellingNietDoorAanDeThymeleafPagina() {
        assertThat(voorstellingController.voorstelling(-1).getModel()).doesNotContainKeys("voorstelling");
    }
}
