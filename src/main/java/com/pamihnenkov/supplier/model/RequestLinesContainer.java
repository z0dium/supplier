package com.pamihnenkov.supplier.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import java.util.List;


public class RequestLinesContainer {

    private List<RequestLine> requestLines = new ArrayList<>();

    @Autowired
    public RequestLinesContainer(List<RequestLine> requestLines) {
        this.requestLines = requestLines;
    }

    public RequestLinesContainer() {
    }

    public void addRequestLine(RequestLine requestLine){
        this.requestLines.add(requestLine);
    }

    public List<RequestLine> getRequestLines() {
        return requestLines;
    }

    public void setRequestLines(List<RequestLine> requestLines) {
        this.requestLines = requestLines;
    }
}
