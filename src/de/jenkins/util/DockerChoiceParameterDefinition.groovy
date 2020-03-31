package de.jenkins.util

import hudson.model.ParameterValue
import hudson.model.SimpleParameterDefinition
import hudson.model.StringParameterValue
import net.sf.json.JSONObject
import org.apache.commons.lang.NotImplementedException
import org.kohsuke.stapler.StaplerRequest

class DockerChoiceParameterDefinition extends SimpleParameterDefinition {

    private final List<String> choices = Arrays.asList("docker-1", "docker-2");

    protected DockerChoiceParameterDefinition(String name) {
        super(name);
    }

    protected DockerChoiceParameterDefinition(String name, String description) {
        super(name, description);
    }

    @Override
    public ParameterValue createValue(String value) {
        return checkValue(value);
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        throw new NotImplementedException();
    }

    protected StringParameterValue checkValue(String value) {
        if (choices.contains(value)) {
            return new StringParameterValue(getName(), value, getDescription());
        }
        throw new IllegalArgumentException("Illegal value. Please chose: " + String.join(" ", choices));
    }
}
