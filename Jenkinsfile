properties([parameters([booleanParam(defaultValue: true, description: '', name: 'running')]), [$class: 'ThrottleJobProperty', categories: [], limitOneJobWithMatchingParams: false, maxConcurrentPerNode: 1, maxConcurrentTotal: 1, paramsToUseForLimit: '', throttleEnabled: true, throttleOption: 'category'], pipelineTriggers([[$class: 'PeriodicFolderTrigger', interval: '1m']])])

stage('Build') {
  node {
    checkout scm
  }
}

stage('Static Code Analysis') {

  node {
    sh "echo 'Run Static Code Analysis'"
  }
}

stage('Unit Tests') {
  node {
    println env.BRANCH_NAME
    timeout(240) {
      waitUntil {
          def executor = env.EXECUTOR_NUMBER
          echo " branch: ${BRANCH_NAME}"
          echo " build display name: ${env.BUILD_DISPLAY_NAME}"
          echo " node name: ${env.NODE_NAME} "
          echo " executor count: ${env.EXECUTOR_NUMBER}"
          return (executor == 2);
      }
    }
  }
}

stage('Acceptance Tests') {
  node {
    sh "echo 'Run Acceptance Tests'"
  }
}

stage('Nofification') {
  node {
    sh "echo 'Send Notifications'"
  }
}
