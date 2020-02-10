package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.session.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ReservatieControllerTest {
    private ReservatieController reservatieController;
    @Mock
    private ReservatieService reservatieService;
    @Mock
    private KlantService klantService;
    @Mock
    private VoorstellingService voorstellingService;
    @Mock
    private Mandje mandje;

    @Before
    public void before() {
        reservatieController = new ReservatieController(reservatieService, voorstellingService,
                klantService, mandje);
    }

    @Test
    public void reservatieToevoegenGebruiktDeThymeleafPaginaToevoegen() {
        assertThat(reservatieController.toevoegen().getViewName()).isEqualTo("toevoegen");
    }

    @Test
    public void reservatieBevestigenGebruiktDeThymeleafPaginaBevestigen() {
        assertThat(reservatieController.create(null, null, null).getViewName()).isEqualTo("create");
    }
}
