package cn.com.hellowood.dynamicdatasource.configuration;


import cn.com.hellowood.dynamicdatasource.common.DataSourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Multiple DataSource Context Holder
 *
 * @author HelloWood
 * @date 2017 -08-15 14:26
 * @Email hellowoodes @gmail.com
 */
public class DynamicDataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static Lock lock = new ReentrantLock();

    private static int counter = 0;

    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.master::name);


    /**
     * All DataSource List
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * The constant slaveDataSourceKeys.
     */
    public static List<Object> slaveDataSourceKeys = new ArrayList<>();

    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * Use master data source.
     */
    public static void useMasterDataSource() {
        CONTEXT_HOLDER.set(DataSourceKey.master.name());
    }

    /**
     * Use slave data source.
     */
    public static void useSlaveDataSource(String tokenId) {
        lock.lock();

        try {
            /*int datasourceKeyIndex = counter % slaveDataSourceKeys.size();
            CONTEXT_HOLDER.set(String.valueOf(slaveDataSourceKeys.get(datasourceKeyIndex)));
            counter++;*/
            if (StringUtils.isEmpty(tokenId)) {
                CONTEXT_HOLDER.set(DataSourceKey.master.name());
                logger.info(CONTEXT_HOLDER.get());
                return;
            }
            if (tokenId.contains("123")) {
                CONTEXT_HOLDER.set(DataSourceKey.slaveAlpha.name());
            } else if (tokenId.contains("456")) {
                CONTEXT_HOLDER.set(DataSourceKey.slaveBeta.name());
            } else if (tokenId.contains("789")) {
                CONTEXT_HOLDER.set(DataSourceKey.slaveGamma.name());
            } else {
                CONTEXT_HOLDER.set(DataSourceKey.master.name());
            }
            logger.info(tokenId + "====" + CONTEXT_HOLDER.get());
        } catch (Exception e) {
            logger.error("Switch slave datasource failed, error message is {}", e.getMessage());
            useMasterDataSource();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * Check if give DataSource is in current DataSource list
     *
     * @param key the key
     * @return boolean boolean
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}
