package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }


    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateCar(String licensePlatecar){
        return parkingSpotRepository.existsByLicensePlateCar(licensePlatecar);

    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber){
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartamentAndBlock(String apartament,String block){
        return parkingSpotRepository.existsByApartamentAndBlock(apartament,block);
    }

    public Page<ParkingSpotModel> findAllParking(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findByIdParking(UUID id){
        return parkingSpotRepository.findById(id);
    }

    public List<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber){
        return parkingSpotRepository.findByParkingSpotNumberContains(parkingSpotNumber);
    }

    public List<ParkingSpotModel> findByLicensePlateCar(String licensePlateCar){
        return parkingSpotRepository.findByLicensePlateCarContains(licensePlateCar);
    }

    public List<ParkingSpotModel> findByResponsibleName(String responsibleName){
        return parkingSpotRepository.findByResponsibleNameContains(responsibleName);
    }

    @Transactional
    public void deleteParkingSpot(ParkingSpotModel parkingSpotModel){
        parkingSpotRepository.delete(parkingSpotModel);
    }

}
