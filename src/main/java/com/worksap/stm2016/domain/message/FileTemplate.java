package com.worksap.stm2016.domain.message;

import com.worksap.stm2016.domain.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "file_template")
public class FileTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_template_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "content")
    private String content;

    /* auditing */
    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "author_id")
    @CreatedBy
    private User author;

    @OrderBy("name ASC")
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "last_editor_id")
    @LastModifiedBy
    private User lastEditor;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;
}
