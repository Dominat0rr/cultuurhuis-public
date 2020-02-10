package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.forms.KlantRegistratieForm;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.session.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistratieControllerTest {
    private RegistratieController registratieController;
    @Mock
    private KlantService klantService;
    @Mock
    private Mandje mandje;
    @Mock
    RedirectAttributes redirect;
    @Mock
    Errors errors;
    @Mock
    HttpServletRequest request;

    @Before
    public void before() {
        registratieController = new RegistratieController(klantService, mandje);
        redirect = null;
        errors = new Errors() {
            @Override
            public String getObjectName() {
                return null;
            }

            @Override
            public void setNestedPath(String s) {

            }

            @Override
            public String getNestedPath() {
                return null;
            }

            @Override
            public void pushNestedPath(String s) {

            }

            @Override
            public void popNestedPath() throws IllegalStateException {

            }

            @Override
            public void reject(String s) {

            }

            @Override
            public void reject(String s, String s1) {

            }

            @Override
            public void reject(String s, Object[] objects, String s1) {

            }

            @Override
            public void rejectValue(String s, String s1) {

            }

            @Override
            public void rejectValue(String s, String s1, String s2) {

            }

            @Override
            public void rejectValue(String s, String s1, Object[] objects, String s2) {

            }

            @Override
            public void addAllErrors(Errors errors) {

            }

            @Override
            public boolean hasErrors() {
                return false;
            }

            @Override
            public int getErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getAllErrors() {
                return null;
            }

            @Override
            public boolean hasGlobalErrors() {
                return false;
            }

            @Override
            public int getGlobalErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            @Override
            public ObjectError getGlobalError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors() {
                return false;
            }

            @Override
            public int getFieldErrorCount() {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors() {
                return null;
            }

            @Override
            public FieldError getFieldError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors(String s) {
                return false;
            }

            @Override
            public int getFieldErrorCount(String s) {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors(String s) {
                return null;
            }

            @Override
            public FieldError getFieldError(String s) {
                return null;
            }

            @Override
            public Object getFieldValue(String s) {
                return null;
            }

            @Override
            public Class<?> getFieldType(String s) {
                return null;
            }
        };
    }

    // nieuweklant
    @Test
    public void nieuweklantGebruiktDeThymeleafPaginaNieuweKlant() {
        assertThat(registratieController.registratie().getViewName()).isEqualTo("nieuweklant");
    }

    @Test
    public void nieuweklantThymeleafPaginaNieuweKlantHeeftKlantRegistratieForm() {
        assertThat(registratieController.registratie().getModel().equals(KlantRegistratieForm.class));
    }

    // registratie
    @Test
    public void registratieGebruiktDeThymeleafPaginaRegistratie() {
        KlantRegistratieForm form = new KlantRegistratieForm("test", "test", "test",
                "test", "test", "test", "test", "test", "test");
        assertThat(registratieController.registratie(form, errors, request, redirect)
                .getViewName()).isEqualTo("redirect:/reservatie/toevoegen");
    }
}
