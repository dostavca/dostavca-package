package si.dostavca.packet;

import java.util.UUID;

public class Packet {

    private String name;
    private String size;
    private String pid;

    public Packet() {
        generatePid();
    }

    public Packet(String name, String size) {
        this.name = name;
        this.size = size;
        generatePid();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPid() {
        return pid;
    }

    private void generatePid() {
        this.pid = UUID.randomUUID().toString();
    }
}
