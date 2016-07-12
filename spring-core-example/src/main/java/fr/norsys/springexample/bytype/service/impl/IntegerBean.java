package fr.norsys.springexample.bytype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fr.norsys.springexample.bytype.service.BeanInterface;

@Repository
public class IntegerBean implements BeanInterface<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerBean.class);

    @Override
    public Integer getBean() {
        return Integer.valueOf(422);
    }

    @Override
    public void saveBean(final Integer bean) {
        LOGGER.info("saving bean {}", bean);
    }

}
