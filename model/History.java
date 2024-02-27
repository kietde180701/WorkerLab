package model;

import java.time.LocalDate;

public class History extends WorkerModel implements Comparable<History> {
    
    private String status;
    private LocalDate date;
    
    public History(String status, LocalDate date, String id, String name, int age,
            double salary, String workLocation) {
        super(id, name, age, salary, workLocation);
        this.status = status;
        this.date = date;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    @Override
    public int compareTo(History t) {
        return this.getId().compareTo(t.getId());
    }
}
