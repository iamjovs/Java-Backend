package com.example.website.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseMessage {
    String message;
    String action;
    boolean status;

    public ResponseMessage(int userName) {
    }
}
