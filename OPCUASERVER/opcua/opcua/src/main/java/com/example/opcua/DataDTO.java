package com.example.opcua;
public class DataDTO {
    private String nodeId;
    private Object value;

    // Construtor padrão
    public DataDTO() {}

    // Getters e setters
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}