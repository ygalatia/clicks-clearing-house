package service;

import domain.model.Log;
import domain.model.LogRepository;
import domain.model.TransactionMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LoggingService {

    private static final Logger logger = Logger.getLogger(LoggingService.class.getName());

    @Autowired
    LogRepository logRepository;

    public Log storeLog(Log logBody){
        return logRepository.save(logBody);
    }

    public Log getStoredLogById(String logId){
        if (isLogExist(logId)){
            return logRepository.findById(logId).get();
        }
        else {
            return null;
        }
    }

    public List<Log> getStoredLogsByProcessId(String processId){
        //TODO: Add user validation, only valid/authorised user able to get log list
        return logRepository.findByClearingHouseProcessId(processId);
    }

    Boolean isLogExist(String logId){
        if (logRepository.existsById(logId)){
            logger.info("Log record found");
            return true;
        }
        else {
            logger.info("Log record does not exist");
            return false;
        }
    }
}
