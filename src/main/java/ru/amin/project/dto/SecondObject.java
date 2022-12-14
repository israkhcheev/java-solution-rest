package ru.amin.project.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.amin.project.model.SecondObjectCreationModel;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SecondObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    private FirstObject request;

    public SecondObject(SecondObjectCreationModel secondObjectCreationModel, FirstObject firstObject) {
        this.id = secondObjectCreationModel.getId();
        this.title = secondObjectCreationModel.getTitle();
        this.request = firstObject;
    }
}
