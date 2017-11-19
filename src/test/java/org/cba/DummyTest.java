package org.cba;

import org.cba.model.entities.User;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by adam on 11/17/2017.
 */
public class DummyTest {
    @Test
    public void test() {
        User user = User.find.byId(1);
        Assert.assertNotNull(user);
    }
}
