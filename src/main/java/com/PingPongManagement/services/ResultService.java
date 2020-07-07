package com.PingPongManagement.services;

import com.PingPongManagement.models.Result;
import com.PingPongManagement.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    public Optional<Result> getResult(Integer resultId) {
        return resultRepository.findById(resultId);
    }
}
