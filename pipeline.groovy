{ ->
  node {
      stage("Build") {
          echo "Building"
          def build = currentBuild
          currentBuild.result = "STARTED"
          def color = "GREEN"
          notify(build, color)
          
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

def notify(build, color) {
  hipchatSend (color: color, notify: true, message: "Job: '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL}) branch (${env.BRANCH_NAME}) \n "
                                                      + "Status : ${build.result}")
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
def reportOnTestsForBuild() {
  def build = manager.build
  println("Build Number: ${build.number}")
  if (build.getAction(hudson.tasks.junit.TestResultAction.class) == null) {
    println("No tests")
    return ("No Tests")
  }

  // The string that will contain our report.
  String emailReport;

  emailReport = "URL: ${env.BUILD_URL}\n"

  def testResults =    build.getAction(hudson.tasks.junit.TestResultAction.class).getFailCount();
  def failed = build.getAction(hudson.tasks.junit.TestResultAction.class).getFailedTests()
  println("Failed Count: ${testResults}")
  println("Failed Tests: ${failed}")
  def failures = [:]

  def result = build.getAction(hudson.tasks.junit.TestResultAction.class).result

  if (result == null) {
    emailReport = emailReport + "No test results"
  } else if (result.failCount < 1) {
    emailReport = emailReport + "No failures"
  } else {
    emailReport = emailReport + "overall fail count: ${result.failCount}\n\n"
  failedTests = result.getFailedTests();

  failedTests.each { test ->
    failures.put(test.fullDisplayName, test)
    emailReport = emailReport + "\n-------------------------------------------------\n"
    emailReport = emailReport + "Failed test: ${test.fullDisplayName}\n" +
    "name: ${test.name}\n" +
    "age: ${test.age}\n" +
    "failCount: ${test.failCount}\n" +
    "failedSince: ${test.failedSince}\n" +
    "errorDetails: ${test.errorDetails}\n"
    }
  }
  return (emailReport)
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
