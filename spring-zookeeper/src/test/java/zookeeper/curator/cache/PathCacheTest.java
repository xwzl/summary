package zookeeper.curator.cache;


import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.junit.Test;
import zookeeper.curator.CuratorBaseOperations;

@Slf4j
public class PathCacheTest extends CuratorBaseOperations{

    public static final String PATH="/path-cache";

    @Test
    public void testPathCache() throws Exception {

        CuratorFramework curatorFramework = getCuratorFramework();

        createIfNeed(PATH);
        // https://github.com/apache/curator/blob/master/curator-examples/src/main/java/cache/PathCacheExample.java
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, PATH, true);


        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                log.info("event:  {}",event);
            }
        });

        // 如果设置为true则在首次启动时就会缓存节点内容到Cache中
        pathChildrenCache.start(true);
    }
}
