package com.assignment3.image.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "image_table")
@AllArgsConstructor
@Data
public class Image {

    public Image() {
        super();
    }
    public Image(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", columnDefinition = "mediumblob")
    private byte[] picByte;

}
