package curator.leader.test;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LeaderLatchExample {
    private static final int CLIENT_QTY = 10;
    private static final String PATH = "/leaderLatch";

    public static void main(String[] args) throws Exception {
        String connectionString = "127.0.0.1:2181";
        CuratorFramework client2 = CuratorFrameworkFactory.newClient(connectionString, new ExponentialBackoffRetry(1000, 3));
        client2.start();
        System.out.println(client2.getChildren().forPath("/"));

        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
//        TestingServer server = new TestingServer();
        try {
//            String connectionString = server.getConnectString();
            for (int i = 0; i < CLIENT_QTY; ++i) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, new ExponentialBackoffRetry(1000, 3));
                clients.add(client);
                final String clientId = "Client #" + i;
                client.start();
                LeaderLatch example = new LeaderLatch(client, PATH, clientId);
                example.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.err.println("clientId:" + clientId +",isLeader");
                    }

                    @Override
                    public void notLeader() {
                        System.err.println("clientId:" + clientId +",notLeader");
                    }
                });
                examples.add(example);
                example.start();

            }
            examples.get(0).await(5, TimeUnit.SECONDS);
//            Thread.sleep(20000);
            LeaderLatch currentLeader = null;
            while (true) {
                for (int i = 0; i < CLIENT_QTY; ++i) {
                    LeaderLatch example = examples.get(i);
                    System.out.println(example.getId() + "," + example.getLeader().getId() + "," + example.getState().toString());
                    if (example.hasLeadership()) {
                        currentLeader = example;
                    }
                }
                if (currentLeader == null) {
                    System.err.println("current leader is null ");
                    Thread.sleep(1000l);
                    continue;
                } else {
                    System.out.println("current leader is " + currentLeader.getId());
                    break;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
            currentLeader.close();
            examples.get(0).await(5, TimeUnit.SECONDS);
            System.out.println("Client #0 maybe is elected as the leader or not although it want to be");
            System.out.println("the new leader is " + examples.get(0).getLeader().getId());

            System.out.println("Press enter/return to quit\n");
//            new BufferedReader(new InputStreamReader(System.in)).readLine();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Shutting down...");
            for (LeaderLatch exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
//            CloseableUtils.closeQuietly(server);
        }
    }
}
