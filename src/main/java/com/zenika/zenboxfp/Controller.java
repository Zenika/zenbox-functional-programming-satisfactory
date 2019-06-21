package com.zenika.zenboxfp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    ParcManager parc;

    @PostMapping("/parc")
    public void ajusterProductionElectrique(@RequestBody int productionElectrique) {
        parc.setProductionElectrique(productionElectrique);
    }

    @GetMapping("/parc/usines")
    public List<Usine> getUsines() {
        return parc.getUsines();
    }

    @GetMapping("/parc/usines/{id}")
    public Usine getUsine(@PathVariable("id") int id) {
        return parc.getUsine(id);
    }

    @PostMapping("/parc/usines/{id}/cadence")
    public void ajusterCadence(@PathVariable("id") int id, @RequestBody double cadence) {
        parc.setCadence(id, cadence);
    }

    @PostMapping("/parc/usines/{id}/stocker")
    public int stocker(@PathVariable("id") int id, @RequestBody int quantité) {
        return parc.stocker(id, quantité);
    }

    @PostMapping("/parc/usines/{id}/livrer")
    public int livrer(@PathVariable("id") int id, @RequestBody int quantité) {
        return parc.livrer(id, quantité);
    }

    @GetMapping("/parc/historique")
    public List<Parc> historique(@RequestParam(value = "depuis", defaultValue = "0") int depuis) {
        return parc.historique(depuis <= 0 ? Integer.MAX_VALUE : depuis);
    }

    @GetMapping("/parc/simuler")
    public List<Parc> simuler(@RequestParam(value = "étapes", defaultValue = "1") int étapes) {
        return parc.simuler(étapes);
    }
}
