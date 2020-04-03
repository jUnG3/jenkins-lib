package de.jenkins.util

import org.jenkinsci.plugins.credentialsbinding.impl.UsernamePasswordMultiBinding

class ArtifactoryDockerRepository {

    private String baseUrl = "https://localhost"

    public List<String> dockerChoices(UsernamePasswordMultiBinding auth) {
        return new ArrayList<String>()
    }
}
