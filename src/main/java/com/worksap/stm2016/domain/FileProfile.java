package com.worksap.stm2016.domain;


import com.worksap.stm2016.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "file_profile")
public class FileProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false, unique = true)
    private String path;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FileType type;

    @Column(name = "info")
    private String info;

    public enum FileType {
        DOCUMENT, APPLICATION, RESUME
    }

}