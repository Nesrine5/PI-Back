    package com.bezkoder.spring.datajpa.model.chat2Model;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.io.Serializable;
    import java.time.LocalDateTime;
    import java.util.Date;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Message2 implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String contenu;
        private String imagePath;
        private String filePath;
        private Date sent_Date;
        private Boolean isSeen;
        private Date seen_Date;
        private Boolean isLiked;
        private LocalDateTime time;
        @ManyToOne
        @JoinColumn(name = "chat_id", referencedColumnName = "id")
        @JsonIgnoreProperties("messages")
        private Chat2 chat;


        private String sender;



    }

