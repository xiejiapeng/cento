# 解加鹏 - 数据开发

icon:info 男/1992.07    icon:email2 jsxiejp@163.com  icon:phone 18701874689  icon:blog 上海市浦东新区

## 教育背景

:::left
**上海交通大学 计算机科学与技术 硕士**

- 专业成绩: 年级前5%

- 研究领域: 群智感知激励机制研究、共乘问题研究

- 发表论文5篇，包含顶级期刊 IEEE Transactions on Mobile Computing，Theoretical Computer Science和学术会议 IEEE Global Communications Conference

- 上海交通大学优秀毕业生

:::
::: right
***2015.09 - 2018.04***

:::

:::left
**南京大学 数学与应用数学 学士**

- 专业成绩 年级前5%

- 南京大学优秀毕业生

  :::

  :::right

  ***2011.09 - 2015.06***

  :::

## 个人经验与技能

- 负责数据平台组的团队管理，技术架构设计，研发流程管理，研发规范制定。具有从0-1建设数据平台的经验。
- 具有丰富的数据收集，数据存储和指标开发的实践经验。熟悉数据仓库中数据建模，指标建设，数据治理和元数据管理
- 具备大数据架构的实战经验，熟悉多种大数据生态组件，比如Spark, Flink, Trino, Kafka, HBase, Hadoop, Starrocks, Druid, Flume, Zookeeper，Elasticsearch等。对部分组件具有源码级的理解。
- 具有丰富的后端开发经验，具有扎实的程序语言基础，熟练使用Java/scala/go语言开发，熟练SpringBoot框架，分布式原理，了解MySQL，MongoDB，Elasticsearch，Redis等组件的使用，调优和基本原理
- 具有丰富的AWS EMR，K8S等平台的使用经验，使用Terraform，Ansible, Salt等工具管理集群。具有负责管理公司cloud infra，监控集群的经验。

## 工作经验

:::left

**上海薇仕网络科技有限公司(Wish) - 数据平台部 - 数据开发**

:::

:::right

**2023-05 - 至今**

:::

- 负责数据平台的日志收集，存储与资源使用优化。负责数仓的数据治理，元数据管理，权限管理。设计维护EMR EC2的IAM，SG等配置，通过Ldap和Ranger管理用户任务的Authn和Authz。通过Datahub建设数据血缘关系分析平台，提升数仓和Airflow的使用效率

- 建设Wish电商平台的核心数仓和BI报告系统，根据具体业务进行数据建模，设计打点日志，Airflow pipeline的开发和维护。支撑包含物流，推荐，abtesting等业务领域的数据需求。同时管理和监控数据质量，优化数据流程，提升ETL任务效率

- 负责数据平台大数据EMR集群，Presto集群，负责将EMR上的Spark和Flink集群迁移至Kubernetes。通过配置和任务调优，优化集群资源使用率，优化s3存储，监控和优化小文件问题，降低平台的成本

- 基于Kafka connect维护平台Debezium集群，基于Hudi搭建数据平台数据湖，通过Debezium从Olap数据库到数据湖的同步。通过对Debezium和Hudi的二次开发，满足Wish的定制化需求

:::left
**七牛信息技术有限公司 - 数据平台部 - 数据开发**
:::
:::right
**2017.10 - 2023-05**
:::

- 公司数据平台规划，设计与研发，团队管理与日常code review等工作。制定完善的数仓开发流程规范和约定，提升了数据开发效率和数据质量
- 公司日志产品的架构设计与研发落地。负责开发了七牛日志平台中的实时数据分析看板，流量监控平台和日志下载平台。同事满足了大客户的定制化日志数据需求，并保证了SLA和服务质量。
- 建立基于spark和flink的计算任务开发框架，支持通过DSL/SQL语言完成实时/离线计算任务的开发。将Spark任务开发和Api服务开发的效率从天级别提升到小时级别，并提高了服务质量，极大减少了Bug数目
- 整合公司各业务日志数据，建立数据中台。设计数据仓库建模，数据规范和元数据管理。对公有云CDN日志，动态节点等不同业务建立数据集市。
- 公司各业务日志计量，质量分析，数据看板和报表开发，帮助公司运营，技术支持等部门提供数据支撑，更好服务公司客户。同时以极高的要求保障了公司的实时出账，将出账周期从月降低到天或者小时

::: left

**网易杭州研究院  - 对象存储 - 后端开发工程师**

:::

:::right

***2017.04 - 2017.10***

:::

- 负对象存储产品服务端加密功能架构设计和开发工作

- 负责对象存储产品功能设计，API开发

- 负责对接CDN产品融合厂商API

::: left

**英特尔亚太研发中心 - SSG - 软件开发工程师**

:::

:::right

***2016.07 - 2017.02***

:::

- 负责测试过程管理平台testlink的定制开发

- 通过solr建立testcase的搜索平台

- 调研深度学习组件caffe，参与建立机器学习平台

## 项目经历

:::left

### 数据平台从emr迁移到kubernetes

:::

:::right

2023.05 - 2025.5

:::

`EMR` `Kubernetes`  `Flink`  `Spark` `Hive Metastore` `Trino`

- 项目描述

  为了提升数据平台的运维效率，同时满足降本增效的目标，鉴于Wish拥有完善的Kubernetes平台，我们将数据平台从EMR迁移到Kubenetes。包含Flink集群，Spark集群，Hive Metastore和Trino集群的迁移。在迁移过程中我们需要保证平台服务的稳定和高效运行。

- 工作内容

    - 针对我们在EMR中使用的组件，定制开发对应的Docker image并部署

    - 在Kubernetes平台部署Spark K8s Operator和Flink K8s Operator deployment，配置K8s rbac，并且修改数据平台中所有关于Spark/Flink提交的Airflow operator和api，支持通过K8s api直接提交任务

    - 解决从EMR迁移到K8s的其余问题。主要针对Spark而言。因为Spark on K8s暂时不支持external shuffle server，因此需要对任务进行case by case调优。另外需要针对Spark进行S3 committer调优。

:::left

### 湖仓一体架构实践

:::

:::right

2023.11-2024.12

:::

- 项目描述

  Wish拥有大规模的Mongodb集群，我们希望在Hive数仓引入了对Mongo collection的同步。传统的Hive数仓因为不支持更新，事务等特性，我们引入了Hudi作为数据湖的底座，并将Hudi table同步到Hive metastore，实现了湖仓的融合

- 工作内容

    - 基于Kafka connect平台，通过Debezium将Mongo的oplog实时同步到Kafka集群中。通过原始的oplog，可以获取到有变化的Mongo docuemnt集合

    - 通过定制开发Hudi的Payload，将Mongo侧的删除操作映射为了Hudi侧的软删除，实现了Hudi表支持作为Mongo archival表的功能

    - 通过Mongo的full document change stream 和Hudi的delta stream job，将数据库的更新同步到Hudi表，并且将metadata同步到Hive metastore，作为下游数据分析的原始表

    - 将部分Hive表通过Hudi bootstrap转化为Hudi表，利用merge into的语义满足了upsert的数据需要

:::left

### 流媒体/CDN 动态节点项目

:::

:::right

**2020.02 - 2023.04**

:::

`Java` `Kafka`  `Elasticsearch` `Spark` `Flink` `Druid` `Hive` `StarRocks`

- **项目描述**：
  参与公司直播/点播/下载动态节点项目，负责动态节点项目中客户端与服务端的日志接入，质量分析，业务计量。支撑TB级别带宽业务规模和每秒百万级别数据规模。动态节点通常运行在容器中，与物理机相比单价更低，但是质量不够稳定，因此需要通过数据对动态节点的服务质量进行深入分析，提升业务价值。

- **工作内容**：

  ***日志接入***

  接入的数据源主要包括位于动态节点中的服务日志，SDK日志以及播放器产生的客户端日志。

    - 开发自日志收集组件dsync收集K8S节点日志，通过Nginx+Flume接入到中心机房Kafka中作为原始日志

    - 基于SpringBoot开发统一的对外日志收集服务，对于不同的业务提供统一的入口协议，降低日志接入的负担和运维压力。入口协议支持对日志进行自定义解析，并且控制日志进入Kafka topic的路由逻辑。

      收集后的日志统一使用Kafka作为中间存储层，并在清洗后保存到Hive中建立数仓的ODS层。

  ***质量分析***

    - 通过Elasticsearch接入客户端数据，分析动态节点首帧分位数等指标，基于Kibana构建数据看板；通过Druid接入客户端数据，分析动态节点等指标，通过superset构建数据看板

    - 通过Flink Sql进行日志ETL和计算任务，通过Starrocks构建实时数仓，并基于superset建立实时下载速度，回源率等指标的数据看板。并通过API自动化开发组件，基于SQL完成Portal接口的开发和对接工作。

    - 通过Airflow和Spark构建离线计算任务工作流，完成离线指标的开发，对实时指标的结果进行校验和修正

  ***日志即席查询***

    - 通过Trino+hive的方式实现ODS，DW等日志的即席查询平台。在Superset中建立基于Trino的数据源，导入Trino的catalog和scheme和表的元数据，可以在Superset上进行自助数据查询和分析。

    - 通过Zeppelin+Spark的方式实现adhoc的日志分析和查询任务。Zeppelin Spark支持sql和spark两种模式，sql模式可以直接基于Spark Sql查询Hive数据，spark模式可以通过scala代码进行自定义计算。

    - 合理设计Elasticsearch的索引和schema，加快客户端日志的查询性能。调整优化es的shard的分配规则，保证es集群的负载均衡。

::: left

### 公有云平台CDN日志产品

:::

:::right

***2018.03 - 2020.6***

:::

`Java` `Kafka` `Spark` `Flink` `HBase` `Hive` `StarRocks`

- 项目描述

  负责七牛云平台CDN日志相关产品的开发，包括用量统计，日志分析，日志下载和日志推送等业务。目前该项目稳定支撑了10TB带宽级别的业务，每秒处理的数据量达到数百万量级

- 工作内容

  ***日志接入***

    - 通过SpringBoot开发Java微服务接收融合CDN厂商的实时访问日志，对访问日志进行标准化，过滤等操作，作为实时数据源
    - 拉取融合厂商的离线日志到对象存储中，作为离线数据源

  项目采用Lambda的架构处理数据，通过开发Validation服务校验实时数据准确性，并采用离线数据进行校对。

  ***日志数仓***

    - 通过Flink+Hive，建立CDN日志数仓ODS，DW和ADS层面，日志种类包含CDN访问日志，CDN中间源，CDN回源等。
    - 通过Trino+Hive+Superset的方式，建立日志数仓即席查询平台，业务开发，技术支持等人员可以基于即席查询平台自助进行ad-hoc分析

  ***日志用量统计，分析***

    - 通过Spark Streamming对实时日志进行实时计量，通过spark sql对离线日志进行离线计量。计量数据保存到HBase中，并定期同步到MySQL中。计量数据通过API形式提供给公司计费平台和Portal页面
    - 通过Flink对实时日志进行实时分析，统计状态码，命中率等指标，并基于离线数据进行UV，PV，TopUrl等指标计算。分析结果保存到HBase中，通过API的形式提供到Portal页面

  ***日志下载，推送***

    - 基于CDN日志特点，采用一定策略设置Kafka Topic和分区规则
    - 通过SpringBoot开发Java微服务，对实时日志落地保存到HBase中。HBase的列族采用Mob方式存储数据。设计HBase的RowKey避免region上面的热点问题，并支持按照CDN域名+小时等维度快速Scan日志数据
    - 日志下载服务支持读取HBase中存量日志数据和实时产生的MapFile文件，根据客户约定的下载时效，日志格式，日志分割策略对日志进行处理后保存到对象存储中，并以下载外链的形式提供给客户
    - 日志推送服务支持根据客户自定义的HTTP地址或者阿里云SLS仓库配置，将实时日志推送到客户的日志终端

::: left

### 通用计算框架与API自动化平台

:::

:::right

***2020.06 - 2022. 4***

:::

`Java` `Spark` `Flink` `Antlr` `JDBC` `Calcite` `Arrow`

- 项目描述

基于Antlr4开发DSL语言，通过DSL语言可以完成实时/离线的Spark/Flink计算任务的开发。支持基于数据源的配置和DSL配置数据处理视图pipeline，自动完成API的开发

- 工作内容

***计算平台***

- 通过antlr4设计dsl语言，支持数据源读取，数据ETL，窗口计算，缓存等语法，并同时支持实时/离线计算服务的开发
- 支持通过dsl的方式完成实时和离线的开发，弥补了spark streaming的无法完全sql开发的功能缺陷

***通用API工具***

- 通过YAML配置构建DataSource，支持MySQL，Druid，Trino，StarrRocks等JDBC协议，同时支持MongoDB协议
- 通过YAML配置数据视图的Pipeline，基于Apache Arrow管理内存数据，自定义算子支持分位数等指标的计算
- 将Apache Arrow管理的内存数据作为Calcite数据源Adapter，自定义Calcite的JDBC驱动读取Arrow数据，并基于Arrow产生的物理数据源进行Calcite的SQL运算

::: left

### 对象存储服务端加密

:::

:::right

***2017.4 - 2017.09***

:::

`Java` `AES` `MySQL`

- 项目描述

  对对象存储客户端上传数据进行服务端加密，做好服务端的密钥管理，加解密操作，对客户没有感知

- 工作内容

    - 服务端加密用于解决服务端存储数据的安全性问题
    - 完成对象存储服务端加密的架构设计，功能设计，代码开发，测试等操作
    - 服务端加密采用流式AES加密的方式，将明文数据流封装成加密数据流。下载对象的时候将加密数据流实时解密为明文流
    - 支持Range请求，将明文Range翻译为AES加密后的AES块和对应的Range
    - 完成加密存储的API开发和密钥管理模块的工作
