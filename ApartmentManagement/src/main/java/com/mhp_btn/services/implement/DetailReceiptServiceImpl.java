package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.repositories.DetailReceiptRepository;
import com.mhp_btn.services.DetailReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailReceiptServiceImpl implements DetailReceiptService {
    @Autowired
    private DetailReceiptRepository detailRepo;

    @Override
    public List<ApartmentDetailReceipt> getDetailReceiptsByReceiptId(int id) {
        return this.detailRepo.getDetailReceiptsByReceiptId(id);
    }

    @Override
    public void createDetailByReceiptId( ApartmentDetailReceipt detailReceipt) {
        detailRepo.saveDetailReceipt(detailReceipt);
    }

    @Override
    public void deleteDetailReceiptById(int id) {
        this.detailRepo.deleteDetailReceiptById(id);
    }

    @Override
    public ApartmentDetailReceipt getDetailReceiptById(int id) {
        return this.detailRepo.getDetailReceiptById(id);
    }

    @Override
    public void updateDetailReceipt(ApartmentDetailReceipt detailReceipt) {
        this.detailRepo.updateDetailReceipt(detailReceipt);
    }


}
