package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartamentAndBlock(String apartament, String block);

    Page<ParkingSpotModel> findByParkingSpotNumberContains(String parkingSpotNumber, Pageable pageable);
    Page<ParkingSpotModel> findByLicensePlateCarContains(String licensePlateCar,Pageable pageable);
    Page<ParkingSpotModel> findByResponsibleNameContains(String responsibleName,Pageable pageable) ;


    Optional<ParkingSpotModel> findById(UUID id);




}
