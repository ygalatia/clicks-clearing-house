package service;

import domain.ContractStub;
import org.springframework.stereotype.Service;

@Service
public class ClearingService {

    public String readContract(ContractStub contractStub){
        return contractStub.payload();
    }
}
