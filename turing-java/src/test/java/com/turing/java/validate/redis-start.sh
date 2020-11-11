#/usr/bin/bash
redis_path=/root/tool/redis
echo "$1"

case "$1" in
"start")
  $redis_path/src/redis-server $redis_path/config/redis-6379.conf
  $redis_path/src/redis-server $redis_path/config/redis-6380.conf
  $redis_path/src/redis-server $redis_path/config/redis-6381.conf
  $redis_path/src/redis-server $redis_path/config/sentinel-26379.conf --sentinel
  $redis_path/src/redis-server $redis_path/config/sentinel-26380.conf --sentinel
  $redis_path/src/redis-server $redis_path/config/sentinel-26381.conf --sentinel
  ;;
"stop")
  pkill redis-server
  ;;
esac
