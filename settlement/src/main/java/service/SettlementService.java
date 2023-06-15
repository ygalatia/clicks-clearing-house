package service;

import domain.ClearingHouseProcessStub;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SettlementService {

    public boolean isTransactionDischarged(ClearingHouseProcessStub clearingHouseProcessStub) throws ParseException {
        boolean isDischarged = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date endDate = sdf.parse(clearingHouseProcessStub.endDate());
        Date currentDate = new Date();
        if (endDate.after(currentDate)){
            isDischarged = true;
        }
        return isDischarged;
    }
}
