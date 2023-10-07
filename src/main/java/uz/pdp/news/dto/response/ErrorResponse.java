package uz.pdp.news.dto.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Map<String, String> errors;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
