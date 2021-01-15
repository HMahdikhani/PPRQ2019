
Paper title: Achieving ${O(\log^3n)}$ Communication-Efficient Privacy-Preserving Range Query in Fog-Based IoT


The advance of Internet of Things (IoT) techniques has promoted an increasing number of organizations to explore more mission-critical solutions. However, the response latency, bandwidth usage, and reliability are still challenging issues in traditional IoT. To tackle these challenges, fog-based IoT has become popular and range query is one of the most frequently used operations in fog-based IoT, where, given a range query, a fog node will return the aggregated data from IoT devices to the query user. Because the fog nodes are not fully trusted, there is a desire to design a privacy-preserving range query scheme in fog-based IoT. However, most of existing privacy-preserving range query schemes are not efficient in terms of communication overhead, especially for a large-size range. Therefore, it is still a challenging issue to design a communication-efficient range query in fog-based IoT. Aiming at this challenge, in this paper, we propose a new privacy-preserving range query scheme in fog-based IoT. Specifically, we first devise an efficient homomorphic encryption scheme for maintaining the data privacy and security in a range query. Then, we present a novel range decomposition technique to compile the range query, which can transform a given range query $[L,U]$, where $0\leq L \leq U \leq n-1$, into a semi-triangular structure, and enable our proposed scheme to achieve $O(\log^3n)$ communication efficiency. Detailed security analysis shows that our proposed scheme is really privacy-preserving, and the extensive performance evaluation demonstrates that our proposed scheme is efficient in terms of low communication overhead and computational cost.


@article{mahdikhani2020achieving,
  title={Achieving O (log3n) Communication-Efficient Privacy-Preserving Range Query in Fog-Based IoT},
  author={Mahdikhani, Hassan and Lu, Rongxing and Zheng, Yandong and Shao, Jun and Ghorbani, Ali},
  journal={IEEE Internet of Things Journal},
  year={2020},
  publisher={IEEE}
}
