# Plugin gov.redhawk.monitor

Provides code to monitor port statistics.

## Useful classes

* [MonitorUtils](src/gov/redhawk/monitor/MonitorUtils.java) - Provides APIs to add/remove monitoring for ports
* [MonitorPortAdapter](src/gov/redhawk/monitor/MonitorPortAdapter.java) - Used to provide callbacks to an interface method when port statistics are changed

## Supported port IDLs

Monitored ports must support one of the following IDLs to generate statistics:
* IDL:BULKIO/UsesPortStatisticsProvider:1.0
* IDL:BULKIO/ProvidesPortStatisticsProvider:1.0