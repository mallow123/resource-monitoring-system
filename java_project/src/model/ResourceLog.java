package model;

public class ResourceLog {
    private int logId;
    private int systemId;
    private int cpu;
    private int memory;
    private int disk;

    public ResourceLog(int logId, int systemId, int cpu, int memory, int disk) {
        this.logId = logId;
        this.systemId = systemId;
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }

    public int getLogId() { return logId; }
    public int getSystemId() { return systemId; }
    public int getCpu() { return cpu; }
    public int getMemory() { return memory; }
    public int getDisk() { return disk; }
}
