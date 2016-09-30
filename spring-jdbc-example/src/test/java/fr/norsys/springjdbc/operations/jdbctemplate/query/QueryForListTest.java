package fr.norsys.springjdbc.operations.jdbctemplate.query;

import static fr.norsys.springjdbc.bean.UserFixture.allArray;
import static fr.norsys.springjdbc.bean.UserFixture.first;
import static fr.norsys.springjdbc.bean.UserFixture.second;
import static fr.norsys.springjdbc.bean.UserFixture.third;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import fr.norsys.springjdbc.ApplicationTestConfiguration;
import fr.norsys.springjdbc.beans.User;

/**
 * On a configuré le test pour récupérer directement une instance de notre objet testé
 * On aurait aussi pu l'instancier à chaque test avec le jdbcTemplate passé en injection par spring-test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class QueryForListTest {

    @Autowired
    private QueryForList queryForList;

    private Map<String, Object> getUserInfoMap(final User user){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ID", user.getId());
        map.put("NAME", user.getName());
        map.put("EMAIL", user.getMail());
        return map;
    }

    private List<Map<String, Object>> expectedResult(final User... users){
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        for (User user : users){
            resultList.add(getUserInfoMap(user));
        }
        return resultList;
    }

    @Test
    public void should_get_all_elements() {
        List<Map<String, Object>> users = queryForList.getAllElements();

        assertThat(users)
                .isNotNull()
                .hasSize(3)
                .containsAll(expectedResult(allArray()));
    }

    @Test
    public void should_get_list_of_all_ids() {
        List<Integer> ids = queryForList.getAllElementsId();

        assertThat(ids)
                .isNotNull()
                .hasSize(3)
                .contains(1, 2, 3);
    }

    @Test
    public void should_get_JULIEN_and_ALYS_with_name_like_l() {
        List<Map<String, Object>> elements = queryForList.getElementsLike("l");

        assertThat(elements)
                .isNotNull()
                .hasSize(2)
                .containsAll(expectedResult(first(), second()));
    }

    @Test
    public void should_get_didier_with_name_diDiER() {
        List<Map<String, Object>> elements = queryForList.getElementsWithName("diDiER");

        assertThat(elements)
                .isNotNull()
                .hasSize(1)
                .containsOnly(getUserInfoMap(third()));
    }

}
