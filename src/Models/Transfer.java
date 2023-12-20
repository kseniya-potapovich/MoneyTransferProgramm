package Models;

import java.time.LocalDateTime;

public class Transfer {
    private String fileName;
    private String status;
    private LocalDateTime dateTime;

    public Transfer(String fileName, String status, LocalDateTime dateTime) {
        this.fileName = fileName;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
