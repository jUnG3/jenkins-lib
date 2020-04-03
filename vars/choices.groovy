import de.jenkins.util.ArtifactoryDockerRepository

def List<String> getSome() {
    a = new ArtifactoryDockerRepository()
    return a.dockerChoices(usernamePassword(credentialsId: 'test-cred', usernameVariable: 'username', passwordVariable: 'password'))
}