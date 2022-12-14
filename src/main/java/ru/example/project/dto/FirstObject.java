package ru.example.project.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FirstObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private StatusEnum status;

}
