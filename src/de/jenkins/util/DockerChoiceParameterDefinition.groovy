package de.jenkins.util

import hudson.Extension
import hudson.model.ParameterDefinition
import hudson.model.ParameterValue
import hudson.model.SimpleParameterDefinition
import hudson.model.StringParameterValue
import hudson.util.FormValidation
import net.sf.json.JSONObject
import org.jenkinsci.Symbol
import org.kohsuke.stapler.QueryParameter
import org.kohsuke.stapler.StaplerRequest

import javax.annotation.Nonnull
import javax.annotation.Nullable

class DockerChoiceParameterDefinition extends SimpleParameterDefinition {

    private final List<String> choices = Arrays.asList("docker-1", "docker-2");

    protected DockerChoiceParameterDefinition(String name) {
        super(name);
    }

    protected DockerChoiceParameterDefinition(String name, String description) {
        super(name, description);
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        StringParameterValue value = req.bindJSON(StringParameterValue.class, jo);
        value.setDescription(getDescription());
        return checkValue(value.value);
    }

    @Override
    public StringParameterValue createValue(String value) {
        return checkValue(value);
    }

    protected StringParameterValue checkValue(String value) {
        if (choices.contains(value)) {
            return new StringParameterValue(getName(), value, getDescription());
        }
        throw new IllegalArgumentException("Illegal value. Please chose: " + String.join(" ", choices));
    }

    @Extension @Symbol("dockerChoice")
    public static class DescriptorImpl extends ParameterDefinition.ParameterDescriptor {
        @Override
        public String getDisplayName() {
            return Messages.ChoiceParameterDefinition_DisplayName();
        }

        @Override
        public String getHelpFile() {
            return "/help/parameter/choice.html";
        }

        @Override
        /*
         * We need this for JENKINS-26143 -- reflective creation cannot handle setChoices(Object). See that method for context.
         */
        public ParameterDefinition newInstance(@Nullable StaplerRequest req, @Nonnull JSONObject formData) throws FormException {
            String name = formData.getString("name");
            String desc = formData.getString("description");
            return new DockerChoiceParameterDefinition(name, desc);
        }

        /**
         * Checks if parameterized build choices are valid.
         */
        public FormValidation doCheckChoices(@QueryParameter String value) {
            if (ChoiceParameterDefinition.areValidChoices(value)) {
                return FormValidation.ok();
            } else {
                return FormValidation.error(Messages.ChoiceParameterDefinition_MissingChoices());
            }
        }
    }
}
