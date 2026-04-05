package service;

import dao.ResourceDAO;
import model.ResourceLog;
import java.util.Scanner;

public class ResourceService implements Operations {

    ResourceDAO dao = new ResourceDAO();
    Scanner sc = new Scanner(System.in);

    public void add() {
        try {
            System.out.print("ID: ");
            int id = sc.nextInt();

            System.out.print("System ID: ");
            int sys = sc.nextInt();

            System.out.print("CPU: ");
            int cpu = sc.nextInt();

            System.out.print("Memory: ");
            int mem = sc.nextInt();

            System.out.print("Disk: ");
            int disk = sc.nextInt();

            ResourceLog r = new ResourceLog(id, sys, cpu, mem, disk);
            dao.insert(r);

        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void view() {
        try {
            dao.view();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewDetailed() {
        try {
            dao.viewDetailed();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void highCPU() {
        try {
            dao.showHighCPU();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
