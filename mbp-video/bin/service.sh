#!/bin/bash

OES_CONTENT_HOME=/opt/app/mbp
APP_NAME=video
APP_HOME=${OES_CONTENT_HOME}/${APP_NAME}
APP_VERSION=1.0.0

LOG_HOME=${OES_CONTENT_HOME}/logs
LOG_PATH=${LOG_HOME}/${APP_NAME}
ENV_LIST=("default" "dev" "test" "preview" "prod")

REMOTE_PORT=6091

SERVICE_LIB=${APP_HOME}/lib
SERVICE_CONF=${APP_HOME}/conf
SERVICE_RESOURCES=${APP_HOME}/resources

if [[ -n "$2" ]]; then
  PROJECT_ENV=$2
else
  PROJECT_ENV="dev"
fi

safemkdir(){
  if [[ ! -d $1 ]] ; then
    mkdir -p $1
  fi
}

# Production config
if [[ "$PROJECT_ENV" == "prod" ]]; then
    JAVA_OPTIONS="-server -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_PATH -Xms8G -Xmx8G -Xmn3G -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=512M -XX:+DisableExplicitGC -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled -XX:LargePageSizeInBytes=128M -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:SoftRefLRUPolicyMSPerMB=0"
else
    JAVA_OPTIONS="-server -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOG_PATH -Xms512M -Xmx2G -Xmn256M -XX:MetaspaceSize=128M -XX:MaxMetaspaceSize=256M -XX:+DisableExplicitGC -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled -XX:LargePageSizeInBytes=128M -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:SoftRefLRUPolicyMSPerMB=0"
fi

if [[ "$PROJECT_ENV" == "dev" || "$PROJECT_ENV" == "test" ]]; then
  JAVA_OPTIONS="-Xdebug -Xrunjdwp:transport=dt_socket,address=$REMOTE_PORT,server=y,suspend=n $JAVA_OPTIONS"
fi

JAVA_OPTIONS="-XX:ErrorFile=$LOG_PATH/hs_err_pid%p.log $JAVA_OPTIONS -Dspring.config.additional-location=$SERVICE_CONF/ -Dspring.profiles.active=$PROJECT_ENV -Dapp.name=$APP_NAME -Dlog.home=$LOG_HOME -Denv=$PROJECT_ENV -Djava.security.egd=file:/dev/./urandom"

export CLASSPATH=${CLASSPATH}:${SERVICE_CONF}:${SERVICE_RESOURCES}
export JAVA_OPTIONS
export PID_FILE=${APP_HOME}/.pid/${APP_NAME}.pid
dateFormat=$(date '+%Y-%m-%d')

safemkdir ${APP_HOME}/.pid/
safemkdir ${LOG_PATH}/

ACCESS_LOG=${LOG_PATH}/${dateFormat}.info.log
ERROR_LOG=${LOG_PATH}/${dateFormat}.error.log

hint="Usage: start "
hasEnv=0

checkEnvParam(){
  for env_name in ${ENV_LIST[@]};do
    if [[ "${env_name}" == "$1" ]] ; then
        hasEnv=1
    fi
  done
}

getUsageHint(){
  for env_name in ${ENV_LIST[@]};do
    hint=${hint}${env_name}" or "
  done

  hint=${hint}"|stop|log|error"
}

checkEnvParam $2
getUsageHint

started="#################################################################################\n
           Application [${APP_NAME}] for environment [${PROJECT_ENV}] Started At  $(date) \n
        ##################################################################################\n"

stopped="#################################################################################\n
                Application [${APP_NAME}] Stopped At  $(date)                             \n
        ##################################################################################\n"

if [[ "$1" == "start" ]]; then
    if [[ "${hasEnv}" == 0 ]]; then
        echo ${hint}
        exit 0
    fi

    if [[ ! -f "$PID_FILE" ]]; then
        echo -e ${started} | tee -a ${ACCESS_LOG}
        nohup java ${JAVA_OPTIONS} -jar ${APP_HOME}/lib/oes-content-manage-${APP_VERSION}.jar 1>> ${ACCESS_LOG} 2>> ${ERROR_LOG} 2>&1 &echo $! > ${PID_FILE}

        sleep 10
        tail -n 300 ${ACCESS_LOG}
    else
        echo "$APP_NAME service already started, please stop first and try again"
    fi
elif [[ "$1" == "stop" ]]; then
    if [[ -f "$PID_FILE" ]]; then
        echo -e ${stopped} | tee -a ${ACCESS_LOG}
        cat ${PID_FILE} | xargs kill -9
        rm ${PID_FILE}
        echo "$APP_NAME stop : [OK]"
    else
        echo "$APP_NAME service not started yet, please start first and try again"
    fi
elif [[ "$1" == "log" ]]
    then if [[ "$2" == "error" ]]
      then
       tail -f ${ERROR_LOG}
      else
       tail -f ${ACCESS_LOG}
       fi
elif [[ "$1" == "-v" ]]; then
    echo ${APP_VERSION}
elif [[ "$1" == "status" ]]; then
    pid=`cat ${PID_FILE}`
    if [[ $? -eq 0 ]]; then
        p_status=`ps -p ${pid}`
        if [[ $? -eq 0 ]]; then
            echo "Application [${APP_NAME}] is running, pid : ${pid}."
        else
            echo "Application [${APP_NAME}] is not running."
        fi
    else
        echo "Application [${APP_NAME}] is not running."
    fi
else
    echo ${hint}
fi


