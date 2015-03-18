package com.mycompany.mydevice;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.samsung.sami.manifest.fields.Field;
import com.samsung.sami.manifest.test.ManifestTest;

public class TestHeartRateTracker extends ManifestTest {
    @Test
    public void testCalorieTracker() throws IOException {
        String manifestPath = "/manifests/HeartRateTrackerManifest.groovy";
        String dataPath = "/data/HeartRateTrackerData.json";
        runManifestTest(manifestPath, dataPath);
    }

    private void runManifestTest(String manifestPath, String dataPath) throws IOException {
        Map<String, Field> fields = runGroovyManifest(manifestPath, dataPath);

        printManifestRunResults(fields);
        Assert.assertNotNull(fields);
        Assert.assertFalse(fields.isEmpty());
 
        // Verify that the Manifest produces the correct value for each field
        Assert.assertEquals(70, fields.get("heartRate").getValue());
        Assert.assertEquals("blah", fields.get("comments").getValue());
    }

    private void printManifestRunResults(Map<String, Field> fields) {
        for (Field field : fields.values())
            System.out.println(field.toString());
    }

}
