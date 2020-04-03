import org.jenkinsci.plugins.credentialsbinding.impl.UsernamePasswordMultiBinding
import de.jenkins.util.ArtifactoryDockerRepository

def List<String> getSome() {
    a = new ArtifactoryDockerRepository()
    b = new UsernamePasswordMultiBinding()
    return a.dockerChoices(b)
}