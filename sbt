SBT_OPTS="-Xms128M -Xmx512M -Xss1M -XX:+CMSClassUnloadingEnabled -Dfile.encoding=UTF8"
java $SBT_OPTS -jar `dirname $0`/sbt-launch.jar "$@"