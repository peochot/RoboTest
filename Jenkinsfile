
stage('Build') {
  properties([disableConcurrentBuilds(), overrideIndexTriggers(false), [$class: 'ThrottleJobProperty', categories: [], limitOneJobWithMatchingParams: false, maxConcurrentPerNode: 1, maxConcurrentTotal: 1, paramsToUseForLimit: '', throttleEnabled: true, throttleOption: 'project'], pipelineTriggers([[$class: 'PeriodicFolderTrigger', interval: '1m']])])
  node {
  lock(resource: "lock_${env.NODE_NAME}_${env.BRANCH_NAME}", inversePrecedence: true) {
    checkout scm
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
}
