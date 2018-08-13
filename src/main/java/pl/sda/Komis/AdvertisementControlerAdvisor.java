package pl.sda.Komis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sda.Komis.exception.AdvertisementNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AdvertisementControlerAdvisor {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePersonNotFound(AdvertisementNotFoundException e){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMsg", e.getMessage());
        return errorMap;
    }
}
