package com.sdm.hw.common.capability;

import com.sdm.hw.logging.intf.HwLogger;
import com.sdm.hw.logging.services.LogManager;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * This framework class is implemented as a singleton pattern. It is the main class for loading and caching Capability
 * related information used by the application.
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */
public final class CapabilityManager {
    private static final String CAPABILITY_CONFIG_FILE = "capability.xml";
    // Following tokens are used for building XPath for a capability.
    private static final String XPATH_PREFIX = "capabilityGroup[@name='";
    private static final String CLOSE_BRACKET = "']";
    private static final String FORWARD_SLASH = "/";
    private static final String ENABLED_TAG = "enabled[@code='";
    private static final String CAPABILITY_NAME_TAG = "capability[@name='";
    private static final String VALUE_TAG = "value[@code='";
    private static final boolean ENABLE_LOCAL_CACHE = true;
    private static final int CONFIG_TRIGGER_PERIOD = 30;
    private static final TimeUnit CONFIG_TRIGGER_UNIT = TimeUnit.SECONDS;
    private static final int CONFIG_RETRY_INTERVAL = 30;
    private static final HwLogger LOGGER = LogManager.getLogger(CapabilityManager.class);
    // Singleton Class initialization
    private static volatile CapabilityManager _instance = null;
    private String curProvinceCode;
    // the xmlConfiguration variable should be only used in initConfig() and getConfig() only
    private XMLConfiguration xmlConfiguration = null;
    private CapabilityCache capabilityCache = new CapabilityCache();

    /**
     * Private constructor for singleton
     */
    private CapabilityManager() {
    }

    public static synchronized CapabilityManager getInstance() {
        if (_instance == null) {
            _instance = new CapabilityManager();
        }
        return _instance;
    }

    /**
     * This method resets CapabilityManager singleton instance
     */
    public static synchronized void reset() {
        _instance = null;
        _instance = getInstance();
    }

    private void initConfig() throws Exception {
        clearCache();

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n"
                + CAPABILITY_CONFIG_FILE
                + " file will be searched/loaded from the following classpath and checked every "
                + CONFIG_TRIGGER_PERIOD + " " + CONFIG_TRIGGER_UNIT + " for any change:\n");
        for (URL url : urls) {
            stringBuilder.append("\t" + url.getFile() + "\n");
        }
        LOGGER.logWarn(stringBuilder.toString());
        xmlConfiguration = new XMLConfiguration(CAPABILITY_CONFIG_FILE);
        xmlConfiguration.setSchemaValidation(true);
        xmlConfiguration.setExpressionEngine(new XPathExpressionEngine());
        final FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
        strategy.setRefreshDelay(CONFIG_TRIGGER_UNIT.toMillis(CONFIG_TRIGGER_PERIOD));
        xmlConfiguration.setReloadingStrategy(strategy);
        // This will throw a ConfigurationException if the XML document does not
        // conform to its Schema.
        xmlConfiguration.load();
        LOGGER.logInfo("Following capability file successfully loaded: "
                + xmlConfiguration.getFile().getAbsolutePath());
        xmlConfiguration.addConfigurationListener(
                new ConfigurationListener() {
                    @Override
                    public void configurationChanged(ConfigurationEvent event) {
                        if (!event.isBeforeUpdate()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("\n");
                            stringBuilder.append("\tReceived event!\n");
                            stringBuilder.append("\tType = " + event.getType() + "\n");
                            if (event.getPropertyName() != null) {
                                stringBuilder.append("\tProperty name = " + event.getPropertyName() + "\n");
                            }
                            if (event.getPropertyValue() != null) {
                                stringBuilder.append("\tProperty value = " + event.getPropertyValue() + "\n");
                            }
                            LOGGER.logWarn(stringBuilder.toString());
                        }
                    }
                }
        );
    }

    /**
     * This method is used to get the configuration object. It is used by various method to get the properly
     * initialized configuration object
     * @return XMLConfiguration
     */
    private XMLConfiguration getConfig() {
        // keep trying to get the init if the configuration is returned null or
        // not validating.
        while (xmlConfiguration == null
                || xmlConfiguration.getReloadingStrategy().reloadingRequired()
                || !xmlConfiguration.isValidating()) {

            try {
                initConfig();
            } catch (Exception ex) {
                LOGGER.logError(ex.getMessage(), ex);
                xmlConfiguration = null;
                clearCache();
                LOGGER.logWarn("... correct the configuration file and make sure it is validated" +
                        " against the schema. System will retry in " + CONFIG_RETRY_INTERVAL + " seconds.");
                try {
                    TimeUnit.SECONDS.sleep(CONFIG_RETRY_INTERVAL);
                } catch (InterruptedException iex) {
                    LOGGER.logError(ex.getMessage(), iex);
                }
            }
        }
        return xmlConfiguration;
    }

    private void validateCache() {
        // clear the cache if capability file changed.
        if (getConfig().getReloadingStrategy().reloadingRequired()) {
            clearCache();
        }
    }

    /**
     * This method reloads province from database
     */
    private void reloadCurProvinceCode() {
        ProvinceProvider provinceProvider = ProvinceProvider.getInstance();
        provinceProvider.reloadProvince();
        curProvinceCode = provinceProvider.getCurrentProvince().getCode();
    }

    /**
     * This method clears cachce only
     */
    public void clearCache() {
        reloadCurProvinceCode();
        capabilityCache.cleanCache();
    }

    /**
     * This method is used to determine if a capability is enable or not
     *
     * @param key an enum of CapabilityKey type
     * @return boolean true or false
     */
    public boolean getBoolean(CapabilityKey key) {
        Boolean enabled;
        if (ENABLE_LOCAL_CACHE) {
            validateCache();
            enabled = capabilityCache.getBoolean(key);
        }
        if (enabled == null) { // capability  is not cached yet.
            boolean groupEnabled = isGroupEnabled(key);
            if (key.isGroup()) {
                enabled = groupEnabled;
            } else {
                // both group and key should be true for a capability to be true
                enabled = groupEnabled && getConfig().getBoolean(getCapabiltyXpath(key));
            }
            // add capability value to the cache
            capabilityCache.setBoolean(key, enabled);
        }
        return enabled;
    }

    /**
     * This method is used to return a text of the Capability
     *
     * @param key an enum of CapabilityKey type
     * @return String a text value of the capability
     */
    public String getString(CapabilityKey key) {
        String val;
        if (ENABLE_LOCAL_CACHE) {
            validateCache();
            val = capabilityCache.getString(key);
        }
        if (val == null) {// capability  is not cached yet.
            val = getConfig().getString(getCapabiltyXpath(key));
            // add capability value to the cache
            capabilityCache.setString(key, val);
        }
        return val;
    }

    /**
     * This method is used to return a long value of a Capability
     *
     * @param key an enum of CapabilityKey type
     * @return long an long value of the capability
     */
    public long getLong(CapabilityKey key) {
        Long val;
        if (ENABLE_LOCAL_CACHE) {
            validateCache();
            val = capabilityCache.getLong(key);
        }
        if (val == null) {// capability  is not cached yet.
            val = getConfig().getLong(getCapabiltyXpath(key));
            // add capability value to the cache
            capabilityCache.setLong(key, val);
        }
        return val;
    }

    /**
     * This method is used to return a double value of a Capability
     *
     * @param key an enum of CapabilityKey type
     * @return double a double value of the capability
     */
    public double getDouble(CapabilityKey key) {
        Double val;
        if (ENABLE_LOCAL_CACHE) {
            validateCache();
            val = capabilityCache.getDouble(key);
        }
        if (val == null) {// capability  is not cached yet.
            val = getConfig().getDouble(getCapabiltyXpath(key));
            // add capability value to the cache
            capabilityCache.setDouble(key, val);
        }
        return val;
    }

    private String getGroupXPath(CapabilityKey key) {
        StringBuilder groupXpath = new StringBuilder();

        String[] tokens = key.toString().split(Pattern.quote(CapabilityKey.EXPRESSION_DELIMITOR));
        int numOfGroups = key.isGroup() ?
                tokens.length : // the expression contains group only
                tokens.length - 1; // last one is not a group
        for (int i = 0; i < numOfGroups; i++) {
            groupXpath.append(XPATH_PREFIX);
            groupXpath.append(tokens[i]);
            groupXpath.append(CLOSE_BRACKET);
            groupXpath.append(FORWARD_SLASH);
        }
        return groupXpath.toString();
    }

    private String getCapabiltyXpath(CapabilityKey key) {
        String[] tokens = key.toString().split(Pattern.quote(CapabilityKey.EXPRESSION_DELIMITOR));
        String capability = tokens[tokens.length - 1];
        StringBuilder capabilityXPath = new StringBuilder();
        capabilityXPath.append(getGroupXPath(key));
        capabilityXPath.append(CAPABILITY_NAME_TAG);
        capabilityXPath.append(capability);
        capabilityXPath.append(CLOSE_BRACKET);
        capabilityXPath.append(FORWARD_SLASH);
        capabilityXPath.append(VALUE_TAG);
        capabilityXPath.append(curProvinceCode);
        capabilityXPath.append(CLOSE_BRACKET);
        return capabilityXPath.toString();
    }

    private boolean isGroupEnabled(CapabilityKey key) {
        boolean groupEnable = true;

        StringBuilder groupXPath = new StringBuilder();

        String[] tokens = key.toString().split(Pattern.quote(CapabilityKey.EXPRESSION_DELIMITOR));
        int numOfGroups = key.isGroup() ?
                tokens.length : // the expression contains group only
                tokens.length - 1; // last one is not a group
        for (int i = 0; i < numOfGroups; i++) {
            groupXPath.append(XPATH_PREFIX);
            groupXPath.append(tokens[i]);
            groupXPath.append(CLOSE_BRACKET);
            groupXPath.append(FORWARD_SLASH);
            if (!getConfig().getBoolean(groupXPath.toString() + ENABLED_TAG + curProvinceCode + CLOSE_BRACKET)) {
                // if any of the groups in hierarchy is disabled, all the sub group will be considered disabled
                groupEnable = false;
                break;
            }
        }
        return groupEnable;
    }

    /**
     * Inner class encapsulating capability cache
     */
    private static class CapabilityCache {
        // Hash map storing capability cache
        private Map<CapabilityKey, Object> cacheMap = new HashMap<>();

        // getters and setters
        private Boolean getBoolean(CapabilityKey key) {
            return (Boolean) cacheMap.get(key);
        }

        private void setBoolean(CapabilityKey key, Boolean val) {
            cacheMap.put(key, val);
        }

        private String getString(CapabilityKey key) {
            return (String) cacheMap.get(key);
        }

        private void setString(CapabilityKey key, String val) {
            cacheMap.put(key, val);
        }

        private Long getLong(CapabilityKey key) {
            return (Long) cacheMap.get(key);
        }

        private void setLong(CapabilityKey key, Long val) {
            cacheMap.put(key, val);
        }

        private Double getDouble(CapabilityKey key) {
            return (Double) cacheMap.get(key);
        }

        private void setDouble(CapabilityKey key, Double val) {
            cacheMap.put(key, val);
        }

        private void cleanCache() {
            cacheMap.clear();
        }
    }
}
