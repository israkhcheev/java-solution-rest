package ru.amin.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Request {

    @Id
    private Long id;
    private String title;
    private String message;
    private StatusEnum status;

}
