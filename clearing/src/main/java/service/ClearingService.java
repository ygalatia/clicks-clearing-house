package service;

import domain.model.ClearingHouseProcess;
import domain.model.Contract;
import org.springframework.stereotype.Service;

@Service
public class ClearingService {

//    public Contract readContract(String payload){
//        return payload;
//    }

    public ClearingHouseProcess generateProcess(Boolean isCleared, String startDate, String endDate, Contract payload){
        ClearingHouseProcess clearingHouseProcess = new ClearingHouseProcess(123, isCleared, startDate, endDate, payload);
        return clearingHouseProcess;
    }


}
