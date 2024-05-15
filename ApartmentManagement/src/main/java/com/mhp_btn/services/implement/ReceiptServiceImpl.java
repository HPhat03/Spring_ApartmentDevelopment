package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.repositories.ReceiptRepository;
import com.mhp_btn.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepo;
    @Override
    public List<ApartmentReceipt> getAllReceipt() {
        return this.receiptRepo.getAllReceipt();
    }

    @Override
    public ApartmentReceipt getReceiptById(int id) {
        return  this.receiptRepo.getReceiptById(id);
    }

    @Override
    public void deleteReiptById(int id) {
        this.receiptRepo.deleteReceiptById(id);
    }

    @Override
    public void addReceipt(ApartmentReceipt receipt) {
        this.receiptRepo.addReceipt(receipt);
    }

    @Override
    public void updateReceipt(ApartmentReceipt receipt) {
        this.receiptRepo.updateReceipt(receipt);
    }
}
