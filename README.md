# Device Monitoring

Device Monitoring is consists of two modules.



- First module is device-pool that is generated virtual devices.
- Second module is monitoring server. It consumes messages that are coming from devices. 

###Messaging Flow
                    
```seq
Device.X-->MonitoringServer: REGISTER ( DMessage )
MonitoringServer-->Device.X: REGISTER SUCESS
Device.X-->MonitoringServer: HEALTH_CHECK ( DMessage )
Device.X-->MonitoringServer: HEALTH_CHECK ( DMessage )
Device.X-->MonitoringServer: HEALTH_CHECK ( DMessage )

Device.X-->MonitoringServer: ALERT ( DMessage )
MonitoringServer-->Device.X: ALERT SUCESS
Device.X-->MonitoringServer: HEALTH_CHECK ( DMessage )
Device.X-->MonitoringServer: HEALTH_CHECK ( DMessage )

Device.X-->MonitoringServer: ALERT ( DMessage )
MonitoringServer-->Device.X: ALERT SUCESS

```
