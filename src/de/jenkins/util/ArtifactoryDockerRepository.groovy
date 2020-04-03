package de.jenkins.util

import org.jenkinsci.plugins.credentialsbinding.impl.UsernamePasswordMultiBinding

class ArtifactoryDockerRepository {

    private String baseUrl = "https://localhost"

    public List<String> dockerChoices(UsernamePasswordMultiBinding auth) {
        if (auth != null) {
            System.out.println(auth)
        }
        return new ArrayList<String>()
    }
}
