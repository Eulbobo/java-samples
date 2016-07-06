package fr.norsys.springexample.properties.elements.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithProperties {

    @Value("${myapplication.specificvalue}")
    private String value;

    @Value("${myapplication.line.long}")
    private String longLine;

    @Value("${myapplication.line.custom}")
    private String customLine;

    @Value("${myapplication.line.default:PAS DE PROPERTY}")
    private String propertyWithDefaultValue;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the longLine
     */
    public String getLongLine() {
        return longLine;
    }

    /**
     * @return the customLine
     */
    public String getCustomLine() {
        return customLine;
    }

    /**
     * @return the propertyWithDefaultValue
     */
    public String getPropertyWithDefaultValue() {
        return propertyWithDefaultValue;
    }

}
