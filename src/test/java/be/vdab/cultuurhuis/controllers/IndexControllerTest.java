package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.services.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
public class IndexControllerTest {
    private IndexController indexController;
    @Mock
    private GenreService genreService;

    @Before
    public void before() {
        when(genreService.findById(1))
                .thenReturn(Optional.of(new Genre("test")));
        indexController = new IndexController(genreService);
    }

    // genres
    @Test
    public void genresGebruiktDeThymeleafPaginaIndex() {
        assertThat(indexController.index().getViewName()).isEqualTo("index");
    }

    @Test
    public void genresGeeftGenresDoorAanDeThymeleafPagina() {
        assertThat(indexController.index().getModel().get("genres")).isInstanceOf(List.class);
    }

    @Test
    public void genreGebruiktDeThymeleafPaginaGenre() {
        assertThat(indexController.genre(1).getViewName()).isEqualTo("genre");
    }
}
