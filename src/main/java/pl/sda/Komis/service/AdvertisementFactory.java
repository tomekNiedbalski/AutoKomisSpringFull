package pl.sda.Komis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.Komis.model.Advertisement;
import pl.sda.Komis.model.AdvertisementRequest;
import pl.sda.Komis.model.Car;
import pl.sda.Komis.repositories.AdvertisementRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class AdvertisementFactory {

    static List<String> companyList;
    static List<String> modelList;
    static List<Integer> yearList;
    static List<Integer> mileageList;
    static List<Integer> priceList;

    static {
        companyList = new ArrayList<>();
        modelList = new ArrayList<>();
        yearList = new ArrayList<>();
        mileageList = new ArrayList<>();
        priceList = new ArrayList<>();
        companyList.addAll(Arrays.asList("Ford","Mazda","Toyota","Kia","Honda","Audi","Citroen","Jeep","Nissan"));
        modelList.addAll(Arrays.asList("Mondeo","Fiesta","Accord","Venga","Rio","Corolla","A4","A5","C3","C4","Almera"));
        yearList.addAll(Arrays.asList(2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014));
        mileageList.addAll(Arrays.asList(10000,20431,431432,32142,123543,190876,230129,399011,90231,142987,167987,143900));
        priceList.addAll(Arrays.asList(10500,15750,45000,32100,21900,7000,5500,61000,42150,39900,25900,13450,17900));
    }

    private Random random = new Random();

    private AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementFactory(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public void createAdvertisement(AdvertisementRequest advertisementRequest){
        Advertisement advertisement = Advertisement.builder()
                .car(advertisementRequest.getCar())
                .description(advertisementRequest.getDescription())
                .price(advertisementRequest.getPrice())
                .sold(advertisementRequest.isSold())
                .build();
        advertisementRepository.save(advertisement);
    }

    private Advertisement generateRandomAdvertisement(){
        Advertisement advertisement = Advertisement.builder()
                .sold(false)
                .price(priceList.get(new Random().nextInt(priceList.size())))
                .car(Car.builder()
                        .company(companyList.get(random.nextInt(companyList.size())))
                        .model(modelList.get(random.nextInt(modelList.size())))
                        .year(yearList.get(random.nextInt(yearList.size())))
                        .mileage(mileageList.get(random.nextInt(mileageList.size())))
                        .build())
                .build();

        return advertisement;
    }

    public void createRandomAdvertisement(int records) {
        for (int i = 0; i < records; i++) {

            advertisementRepository.save(generateRandomAdvertisement());
        }
    }
}
