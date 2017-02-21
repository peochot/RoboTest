{ ->
  node {
      stage("Build") {
          echo "Building"
          try {
            def build = currentBuild
            currentBuild.result = "STARTED"
            def color = "GREEN"
            notifyHipchat()
          } catch(e) {
            currentBuild.result = "FAILED"
            notifyHipchat()
            throw e;
          }
      }

      stage("Unit Test") {
          echo "Unit testing"
      }

      branch(/^master$/){
          stage("Pushing to stage") {
              echo "Push"
          }
          stage("Trigger stage job") {
              echo "Deploying"
          }
      }


  }
}

def notifyHipchat() {
  hipchatSend (color: "RED", notify: true, message: "Job: '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL}) ")
}

@NonCPS
def summarizeBuild(b) {
  b.changeSets.collect { cs ->
    /kind=${cs.kind}; entries=/ + cs.collect { entry ->
      /${entry.commitId} by ${entry.author.id} ~ ${entry.author.fullName} on ${new Date(entry.timestamp)}: ${entry.msg}:  /+ entry.affectedFiles.collect { file ->
        /${file.editType.name} ${file.path}/
      }.join('; ')
    }.join(', ')
  }.join(' & ')
}

@NonCPS
def branch(pattern, body) {
    echo "Current branch: ${env.BRANCH_NAME}"
    echo "Pattern: ${pattern}"
    def matcher = (env.BRANCH_NAME =~ pattern)
    if(matcher.matches())
       body()
    else
       echo "Branch doesn't match the pattern. Skipping step"
}
