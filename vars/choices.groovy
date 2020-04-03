import de.jenkins.util.ArtifactoryDockerRepository

def List<String> getSome() {
    a = new ArtifactoryDockerRepository()
    return a.dockerChoices(null)
}