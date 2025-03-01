package com.bezkoder.spring.datajpa.model.chat2Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private boolean online;

    private String nomUser;

    private String prenomUser;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore()
    private Set<Chat2> chats;





}
