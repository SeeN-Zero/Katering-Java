package com.katering.kateringjava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Products")
public class Products {

    private static final String NOT_EMPTY_MSG = "Tidak Boleh Kosong";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull(message = NOT_EMPTY_MSG)
    @NotBlank(message = NOT_EMPTY_MSG)
    @NotEmpty(message = NOT_EMPTY_MSG)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    public void setImage(MultipartFile image) throws IOException {
        this.image = image.getBytes();
    }

    public String getImage() {
        return Base64.encodeBase64String(image);
    }
}