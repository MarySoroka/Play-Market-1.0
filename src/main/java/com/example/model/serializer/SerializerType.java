package com.example.model.serializer;
public enum SerializerType {
    BINARY("binary"),
    XML("xml"),
    SPECIFIC("specific");

    public String getType() {
        return type;
    }

    private String type;
    SerializerType(String type) {
        this.type = type;
    }
    public static SerializerType getByName(String name){
        for (SerializerType g:
                SerializerType.values()) {
            if (g.type.equals(name)){
                return g;
            }
        }
        return null;
    }
    public static String[] getListOfTypes(){
        return new String[]{SerializerType.BINARY.getType(),SerializerType.XML.getType(),SerializerType.SPECIFIC.getType()};
    }
}
