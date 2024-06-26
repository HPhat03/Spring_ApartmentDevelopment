package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.repositories.SmartCabinetRepository;
import com.mhp_btn.services.SmartCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SmartCabinetServiceImpl implements SmartCabinetService {
    @Autowired
    private SmartCabinetRepository cabinetRepo;


    @Override
    public List<ApartmentSmartCabinet> getAllSmartCabinets(Map<String, String> params)  {
        return this.cabinetRepo.getAllSmartCabinets(params);
    }

    @Override
    public List<ApartmentSmartCabinet> getAllSmartCabinetByApartmentId(int id) {
        return this.cabinetRepo.getAllSmartCabinetByApartmentId(id);
    }

    @Override
    public ApartmentSmartCabinet getSmartCabinetById(int id) {
        return this.cabinetRepo.getSmartCabinetById(id);
    }

    @Override
    public void deleteCabinetById(int id) {
        this.cabinetRepo.deleteCabinetById(id);
    }

    @Override
    public void addCabinet(ApartmentSmartCabinet cabinet) {
        this.cabinetRepo.addCabinet(cabinet);
    }

    @Override
    public void updateCabinet(ApartmentSmartCabinet cabinet) {
        this.cabinetRepo.updateCabinet(cabinet);
    }

    @Override
    public void addOrUpdate(ApartmentSmartCabinet c) {
        this.cabinetRepo.addOrUpdate(c);
    }

    @Override
    public long countCabinets() {
        return this.cabinetRepo.countCabinets();
    }


}
