
stage('Build') {
  properties([disableConcurrentBuilds(), parameters([[$class: 'CredentialsParameterDefinition', credentialType: 'com.cloudbees.plugins.credentials.common.StandardCredentials', defaultValue: '64f9f9ba-7843-4a71-a4ee-6e357d2bd5a7', description: '', name: 'credential', required: false]]), [$class: 'ThrottleJobProperty', categories: [], limitOneJobWithMatchingParams: true, maxConcurrentPerNode: 1, maxConcurrentTotal: 1, paramsToUseForLimit: 'credential', throttleEnabled: true, throttleOption: 'category'], pipelineTriggers([[$class: 'PeriodicFolderTrigger', interval: '1m']])])
  node {
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
