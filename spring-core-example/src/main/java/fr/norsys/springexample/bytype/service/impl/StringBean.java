package fr.norsys.springexample.bytype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.bytype.service.BeanInterface;

@Repository
public class StringBean implements BeanInterface<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringBean.class);

    @Override
    public String getBean() {
        return "Value From StringBean";
    }

    @Override
    public void saveBean(final String bean) {
        LOGGER.info("saving bean {}", bean);
    }

}
