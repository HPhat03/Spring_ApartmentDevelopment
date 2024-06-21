package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentRelativeRegistry;
import com.mhp_btn.repositories.RelativeRegistryRepository;
import com.mhp_btn.services.RelativeRegistryService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelativeRegistryServiceImpl implements RelativeRegistryService {
    @Autowired
    private RelativeRegistryRepository registryRepository;
    @Override
    public List<ApartmentRelativeRegistry> getAllRelativeRegistry() {
        return this.registryRepository.getAllRelativeRegistry();
    }

    @Override
    public ApartmentRelativeRegistry getRelativeRegistryById(int id) {
        return this.registryRepository.getRelativeRegistryById(id);
    }

    @Override
    public void deleteReceiptById(int id) {
        this.registryRepository.deleteRelativeRegistryById(id);
    }

    @Override
    public void addRelativeRegistry(ApartmentRelativeRegistry relativeRegistry) {
        this.registryRepository.addRelativeRegistry(relativeRegistry);
    }

    @Override
    public void updateRelativeRegistry(ApartmentRelativeRegistry relativeRegistry) {
        this.registryRepository.updateRelativeRegistry(relativeRegistry);
    }

    @Override
    public List<ApartmentRelativeRegistry> getRRByApartmentId(int id, HashMap<String, String> params) {
        return this.registryRepository.getRRbyApartmentId(id, params);
    }
}
