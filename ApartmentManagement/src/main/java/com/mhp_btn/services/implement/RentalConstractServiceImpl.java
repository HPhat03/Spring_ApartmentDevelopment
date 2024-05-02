package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.repositories.RentalConstractRepository;
import com.mhp_btn.services.RentalConstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalConstractServiceImpl implements RentalConstractService {
    @Autowired
    private RentalConstractRepository rentalConstractRepo;
    @Override
    public List<ApartmentRentalConstract> getAllRentalConstract() {
        return this.rentalConstractRepo.getAllRentalConstract();
    }

    @Override
    public void deleteRentalConstractById(int id) {
        this.rentalConstractRepo.deleteRentalConstractById(id);
    }

    @Override
    public ApartmentRentalConstract getRentalConstractById(int id) {
        return this.rentalConstractRepo.getRentalConstractById(id);
    }

    @Override
    public void addRentalConstract(ApartmentRentalConstract constract) {
        this.rentalConstractRepo.addRentalConstract(constract);
    }


}
