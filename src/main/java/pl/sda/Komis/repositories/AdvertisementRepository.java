package pl.sda.Komis.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.sda.Komis.model.Advertisement;

import java.util.List;

@Repository
public interface AdvertisementRepository extends MongoRepository<Advertisement, String> {

    List<Advertisement> findByPriceBetweenAndSoldIsFalse(Integer from, Integer to);

    List<Advertisement> findBySoldIsFalse();

    List<Advertisement> findBySoldIsTrue();

    List<Advertisement> findAllByCar_ModelAndSoldIsFalse(String model);

    List<Advertisement> findAllByCar_CompanyAndSoldIsFalse(String company);

    List<Advertisement> findAllByCar_YearBetweenAndSoldIsFalse(Integer fromYear, Integer toYear);

    Advertisement findOneById(String id);
}
