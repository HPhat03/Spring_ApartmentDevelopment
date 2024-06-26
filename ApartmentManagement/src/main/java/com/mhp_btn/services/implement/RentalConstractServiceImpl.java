package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.repositories.RentalConstractRepository;
import com.mhp_btn.services.RentalConstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;

@Service
public class RentalConstractServiceImpl implements RentalConstractService {
    @Autowired
    private RentalConstractRepository rentalConstractRepo;
    @Override
    public List<ApartmentRentalConstract> getAllRentalConstract(HashMap<String,String> params) {
        return this.rentalConstractRepo.getAllRentalConstract(params);
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

    @Override
    public ApartmentRentalConstract getConstractById(int id) {
        return this.rentalConstractRepo.getConstractById(id);
    }

    @Override
    public void updateConstract(ApartmentRentalConstract constract) {
        this.rentalConstractRepo.updateConstract(constract);
    }

    @Override
    public boolean checkRenter(int id, String username) {
        return this.rentalConstractRepo.checkRenter(id, username);
    }
    @Override
    public long countConstracts() {
        return this.rentalConstractRepo.countConstracts();
    }


}
