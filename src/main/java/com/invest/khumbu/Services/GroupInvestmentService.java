package com.invest.khumbu.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.invest.khumbu.Model.GroupInvestment;
import com.invest.khumbu.Repository.GroupInvestmentRepository;

@Service
public class GroupInvestmentService {
    @Autowired
    private GroupInvestmentRepository repository;

    public GroupInvestment saveInvestment(GroupInvestment investment) {
        return repository.save(investment);
    }

    public List<GroupInvestment> getAllInvestments() {
        return repository.findAll();
    }
}
