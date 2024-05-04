package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentServiceConstract;
import com.mhp_btn.repositories.ServiceConstractRepository;
import com.mhp_btn.services.ServiceConstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceConstractServiceImpl implements ServiceConstractService {
    @Autowired
    private ServiceConstractRepository serviceRepo;

    @Override
    public List<ApartmentServiceConstract> getAllServiceConstract() {
        return this.serviceRepo.getAllServiceConstract();
    }

    @Override
    public ApartmentServiceConstract getServiceConstractById(int id) {
        return this.serviceRepo.getServiceConstractById(id);
    }

    @Override
    public void deleteServiceConstractById(int id) {
        this.serviceRepo.deleteServiceConstractById(id);
    }

    @Override
    public void addServiceConstract(ApartmentServiceConstract constract) {
        this.serviceRepo.addServiceConstract(constract);
    }

    @Override
    public void updateServiceConstract(ApartmentServiceConstract constract) {
        this.serviceRepo.updateServiceConstract(constract);
    }

}
