package pl.sda.Komis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.Komis.exception.AdvertisementNotFoundException;
import pl.sda.Komis.model.Advertisement;
import pl.sda.Komis.model.AdvertisementUpdate;
import pl.sda.Komis.repositories.AdvertisementRepository;

import java.util.List;

@Service
public class AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public List<Advertisement> findAllBy(Integer from, Integer to, String model, String company, Integer fromYear, Integer toYear) {
        if (from != null & to != null) {
            return findAllByPriceBetween(from, to);
        } else if (model != null) {
            return findAllByModel(model);
        }
        else if(company != null){
            return findAllByCompany(company);
        }
        else if (fromYear != null & toYear != null){
            return findAllByYear(fromYear, toYear);
        }
            else
            return findActiveAdvertisements();
    }


    public void updateAdvertisement(AdvertisementUpdate advertisementUpdate) {
        Advertisement advertisement;
        if(advertisementRepository.findOneById(advertisementUpdate.getId()) == null){
            throw new AdvertisementNotFoundException("There is no advertisement with id:" + advertisementUpdate.getId());
        }
        else {
            advertisement = Advertisement.builder()
                    .car(advertisementUpdate.getCar())
                    .price(advertisementUpdate.getPrice())
                    .description(advertisementUpdate.getDescription())
                    .sold(advertisementUpdate.isSold())
                    .id(advertisementUpdate.getId())
                    .userName(advertisementUpdate.getUserName())
                    .build();
        }
        advertisementRepository.save(advertisement);
    }

    public void changeFlag(String id) {
        Advertisement advertisement = advertisementRepository.findOneById(id);
        if(advertisement == null){
            throw new AdvertisementNotFoundException("There is no advertisement with id:" + id);
        }
        else {
            advertisement.setSold(true);
        }
        advertisementRepository.save(advertisement);
    }

    public List<Advertisement> findSold() {
        return advertisementRepository.findBySoldIsTrue();
    }

    private List<Advertisement> findAllByPriceBetween(Integer from, Integer to) {
        return advertisementRepository.findByPriceBetweenAndSoldIsFalse(from, to);
    }

    private List<Advertisement> findActiveAdvertisements() {
        return advertisementRepository.findBySoldIsFalse();
    }

    private List<Advertisement> findAllByModel(String model) {
        return advertisementRepository.findAllByCar_ModelAndSoldIsFalse(model);
    }

    private List<Advertisement> findAllByCompany(String company) {
        return advertisementRepository.findAllByCar_CompanyAndSoldIsFalse(company);
    }

    private List<Advertisement> findAllByYear(Integer fromYear, Integer toYear){
        return advertisementRepository.findAllByCar_YearBetweenAndSoldIsFalse(fromYear, toYear);
    }
}
