package com.PingPongManagement.controllers;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.models.Result;
import com.PingPongManagement.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/result/")
public class ResultController {
    @Autowired
    private ResultService resultService;

    // save result route
    @PostMapping("/")
    public ResponseEntity<?> saveResult(@Valid @RequestBody Result result, BindingResult bindingResult) {
        try {
            resultService.saveResult(result);

            return new ResponseEntity<>(new ResponseMessage("Save result successully!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            if (!errors.isEmpty()) {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get result by resultId route
    @GetMapping("/{resultId}")
    public ResponseEntity<?> getResult(@PathVariable Integer resultId) {
        try {
            Optional<Result> result = resultService.getResult(resultId);

            if (result.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("There is no result here!"),
                        HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
