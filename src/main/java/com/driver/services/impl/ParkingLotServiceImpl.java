package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.hibernate.sql.Update;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address)
    {
        ParkingLot obj=new ParkingLot();
        obj.setName(name);
        obj.setAddress(address);
        parkingLotRepository1.save(obj);
        return obj;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour)
    {
        Spot spot=new Spot();
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        spot.setParkingLot(parkingLot);

        spot.setPricePerHour(pricePerHour);
        if(numberOfWheels<=2)
        {
            spot.setSpotType(SpotType.TWO_WHEELER);
        }
        else if(numberOfWheels<=4)
        {
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else
        {
            spot.setSpotType(SpotType.OTHERS);
        }
        spot.setOccupied(false);
        parkingLot.getSpotList().add(spot);
        parkingLotRepository1.save(parkingLot);
        return spot;

    }

    @Override
    public void deleteSpot(int spotId)
    {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour)
    {
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        Spot updateSpot=null;
        for (Spot spot:parkingLot.getSpotList())
        {
            if(spot.getId()==spotId)
                updateSpot=spot;
        }
        updateSpot.setPricePerHour(pricePerHour);
        spotRepository1.save(updateSpot);
        return updateSpot;

    }

    @Override
    public void deleteParkingLot(int parkingLotId)
    {
        parkingLotRepository1.deleteById(parkingLotId);

    }
}