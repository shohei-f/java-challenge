package jp.co.axa.apidemo.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * API Error Details Bean
 * @author shohei
 */
@Data
@AllArgsConstructor
public class APIErrorDetails {

    private String message;
    private String URI;
    private LocalDateTime timestamp;

}
