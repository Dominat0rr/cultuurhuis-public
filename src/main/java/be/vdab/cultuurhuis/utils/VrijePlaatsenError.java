package be.vdab.cultuurhuis.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class VrijePlaatsenError {
    private final Map<Integer, Integer> plaatsenMislukt = new LinkedHashMap<>();
    private Integer plaatsen;
    private Integer vrijeplaatsen;

    public VrijePlaatsenError() {}

    public void put(Integer plaatsen, Integer vrijeplaatsen){
        plaatsenMislukt.put(plaatsen, vrijeplaatsen);
        this.plaatsen = plaatsen;
        this.vrijeplaatsen = vrijeplaatsen;
    }

    public Map<Integer, Integer> getPlaatsMap() {
        return plaatsenMislukt;
    }

    public Integer getPlaats(){
        return plaatsen;
    }

    public Integer getVrijePlaats(){
        return vrijeplaatsen;
    }
}