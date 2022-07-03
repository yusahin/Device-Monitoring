# Device Monitoring

Device Monitoring is consists of two modules.



- First module is device-pool that is generated virtual devices.
- Second module is monitoring server. It consumes messages that are coming from devices. 

## Initialize

Device-pool is based on spring-boot project and runs `localhost:8080`.

Monitoring-Server is based on spring-boot project and `localhost:7070`.  



## Messaging Flow

```mermaid
%% Example of sequence diagram
  sequenceDiagram
Device.X->>MonitoringServer: REGISTER ( DMessage )
MonitoringServer->>Device.X: REGISTER SUCCESS
Device.X->>MonitoringServer: HEALTH_CHECK ( DMessage )
Device.X->>MonitoringServer: HEALTH_CHECK ( DMessage )
Device.X->>MonitoringServer: HEALTH_CHECK ( DMessage )

Device.X->>MonitoringServer: ALERT ( DMessage )
MonitoringServer->>Device.X: ALERT SUCCESS
Device.X->>MonitoringServer: HEALTH_CHECK ( DMessage )
Device.X->>MonitoringServer: HEALTH_CHECK ( DMessage )

Device.X->>MonitoringServer: ALERT ( DMessage )
MonitoringServer->>Device.X: ALERT SUCCESS
```

![image](https://user-images.githubusercontent.com/32652047/177052290-4560d598-ff49-46b9-9d54-b5e655ed8e48.png)
