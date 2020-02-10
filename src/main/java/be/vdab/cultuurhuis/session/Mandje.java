package be.vdab.cultuurhuis.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.*;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Long, Integer> voorstellingen = new LinkedHashMap<>();  // <id, aantal plaatsen>

    public void voegToe(long id, int aantal) {
        if (this.bevat(id)) {
            voorstellingen.put(id, voorstellingen.get(id) + aantal);
        }
        else voorstellingen.put(id, aantal);
    }

    public boolean bevat(long id) {
        return voorstellingen.containsKey(id);
    }

    public boolean isGevuld() {
        return !voorstellingen.isEmpty();
    }

    public boolean isLeeg() {
        return voorstellingen.isEmpty();
    }

    public Optional<Integer> getAantal(long id) {
        return Optional.ofNullable(voorstellingen.get(id));
    }

    public Set<Long> getIds() {
        return voorstellingen.keySet();
    }

    public void maakLeeg() {
        voorstellingen.clear();
    }

    public Map<Long, Integer> getVoorstellingen() {
        return Collections.unmodifiableMap(voorstellingen);
    }

    public void verwijder(long id) {
        voorstellingen.remove(id);
    }
}
