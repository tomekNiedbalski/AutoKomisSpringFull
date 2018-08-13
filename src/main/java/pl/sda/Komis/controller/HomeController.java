package pl.sda.Komis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.Komis.model.Advertisement;
import pl.sda.Komis.model.AdvertisementRequest;
import pl.sda.Komis.model.AdvertisementUpdate;
import pl.sda.Komis.service.AdvertisementFactory;
import pl.sda.Komis.service.AdvertisementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/komis")
public class HomeController {

    private AdvertisementService advertisementService;

    private AdvertisementFactory advertisementFactory;

    @Autowired
    public HomeController(AdvertisementService advertisementService, AdvertisementFactory advertisementFactory) {
        this.advertisementService = advertisementService;
        this.advertisementFactory = advertisementFactory;
    }

    @GetMapping("/all")
    public List<Advertisement> getAllAdverts(@RequestParam(required = false) Integer from,
                                             @RequestParam(required = false) Integer to,
                                             @RequestParam(required = false) String model,
                                             @RequestParam(required = false) String company,
                                             @RequestParam(required = false) Integer fromYear,
                                             @RequestParam(required = false) Integer toYear) {
        return advertisementService.findAllBy(from, to, model, company, fromYear, toYear);
    }

    @GetMapping("/showSold")
    public List<Advertisement> getSold() {
        return advertisementService.findSold();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdvertisement(@RequestBody @Valid AdvertisementRequest advertisementRequest) {
        advertisementFactory.createAdvertisement(advertisementRequest);
    }

    @PostMapping("/addRandom")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRandomAdvertisement(@RequestParam Integer records) {
        advertisementFactory.createRandomAdvertisement(records);
    }

    @PutMapping("/update")
    public void updateAdvertisement(@RequestBody @Valid AdvertisementUpdate advertisementUpdate) {
        advertisementService.updateAdvertisement(advertisementUpdate);
    }

    @PutMapping("/changeFlag")
    public void changeFlag(@RequestParam String id) {
        advertisementService.changeFlag(id);
    }
}
