package com.bezkoder.spring.datajpa.message;

import com.bezkoder.spring.datajpa.model.Evaluation;
import com.bezkoder.spring.datajpa.model.FileDB;
import com.bezkoder.spring.datajpa.model.Livrable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    @JsonProperty("livrableNom")
    private String livrableNom;

    @JsonProperty("livrablePrenom")
    private String livrablePrenom;

    @JsonProperty("livrableNote")
    private int livrableNote;

    @JsonProperty("livrableEvaluations")
    private List<Evaluation> livrableEvaluations;

    @JsonProperty("fileId")
    private String fileId;

    public ResponseFile(String name, String url, String type, long size, Livrable livrable, String fileId) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.fileId = fileId;

        if (livrable != null) {
            this.livrableNom = livrable.getNom();
            this.livrablePrenom = livrable.getPrenom();
            this.livrableNote = livrable.getNote();
            this.livrableEvaluations = livrable.getEvaluations();
        }  }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLivrableNom() {
        return livrableNom;
    }

    public void setLivrableNom(String livrableNom) {
        this.livrableNom = livrableNom;
    }

    public String getLivrablePrenom() {
        return livrablePrenom;
    }

    public void setLivrablePrenom(String livrablePrenom) {
        this.livrablePrenom = livrablePrenom;
    }

    public int getLivrableNote() {
        return livrableNote;
    }

    public void setLivrableNote(int livrableNote) {
        this.livrableNote = livrableNote;
    }

    public List<Evaluation> getLivrableEvaluations() {
        return livrableEvaluations;
    }

    public void setLivrableEvaluations(List<Evaluation> livrableEvaluations) {
        this.livrableEvaluations = livrableEvaluations;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
