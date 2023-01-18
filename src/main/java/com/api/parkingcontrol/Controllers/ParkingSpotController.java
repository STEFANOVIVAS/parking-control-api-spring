package com.api.parkingcontrol.Controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping(value="/parking-spot")
public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car already in use!");
        }
        if(parkingSpotService.existsByApartamentAndBlock(parkingSpotDto.getApartament(),parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Apartament and block already in use!");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot number already in use!");
        }


        var parkingSpotModel=new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto,parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0,size=10,sort= "id",direction=Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAllParking(pageable));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable UUID id){
        Optional<ParkingSpotModel> parkingSpotOptional=parkingSpotService.findByIdParking(id);
        if(parkingSpotOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotOptional.get());
    }

    @GetMapping(value="/spot-number")
    public ResponseEntity<Page<ParkingSpotModel>> findAllbySpotNumber(@RequestParam("parkingSpotNumber") String spotNumber, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findByParkingSpotNumber(spotNumber,pageable));

    }

    @GetMapping(value="/plate-car")
    public ResponseEntity<Page<ParkingSpotModel>> findAllbyPlateCar(@RequestParam("licensePlateCar") String licensePlateCar,Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findByLicensePlateCar(licensePlateCar,pageable));

    }

    @GetMapping(value="/name")
    public ResponseEntity<Page<ParkingSpotModel>> findAllby(@RequestParam("licensePlateCar") String licensePlateCar,Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findByLicensePlateCar(licensePlateCar,pageable));

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable UUID id){
        Optional<ParkingSpotModel> parkingSpotOptional=parkingSpotService.findByIdParking(id);
        if(parkingSpotOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        parkingSpotService.deleteParkingSpot(parkingSpotOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully!");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotOptional=parkingSpotService.findByIdParking(id);
        if(parkingSpotOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
        var parkingSpotModel=parkingSpotOptional.get();
        BeanUtils.copyProperties(parkingSpotDto,parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModel.getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModel.getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));


    }


}
