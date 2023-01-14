package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartamentAndBlock(String apartament, String block);

    List<ParkingSpotModel> findByParkingSpotNumberContains(String parkingSpotNumber);
    List<ParkingSpotModel> findByLicensePlateCarContains(String licensePlateCar);
    List<ParkingSpotModel> findByResponsibleNameContains(String responsibleName) ;
    void deleteParkingSpotModelById (UUID id);

    Optional<ParkingSpotModel> findById(UUID id);




}
