    package com.bezkoder.spring.datajpa.model.chat2Model;


    import com.bezkoder.spring.datajpa.model.enumerations.TypeChat;
    import com.bezkoder.spring.datajpa.model.userModel.User;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.io.Serializable;
    import java.util.Set;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Chat2 implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private  String titre;
        private  Boolean isCrypted;

        private String firstUserName;
        private String secondUserName;
        @Transient
        public static final String SEQUENCE_NAME = "chat_sequence";
        @Enumerated
        private TypeChat type;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnoreProperties("chat")
        @ToString.Exclude
        Set<Message2> messages;

        @ManyToMany(mappedBy="chats", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        @JsonIgnore()
        @ToString.Exclude
        private Set<User2> users;



        @ManyToMany(mappedBy="chats", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        @JsonIgnore()
        @ToString.Exclude
        private Set<User> userss;


    }
