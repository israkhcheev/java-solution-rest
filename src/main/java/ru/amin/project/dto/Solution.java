package ru.amin.project.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.amin.project.model.SolutionInsertion;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Solution {

    @Id
    private Long id;
    private String title;
    private String message;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;

    public Solution(SolutionInsertion solutionInsertion, Request request) {
        this.id = solutionInsertion.getId();
        this.title = solutionInsertion.getTitle();
        this.message = solutionInsertion.getMessage();
        this.request = request;
    }
}
