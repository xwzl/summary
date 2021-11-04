# 1. sentinel-spring-cloud-gateway-adapter

引入此依赖要打开 GatewayConfiguration 配置,但是控制台无法删除该 API

# 2. spring-cloud-alibaba-sentinel-gateway

引入此依赖要关闭 GatewayConfiguration 配置，修改后的 sentinel 转换规则会错误，因此用官方的 sentinel.
