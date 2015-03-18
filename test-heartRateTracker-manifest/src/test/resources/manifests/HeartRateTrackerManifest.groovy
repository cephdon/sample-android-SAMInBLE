import groovy.json.JsonSlurper
import com.samsung.sami.manifest.Manifest
import com.samsung.sami.manifest.fields.*
import static com.samsung.sami.manifest.fields.StandardFields.*
import static com.samsung.sami.manifest.groovy.JsonUtil.*

public class HeartRateTrackerManifest implements Manifest {
	static final COMMENTS = new FieldDescriptor("comments", String.class)

	@Override
	List<Field> normalize(String input) {
        def slurper = new JsonSlurper()
        def json = slurper.parseText(input)

        def fields = []

        addToList(fields, json, "heart_rate", HEART_RATE)
		addToList(fields, json, COMMENTS)

		return fields
	}

	@Override
	List<FieldDescriptor> getFieldDescriptors() {
		return [HEART_RATE, COMMENTS]
	}
}