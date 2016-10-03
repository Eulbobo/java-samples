package fr.norsys.logs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

public class ServiceAvecDesLogsTest {

    @Test
    public void should_get_a_string() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.methodeDansInterface(anyInt())).thenReturn("42");

        ServiceAvecDesLogs service = new ServiceAvecDesLogs(mock);

        String result = service.maMethodeQuiRenvoieUnString();

        assertThat(result).isNotNull()
                .isEqualTo("42");
    }

    private static List<Object> resultListMock() {
        List<Object> resultList = new ArrayList<>();
        resultList.add("42");
        resultList.add("41");
        resultList.add("40");
        return resultList;
    }

    @Test
    public void should_have_object_42_for_id_BADABADA() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        ServiceAvecDesLogs service = new ServiceAvecDesLogs(mock);

        boolean result = service.hasObjectForId("42", "BADABADA");

        assertThat(result).isTrue();
    }

    @Test
    public void should_not_have_object_0_for_id_BADABADA() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        ServiceAvecDesLogs service = new ServiceAvecDesLogs(mock);

        boolean result = service.hasObjectForId("0", "BADABADA");

        assertThat(result).isFalse();
    }

    @Test
    public void validation_should_work_everytime_when_no_exception_is_thrown() {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        final ServiceAvecDesLogs service = new ServiceAvecDesLogs(mock);

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                service.validationDonneesForId("0");
            }
        });

        assertThat(thrownByMethod).isNull();
    }

    @Test
    public void validation_should_work_everytime_when_sqlexception_is_thrown() throws SQLException {
        InterfaceAvecDesMethodes mock = mock(InterfaceAvecDesMethodes.class);
        when(mock.recuperationDesDonnees(anyString())).thenReturn(resultListMock());

        doThrow(SQLException.class)
            .when(mock)
            .verificationDesDonnees(anyList());

        final ServiceAvecDesLogs service = new ServiceAvecDesLogs(mock);

        Throwable thrownByMethod = catchThrowable(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                service.validationDonneesForId("0");
            }
        });

        assertThat(thrownByMethod).isNull();
    }

}
