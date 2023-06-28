package controller;

import domain.model.Log;
import domain.model.TransactionMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.LoggingService;

import java.util.List;

@RestController
public class LoggingController {

    @Autowired
    LoggingService loggingService;

    @GetMapping(path = "/logging/")
    public String logging(){
        return "Logging service";
    }

    @PostMapping(path = "/logging/recordTransaction")
    public Log recordTransaction(@RequestParam String processId, @RequestBody TransactionMetadata transactionMetadata){
        return loggingService.storeLog(transactionMetadata, processId);
    }

    @GetMapping(path = "logging/getLogRecord")
    public Log getLogById(@RequestParam String logId){
        return loggingService.getStoredLogById(logId);
    }

    @GetMapping(path = "logging/getAllByPID")
    public List<Log> getLogByPID(@RequestParam String clearingHouseProcessId){
        return loggingService.getStoredLogsByProcessId(clearingHouseProcessId);
    }
}
