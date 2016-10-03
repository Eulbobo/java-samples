package fr.norsys.springjdbc.bean;

import static com.openpojo.validation.ValidatorBuilder.create;

import org.junit.Test;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import fr.norsys.springjdbc.beans.User;

public class PojoTest {

    @Test
    public void userBean_should_be_a_correct_bean() {
        Validator validator = create()
                // Add Rules to validate structure for POJO_PACKAGE
                // See com.openpojo.validation.rule.impl for more ...
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new NoFieldShadowingRule())
                // Add Testers to validate behaviour for POJO_PACKAGE
                // See com.openpojo.validation.test.impl for more ...
                .with(new SetterTester())
                .with(new GetterTester())
                .build();


        validator.validate(PojoClassFactory.getPojoClass(User.class));
    }
}
