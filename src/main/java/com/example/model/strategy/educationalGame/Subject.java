package com.example.model.strategy.educationalGame;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public enum Subject implements Serializable {
    MATH("math", "mathSource","mathTest"),
    ENGLISH("english","englishSource","englishTest"),
    RUSSIA("russian","russianSource","russianTest"),
    PHYSICS("physics","physicsSource","physicsTest"),
    HISTORY("history","historySource","historyTest"),
    BIOLOGY("biology","biologySource","biologyTest");

    Subject() {
    }


    @JacksonXmlProperty(localName = "subject")
    private String subject;
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    @JacksonXmlProperty(localName = "test")
    private String test;

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    @JacksonXmlProperty(localName = "source")
    private String source;
    Subject(String subject, String source, String test) {
        this.subject = subject;
        this.source = source;
        this.test = test;
    }
    public static Subject getByName(String subject){
        for (Subject s:
             Subject.values()) {
            if(s.subject.equals(subject)){
                return s;
            }
        }
        return null;
    }
    public static Subject getBySource(String source){
        for (Subject s:
                Subject.values()) {
            if(s.source.equals(source)){
                return s;
            }
        }
        return null;
    }
}
