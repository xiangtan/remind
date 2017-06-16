package com.fsmeeting.cas.server.integration.redis;

import org.jasig.cas.ticket.ServiceTicket;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.AbstractDistributedTicketRegistry;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 10:45 2017/6/15
 */
@Component("redisTicketRegistry")
public final class RedisTicketRegistry extends AbstractDistributedTicketRegistry implements DisposableBean {

    /**
     * Redis client.
     */
    @Autowired
    @Qualifier("jedisSentinelPool")
    private JedisSentinelPool jedisPool;

    /**
     * ST最大空闲时间
     */
    @Value("${st.timeToKillInSeconds:10}")
    private int stTimeout;

    /**
     * TGT最大空闲时间
     */
    @Value("${tgt.maxTimeToLiveInSeconds:28800}")
    private int tgtTimeout;

    @Override
    protected void updateTicket(final Ticket ticket) {
        logger.debug("Updating ticket {}", ticket);
        Jedis jedis = jedisPool.getResource();
        String ticketId = ticket.getId();
        try {
            jedis.expire(ticketId.getBytes(), getTimeout(ticket));
        } catch (final Exception e) {
            logger.error("Failed updating {}", ticket, e);
        } finally {
            jedis.close();
        }
    }

    @Override
    public void addTicket(final Ticket ticket) {
        logger.debug("Adding ticket {}", ticket);
        Jedis jedis = jedisPool.getResource();
        String ticketId = ticket.getId();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(ticket);
        } catch (IOException e) {
            logger.error("adding ticket {} to redis error.", ticket);
        } finally {
            try {
                if (null != oos) oos.close();
            } catch (IOException e) {
                logger.error("oos closing error when adding ticket {} to redis.", ticket);
            }
        }
        jedis.setex(ticketId.getBytes(), getTimeout(ticket), bos.toByteArray());
        jedis.close();
    }

    @Override
    public boolean deleteSingleTicket(String ticketId) {
        logger.debug("Deleting ticket {}", ticketId);
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(ticketId.getBytes());
            return true;
        } catch (final Exception e) {
            logger.error("Failed deleting {}", ticketId, e);
            return false;
        } finally {
            jedis.close();
        }
    }

    @Override
    public Ticket getTicket(final String ticketId) {
        Jedis jedis = jedisPool.getResource();
        try {
            byte[] value = jedis.get(ticketId.getBytes());
            if (null == value) {
                logger.error("Failed fetching {}, ticketId is null. ", ticketId);
                return null;
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(value);
            ObjectInputStream ois = null;
            ois = new ObjectInputStream(bais);
            final Ticket t = (Ticket) ois.readObject();
            if (t != null) {
                return getProxiedTicketInstance(t);
            }
        } catch (final Exception e) {
            logger.error("Failed fetching {}. ", ticketId, e);
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * {@inheritDoc} * This operation is not supported. * * @throws UnsupportedOperationException if you try and call this operation.
     */
    @Override
    public Collection<Ticket> getTickets() {
        throw new UnsupportedOperationException("GetTickets not supported.");
    }

    /**
     * Destroy the client and shut down. * * @throws Exception the exception
     */
    public void destroy() throws Exception {
        jedisPool.destroy();
    }

    @Override
    protected boolean needsCallback() {
        return true;
    }

    /**
     * Gets the timeout value for the ticket. * * @param t the t * @return the timeout
     */
    private int getTimeout(final Ticket t) {
        if (t instanceof TicketGrantingTicket) {
            return this.tgtTimeout;
        } else if (t instanceof ServiceTicket) {
            return this.stTimeout;
        }
        throw new IllegalArgumentException("Invalid ticket type");
    }

    public void setJedisSentinelPool(JedisSentinelPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public int getStTimeout() {
        return stTimeout;
    }

    public void setStTimeout(int stTimeout) {
        this.stTimeout = stTimeout;
    }

    public void setTgtTimeout(int tgtTimeout) {
        this.tgtTimeout = tgtTimeout;
    }
}
