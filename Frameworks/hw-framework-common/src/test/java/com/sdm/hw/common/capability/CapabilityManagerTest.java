package com.sdm.hw.common.capability;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2018-12-21
 */


public class CapabilityManagerTest extends CapabilityTest {

    @Test
    public void testBoolean() {
        provinceProvider.setCurrentProvince(Province.NOVA_SCOTIA);
        capabilityManager.clearCache();
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));

        provinceProvider.setCurrentProvince(Province.NEW_BRUNSWICK);
        capabilityManager.clearCache();
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));

        provinceProvider.setCurrentProvince(Province.ONTARIO);
        capabilityManager.clearCache();
        assertEquals(false, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
    }

    @Test
    public void testString() {
        capabilityManager.clearCache();
        provinceProvider.setCurrentProvince(Province.NOVA_SCOTIA);
        assertEquals("BOTH", capabilityManager.getString(CapabilityStringKey.DECN_BUSINESS_ENTITY_TYPE));

        capabilityManager.clearCache();
        provinceProvider.setCurrentProvince(Province.NEW_BRUNSWICK);
        assertEquals("BOTH", capabilityManager.getString(CapabilityStringKey.DECN_BUSINESS_ENTITY_TYPE));

        capabilityManager.clearCache();
        provinceProvider.setCurrentProvince(Province.ONTARIO);
        assertEquals("N", capabilityManager.getString(CapabilityStringKey.DECN_BUSINESS_ENTITY_TYPE));
    }

    @Test
    public void testInt() {
        provinceProvider.setCurrentProvince(Province.NOVA_SCOTIA);
        capabilityManager.clearCache();
        assertEquals(100, capabilityManager.getLong(CapabilityLongKey.TEST_2_LEVEL_GROUP));

        provinceProvider.setCurrentProvince(Province.NEW_BRUNSWICK);
        capabilityManager.clearCache();
        assertEquals(200, capabilityManager.getLong(CapabilityLongKey.TEST_2_LEVEL_GROUP));

        provinceProvider.setCurrentProvince(Province.ONTARIO);
        capabilityManager.clearCache();
        assertEquals(999, capabilityManager.getLong(CapabilityLongKey.TEST_2_LEVEL_GROUP));
    }

    @Test
    public void testDouble() {
        provinceProvider.setCurrentProvince(Province.NOVA_SCOTIA);
        capabilityManager.clearCache();
        assertEquals(1.5, capabilityManager.getDouble(CapabilityDoubleKey.TEST_2_LEVEL_GROUP), 0.0);

        provinceProvider.setCurrentProvince(Province.NEW_BRUNSWICK);
        capabilityManager.clearCache();
        assertEquals(2.5, capabilityManager.getDouble(CapabilityDoubleKey.TEST_2_LEVEL_GROUP), 0.0);

        provinceProvider.setCurrentProvince(Province.ONTARIO);
        capabilityManager.clearCache();
        assertEquals(10.9, capabilityManager.getDouble(CapabilityDoubleKey.TEST_2_LEVEL_GROUP), 0.0);
    }

    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testIllFormednessConfigAtStart() throws Exception {
        badConfigAtStartExecTestHelper(illFormedConfig);
    }

    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testInvalidConfigAtStart() throws Exception {
        badConfigAtStartExecTestHelper(invalidConfig);
    }

    private void badConfigAtStartExecTestHelper(File badConfig) throws Exception {
        // Following line copies an bad config file into config file. This will cause a ConfigurationException
        // and put the framework in a loop util the configuration is fixed.
        copyFile(badConfig, capabilityConfig);
        provinceProvider.setCurrentProvince(Province.NOVA_SCOTIA);
        // Reset the capability manager to simulate a behaviour which would be similar at the starting of the System.
        CapabilityManager.reset();
        capabilityManager = CapabilityManager.getInstance();
        // Following line copies a valid file into config file in a separate thread which will break the above loop.
        copyFile(validConfig, capabilityConfig, TimeUnit.SECONDS, DELAY);
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
    }

    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testIllFormedConfigMidExec() throws IOException {
        badConfigMidExecTestHelper(illFormedConfig);
    }

    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testInvalidConfigMidExec() throws IOException {
        badConfigMidExecTestHelper(invalidConfig);
    }

    /**
     * Helper function to test IllFormed Configuraiton scenarios
     */
    private void badConfigMidExecTestHelper(File badConfig) throws IOException {
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
        // Following line copies an ill-Formed config file into config file. This will cause a ConfigurationException
        // and put the framework in a loop util the configuration is fixed.
        copyFile(badConfig, capabilityConfig);
        // Sleep to let the system load badConfig configuration file before processing it.
        sleep(TimeUnit.SECONDS, DELAY);
        // Following line copies a valid file into config file in a separate thread which will let system proceed.
        copyFile(validConfig, capabilityConfig, TimeUnit.SECONDS, DELAY);
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
    }

    /**
     * Test handling of deleted config file at start
     */
    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testDeletedConfigAtStart() throws IOException {
        deleteCapabilityFile();
        assertEquals(false, capabilityConfig.exists());
        CapabilityManager.reset();
        capabilityManager = CapabilityManager.getInstance();
        // Sleep to let the system load sense the missng file.
        sleep(TimeUnit.SECONDS, DELAY);
        // Following line copies a valid file into config file in a separate thread which will let system proceed.
        copyFile(validConfig, capabilityConfig, TimeUnit.SECONDS, DELAY);
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
    }

    /**
     * Test handling of deleted config file during execution
     */
    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testDeletedConfigMidExecution() throws IOException {
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
        deleteCapabilityFile();
        assertEquals(false, capabilityConfig.exists());
        // Sleep to let the system load sense the missing file.
        sleep(TimeUnit.SECONDS, DELAY);
        // Following line copies a valid file into config file in a separate thread which will let system proceed.
        copyFile(validConfig, capabilityConfig, TimeUnit.SECONDS, DELAY);
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
    }

    /**
     * Test handling of misplaced config file at start
     */
    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testMisplacedConfigAtStart() throws IOException {
        moveCapabilityToTempDir();
        CapabilityManager.reset();
        capabilityManager = CapabilityManager.getInstance();
        assertEquals(false, capabilityConfig.exists());
        // Sleep to let the system load sense the missng file.
        sleep(TimeUnit.SECONDS, DELAY);
        // Following line copies a valid file into config file in a separate thread which will let system proceed.
        copyFile(validConfig, capabilityConfig, TimeUnit.SECONDS, DELAY);
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
        deleteTempDir();
    }

    /**
     * Test handling of misplaced config file during execution
     */
    @Ignore("Test is ignored for regular Junit testing. It should be executed when any change is performed in this package.")
    @Test
    public void testMisplacedConfigMidExecution() throws IOException {
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
        moveCapabilityToTempDir();
        assertEquals(false, capabilityConfig.exists());
        // Sleep to let the system load sense the missng file.
        sleep(TimeUnit.SECONDS, DELAY);
        // Following line copies a valid file into config file in a separate thread which will let system proceed.
        copyFile(validConfig, capabilityConfig, TimeUnit.SECONDS, DELAY);
        assertEquals(true, capabilityManager.getBoolean(CapabilityBooleanKey.ALLERGY_STATUS));
        deleteTempDir();
    }
}
