package controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import view.SalaryStatus;
import model.History;
import model.WorkerModel;
public class WorkerController {
    public List<WorkerModel> workers;

    public WorkerController() {
        workers = new ArrayList<>();
    }

    public boolean addWorker(WorkerModel worker) {
        if (worker.getId() == null || worker.getId().isEmpty() || isDuplicateId(worker.getId())) {
            return false;
        }
        if (worker.getAge() < 18 || worker.getAge() > 50) {
            return false;
        }
        if (worker.getSalary() <= 0) {
            return false;
        }
        workers.add(worker);
        return true;
    }

    public boolean changeSalary(SalaryStatus status, String code, double amount) {
        WorkerModel worker = getWorkerByCode(code);

        if (worker == null) {
            return false;
        }
        
        // Create a new History instance for the worker
        History history = new History(status == SalaryStatus.INCREASE ? "UP" : "DOWN", LocalDate.now(),
                worker.getId(), worker.getName(), worker.getAge(), worker.getSalary(), worker.getWorkLocation());
        
        if (status == SalaryStatus.INCREASE) {
            worker.setSalary(worker.getSalary() + amount);
        } else if (status == SalaryStatus.DECREASE) {
            if (amount <= 0 || worker.getSalary() - amount < 0) {
                return false;
            }
            worker.setSalary(worker.getSalary() - amount);
        }
        
        // Add the history to the list of workers
        workers.add(history);
        
        return true;
    }
    public List<WorkerModel> getInformationSalary() {
        return workers;
    }

    public boolean isDuplicateId(String id) {
        for (WorkerModel worker : workers) {
            if (worker.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private WorkerModel getWorkerByCode(String code) {
        for (WorkerModel worker : workers) {
            if (worker.getId().equals(code)) {
                return worker;
            }
        }
        return null;
    }
}
