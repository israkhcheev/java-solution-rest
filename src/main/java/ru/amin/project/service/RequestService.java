package ru.amin.project.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amin.project.dto.Request;
import ru.amin.project.repository.RequestRepo;

import java.util.Objects;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepo requestRepo;

    @Autowired
    public RequestService(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public Request saveRequest(Request request) {
        Optional<Request> requestOptional = requestRepo.findById(request.getId());
        return requestOptional
                .map(value -> updateRequest(request, value))
                .orElseGet(() -> requestRepo.save(request));
    }

    public Request getRequest(Long id) throws EntityNotFoundException {
        Optional<Request> requestOptional = requestRepo.findById(id);
        if(requestOptional.isEmpty()) throw new EntityNotFoundException();
        return requestOptional.get();
    }

    public void removeRequest(Long id) {
        Optional<Request> requestOptional = requestRepo.findById(id);
        if(requestOptional.isEmpty()) throw new EntityNotFoundException();
        requestRepo.delete(requestOptional.get());
    }

    private Request updateRequest(Request request, Request dbRequest) {
        if(Objects.nonNull(request.getMessage())) dbRequest.setMessage(request.getMessage());
        if(Objects.nonNull(request.getTitle())) dbRequest.setTitle(request.getTitle());
        if(Objects.nonNull(request.getStatus())) dbRequest.setStatus(request.getStatus());
        requestRepo.save(dbRequest);
        return dbRequest;
    }

}
