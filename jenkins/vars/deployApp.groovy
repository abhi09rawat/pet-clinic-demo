pipeline {
  agent any

  stages {

    stage('Clean Workspace') {
      steps {
        // Clean the workspace
        cleanWs()
      }
    }

    stage('Checkout') {
      steps {
        // clone your project from Git/SVN etc
        sh 'git clone https://github.com/abhi09rawat/pet-clinic-demo.git'
        //git 'https://github.com/abhi09rawat/pet-clinic-demo.git'
      }
    }

    /*stage('Static Code Testing') {
      steps {
        // Test (Unit test / Automation test(Selenium/Robot framework) / etc.)
        sh """
                    mvn sonar:sonar \
                   -Dsonar.projectKey=Pet-clinic-scan \
                   -Dsonar.host.url=http://10.95.8.130:9000 \
                   -Dsonar.login=7d34b87f4d1429379dc0ec81294756885b97aebc
                   -X > debug.log
                   """  
      }
    }*/

    stage('Build and image creation') {
      steps {
        // build
        sh """
              cd pet-clinic-demo \
              ./mvnw spring-boot:build-image
              cd ..
              """
      }
    }

    
/*
    stage('Code Analysis') {
      steps {
        // Static Code analysis (Coverity/ SonarQube /openvas/Nessus etc.)
      }
    }

    stage('Generate Release Notes') {
      steps {
        // Release note generation .
      }
    }

    stage('Tagging') {
      steps {
        // Tagging specific version number
      }
    }

    stage('Release') {
      steps {
        // release specific versions(Snapshot / release / etc.)
      }
    }

    stage('Deploy') {
      steps {
        // Deploy to cloud providers /local drives /artifactory etc.
        // Deploy to Deploy/prod /test/ etc
      }
    }*/
  }

  post {
    success {
      echo "SUCCESS"
    }
    failure {
      echo "FAILURE"
    }
    changed {
      echo "Status Changed: [From: $currentBuild.previousBuild.result, To: $currentBuild.result]"
    }
    always {
      script {
        def result = currentBuild.result
        if (result == null) {
          result = "SUCCESS"
        }
      }
    }
  }
}
