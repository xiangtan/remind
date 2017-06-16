package com.fsmeeting.test.cas.server.integration.redis;

import org.junit.Test;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 10:53 2017/6/15
 */
//https://github.com/apereo/cas/blob/4.2.x/cas-server-integration-memcached/src/test/java/org/jasig/cas
//@RunWith(Parameterized.class)
public class RedisTicketRegistryTest {


    @Test
    public void testWriteGetDelete() throws Exception {
        //对ticket执行增查删操作
       /* final String id = "ST-1234567890ABCDEFGHIJKL-crud";
        final ServiceTicket ticket = mock(ServiceTicket.class, withSettings().serializable());
        when(ticket.getId()).thenReturn(id);
        registry.addTicket(ticket);
        final ServiceTicket ticketFromRegistry = (ServiceTicket) registry.getTicket(id);
        Assert.assertNotNull(ticketFromRegistry);
        Assert.assertEquals(id, ticketFromRegistry.getId());
        registry.deleteTicket(id);
        Assert.assertNull(registry.getTicket(id));*/
    }

}
