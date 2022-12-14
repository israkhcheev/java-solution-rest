package ru.amin.project.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import ru.amin.project.dto.Request;

@Data
public class SolutionInsertion {

    private Long id;
    private String title;
    private String message;
    private Long requestId;

}
